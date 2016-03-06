package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAO {
	String jdbcDriver="oracle.jdbc.driver.OracleDriver";
	String dbConnect ="jdbc:oracle:thin:@10.25.6.156:1521:xe";
//	String dbConnect="jdbc:oracle:thin:@localhost:1521:xe";
	int enterRoom(String id){
		int result = 0;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "update oplayroom set prpopular=2 where id=?";
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
	int exitRoom(String id){
		int result = 0;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "delete oplayroom where id=?";
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
	int insertRoom(RoomVO vo){
		int result = 0;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "insert into oplayroom values(?,play_seq.nextval,?,1,sysdate)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPrtitle());
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
	ArrayList<RoomVO> selectRoom(){
		ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		Connection con = null;
		ResultSet rs = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query = "select * from oplayroom";
			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String userid = rs.getString("id");
				int prseq = rs.getInt("prseq");
				String prtitle = rs.getString("prtitle");
				int prpopular = rs.getInt("prpopular");
				String indate = rs.getString("prindate");
				RoomVO vo = new RoomVO(userid, prseq, prtitle, prpopular, indate);
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
}
