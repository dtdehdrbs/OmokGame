package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayDAO {
	String jdbcDriver="oracle.jdbc.driver.OracleDriver";
//	String dbConnect ="jdbc:oracle:thin:@10.25.6.156:1521:xe";
	String dbConnect="jdbc:oracle:thin:@localhost:1521:xe";
	ArrayList<PlayVO> selectPlay(String id){
		ArrayList<PlayVO> list = new ArrayList<PlayVO>();
		Connection con = null;
		ResultSet rs = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "select * from oplay where id=? order by posX, posY";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				int posX = rs.getInt("posX");
				int posY = rs.getInt("posY");
				int status = rs.getInt("status");
				PlayVO vo = new PlayVO(id,posX,posY,status);
				list.add(vo);				
			}
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	PlayVO selectRecentPlay(String id,int order){
		PlayVO vo  = null;
		Connection con = null;
		ResultSet rs = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "select * from oplay where id=? and status=? order by indate desc , posX, posY";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setInt(2, order);
			rs = ps.executeQuery();
			
			if(rs.next()){
				int posX = rs.getInt("posX");
				int posY = rs.getInt("posY");
				int status = rs.getInt("status");				
				vo = new PlayVO(id,posX,posY,status);
			}
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	int insertPlay(String id, String posX, String posY, String status){
		int result = 0;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "insert into oplay values(?,?,?,?,sysdate)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setInt(2, Integer.parseInt(posX));
			ps.setInt(3, Integer.parseInt(posY));
			ps.setInt(4, Integer.parseInt(status));
			result = ps.executeUpdate();			
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result ;
	}
	int deletePlay(String id){
		int result = 0;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "delete from oplay where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			result = ps.executeUpdate();			
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result ;
	}
}
