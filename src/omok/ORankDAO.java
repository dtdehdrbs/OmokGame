package omok;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Component;


@Component
public class ORankDAO {
	String jdbcDriver="oracle.jdbc.driver.OracleDriver";
	String dbConnect ="jdbc:oracle:thin:@localhost:1521:xe";
//	String dbConnect ="jdbc:oracle:thin:@10.25.6.156:1521:xe";
	
	public int winScore(String id){
		int result = -1;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query="update orecord set win=win+?, score=score+? where userid=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,1 );
			ps.setInt(2,10 );
			ps.setString(3, id);
			result = ps.executeUpdate();
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	public int loseScore(String id){
		int result = -1;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query="update orecord set los=los+?, score=score+? where userid=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,1 );
			ps.setInt(2,1 );
			ps.setString(3, id);
			result = ps.executeUpdate();
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
}
