package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.websocket.Session;

public class OBoardDAO {
	String jdbcDriver="oracle.jdbc.driver.OracleDriver";
	String dbConnect ="jdbc:oracle:thin:@10.25.6.156:1521:xe";
//	String dbConnect ="jdbc:oracle:thin:@localhost:1521:xe";
	public int getTotalCount(String boardtype){
		int result = 0;
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String totalQuery = 
		"select count(*) from oboard where boardtype=?";
		PreparedStatement seqPt = 
		con.prepareStatement(totalQuery);
		seqPt.setString(1, boardtype);
		ResultSet rs = seqPt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);
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
	//게시물페이징리스트 조회 메소드
	public ArrayList<OBoardVO> getBoardList
	(int currentPage, int cntPerPage){
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String pagingQuery = 
		"select r, boardseq, boardtitle, "
		+" boardwriter, boardcontents, "
		+"	boardviewcount, "
		+" to_char(boardtime, 'yyyy/mm/dd-hh:mi:ss')"
		+" boardtime from"
		+" (select rownum r, boardtime .*"
		+" from (select * from oboard "
		+" order by boardtime desc) boardtime) "
		+" where r >= ? and r <= ?";
		PreparedStatement seqPt = 
		con.prepareStatement(pagingQuery);
		//페이지선택:currentPage, : 1
		//한페이지당출력레코드갯수 : cntPerPage : 5
	int start = (currentPage-1)*cntPerPage + 1;//1
	int end = currentPage*cntPerPage ;//5

		seqPt.setInt(1, start);
		seqPt.setInt(2, end);
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("boardseq");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	
	public int updateBoard(OBoardVO vo){
		int result=0;
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String updateQuery = "update oboard "
				+ "set boardtitle= ?, boardcontents=? "
				+ "where boardseq="
				+"(select boardseq from (select rownum r, board.*"
				+ " from (select * from oboard where boardtype=?) board) where r=?)";
				PreparedStatement seqPt = 
				con.prepareStatement(updateQuery);	
				seqPt.setString(1, vo.getBoardtitle());
				seqPt.setString(2, vo.getBoardcontents());
				seqPt.setString(3, vo.getBoardtype());
				seqPt.setInt(4, vo.getBoardseq());
				result = seqPt.executeUpdate();
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
	
	public int deleteBoard(int seq,String type){
		int result=0;
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String detailQuery = 
				"delete from oboard where boardseq="
				+ "(select boardseq from (select rownum r, board.*"
				+ " from (select * from oboard where boardtype=?) board) where r=?)";
				PreparedStatement seqPt = 
				con.prepareStatement(detailQuery);	
				seqPt.setString(1, type);
				seqPt.setInt(2, seq);
				result = seqPt.executeUpdate();
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
	
	public OBoardVO getBoardDetail(int seq,String type){
		OBoardVO vo = new OBoardVO();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String updateQuery="update oboard set boardviewcount = boardviewcount + 1"
				+" where boardseq ="
				+ "(select boardseq from (select rownum r, board.*"
				+ " from (select * from oboard where boardtype=?) board) where r=?)";
		PreparedStatement updatePt = 
				con.prepareStatement(updateQuery);
		updatePt.setString(1, type);
		updatePt.setInt(2, seq);
		updatePt.executeUpdate();
		String detailQuery = 
				"select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
						+ "from (select rownum r2, board2.* from(select rownum r, board.* "
						+ "from (select * from oboard where boardtype=?) board order by r desc) board2 ) where r=?";
		PreparedStatement seqPt = 
		con.prepareStatement(detailQuery);	
		seqPt.setString(1, type);
		seqPt.setInt(2, seq);
		ResultSet rs = seqPt.executeQuery();
		if(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
		
		return vo;
	}
	
	public ArrayList<OBoardVO> getQandAList(){//Q&A게시판
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String seqQuery = "select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
				+ "from (select rownum r2, qna2.* from(select rownum r, qna.* "
				+ "from (select * from oboard where boardtype='qna') qna order by r desc) qna2 ) where r2 between 1 and 5";
		PreparedStatement seqPt = 
		con.prepareStatement(seqQuery);	
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	
	public ArrayList<OBoardVO> getNoticeList(){//공지사항
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String seqQuery = "select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
				+ "from (select rownum r2, notice2.* from(select rownum r, notice.* "
				+ "from (select * from oboard where boardtype='notice') notice order by r desc) notice2 ) where r2 between 1 and 5";
		PreparedStatement seqPt = 
		con.prepareStatement(seqQuery);	
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	public ArrayList<OBoardVO> getFreeList(){//자유게시판
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String seqQuery = "select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
				+ "from (select rownum r2, free2.* from(select rownum r, free.* "
				+ "from (select * from oboard where boardtype='free') free order by r desc) free2 ) where r2 between 1 and 5";
		PreparedStatement seqPt = 
		con.prepareStatement(seqQuery);
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	//자유게시판
	public ArrayList<OBoardVO> getNoticeList(int currentPage, int cntPerPage){
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String seqQuery = "select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
				+ "from (select rownum r2, notice2.* from(select rownum r, notice.* "
				+ "from (select * from oboard where boardtype='notice') notice order by r desc) notice2 ) where r2 between ? and ?";
		PreparedStatement seqPt = 
		con.prepareStatement(seqQuery);
		int start = (currentPage-1)*cntPerPage + 1;
		int end = currentPage*cntPerPage ;
		
		seqPt.setInt(1, start);
		seqPt.setInt(2, end);
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	public ArrayList<OBoardVO> getQandAList(int currentPage, int cntPerPage){
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String seqQuery = "select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
				+ "from (select rownum r2, qna2.* from(select rownum r, qna.* "
				+ "from (select * from oboard where boardtype='qna') qna order by r desc) qna2 ) where r2 between ? and ?";
		PreparedStatement seqPt = 
		con.prepareStatement(seqQuery);
		int start = (currentPage-1)*cntPerPage + 1;
		int end = currentPage*cntPerPage ;
		
		seqPt.setInt(1, start);
		seqPt.setInt(2, end);
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	public ArrayList<OBoardVO> getFreeList(int currentPage, int cntPerPage){
		ArrayList<OBoardVO> result = 
				new ArrayList<OBoardVO>();
		Connection con = null;
		try{
		Class.forName
		(jdbcDriver);
		con = DriverManager.getConnection
		(dbConnect, 
			"scott", "scott");
		String seqQuery = "select r, boardtitle, boardwriter, boardcontents, to_char(boardtime, 'yyyy/mm/dd') boardtime, boardviewcount, boardtype "
				+ "from (select rownum r2, free2.* from(select rownum r, free.* "
				+ "from (select * from oboard where boardtype='free') free order by r desc) free2 ) where r2 between ? and ?";
		PreparedStatement seqPt = 
		con.prepareStatement(seqQuery);
		int start = (currentPage-1)*cntPerPage + 1;
		int end = currentPage*cntPerPage ;
		
		seqPt.setInt(1, start);
		seqPt.setInt(2, end);
		ResultSet rs = seqPt.executeQuery();
		while(rs.next()){
			int boardseq = rs.getInt("r");
			String boardtitle = 
				rs.getString("boardtitle");
			String boardwriter = 
					rs.getString("boardwriter");
			String boardcontents = 
					rs.getString("boardcontents");
			String boardtime = 
					rs.getString("boardtime");
			int boardviewcount = 
					rs.getInt("boardviewcount");
			String boardtype = rs.getString("boardtype");
			OBoardVO vo = new OBoardVO
			(boardseq, boardtitle, boardwriter,
			boardcontents, boardtime, boardviewcount, boardtype);
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
	public int insertBoard(OBoardVO vo) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect, "scott", "scott");
			System.out.println("db 연결성공");
			String query = "insert into oboard values (brd_seq.nextval,?,?,?,sysdate,0,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, vo.getBoardtitle());
			st.setString(2, vo.getBoardwriter());
			st.setString(3, vo.getBoardcontents());
			st.setString(4, vo.getBoardtype());
			result = st.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
				System.out.println("db 연결해제성공");
			} catch (SQLException e) {
			}
		}
		return result;
	}
}
