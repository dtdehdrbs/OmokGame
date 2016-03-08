package reply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.OBoardVO;
import org.springframework.stereotype.Component;


@Component
public class OReplyDAO {
	String jdbcDriver="oracle.jdbc.driver.OracleDriver";
//	String dbConnect ="jdbc:oracle:thin:@10.25.6.156:1521:xe";
	String dbConnect ="jdbc:oracle:thin:@localhost:1521:xe";
	public ArrayList<OReplyVO> getReplyList(String type){//자유게시판
		ArrayList<OReplyVO> result = 
				new ArrayList<OReplyVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String query = "select * from oreply where type=?";
		PreparedStatement ps = 
		con.prepareStatement(query);
		ps.setString(1, type);
		ResultSet rs = ps.executeQuery();		
		while(rs.next()){
			int seq = rs.getInt("seq");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String writer = rs.getString("writer");
			int viewcount = rs.getInt("viewcount");
			String rtype = rs.getString("type");
			String time = rs.getString("time");
			OReplyVO vo = new OReplyVO(seq,title,contents,writer,viewcount,time,rtype);
			result.add(vo);
		}
		}catch(ClassNotFoundException e){
			System.out.println("드라이버오류");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){}
		}
		
		return result;
	}
	public int insertReply(OReplyVO vo) {
		int result = 0;
		Connection con = null;
		try {
			
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect, "scott", "scott");
			String query = "insert into oreply values (?,?,?,?,0,?,sysdate)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, vo.getSeq());//seq
			st.setString(2, vo.getTitle());//title
			st.setString(3, vo.getContents());//contents
			st.setString(4, vo.getWriter());//writer
			st.setString(5, vo.getType());//type
			result = st.executeUpdate();
			
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	public OReplyVO getBoardDetail(int seq,String type){
		OReplyVO vo = null;
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection(dbConnect, "scott", "scott");
		
		String updateQuery="update oreply set viewcount = viewcount + 1 where type= ? and seq = ?";
				
		PreparedStatement updatePt = 
				con.prepareStatement(updateQuery);
		updatePt.setString(1, type);
		updatePt.setInt(2, seq);
		updatePt.executeUpdate();
		
		String query = "select * from oreply where seq=? and type=?";
		PreparedStatement ps = con.prepareStatement(query);	
		ps.setInt(1, seq);
		ps.setString(2, type);		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int rseq = rs.getInt("seq");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String writer = rs.getString("writer");
			int viewcount = rs.getInt("viewcount");
			String rtype = rs.getString("type");
			String time = rs.getString("time");
			vo = new OReplyVO(rseq,title,contents,writer,viewcount,time,rtype);
		}
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
		}finally{
			try{
				con.close();
			}catch(SQLException e){}
		}
		
		return vo;
	}
}
