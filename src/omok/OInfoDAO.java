package omok;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.OBoardVO;

public class OInfoDAO {
	String jdbcDriver="oracle.jdbc.driver.OracleDriver";
//	String dbConnect ="jdbc:oracle:thin:@localhost:1521:xe";
	String dbConnect ="jdbc:oracle:thin:@10.25.6.156:1521:xe";
	
	
	//회원가입DAO
	public int insertInfo(OInfoVO vo){
		int result = -1;
		Connection con = null;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			String query="insert into oinfo values(?,?,?,?,?,?,sysdate,'일반회원')";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, vo.getId());
			ps.setString(2, EncryptMD5.encryptMD5(vo.getPw()));
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getPhone());
			ps.setString(6, vo.getBirth());
			result = ps.executeUpdate();
			
			String query2="insert into orecord values(?,0,0,0)";
			PreparedStatement ps2 = con.prepareStatement(query2);
			ps2.setString(1, vo.getId());
			ps2.executeUpdate();
			
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
	//로그인DAO
	public int selectID(String id,String pw){
		int result = -1;
		Connection con = null;
		ResultSet rs = null;
		try{
			String query="select pw from oinfo where id=?";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("pw").equals(EncryptMD5.encryptMD5(pw))){
					result = 0;//정상
				}else{
					result = 1;//비번다름
				}
			}else{
				result = 2;//아이디없음
			}
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return result;
		
	}
	//회원정보수정DAO
	public int updateInfo(OInfoVO vo){
		int result = -1;
		Connection con = null;
		
		try{
			String query="update oinfo set pw=?,name=? , email=?, phone=? where id=?  ";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, EncryptMD5.encryptMD5(vo.getPw()));
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getPhone());
			ps.setString(5, vo.getId());
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
	//회원탈퇴DAO
	public int deleteInfo(String id){
		int result = -1;
		Connection con = null;
		try{
			String query="delete from oinfo where id=?";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			result = ps.executeUpdate();
			String query1="delete from orecord where id=?";
			Class.forName(jdbcDriver);
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setString(1, id);
			ps1.executeUpdate();
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
	//회원조회DAO
	public OInfoVO selectInfo(String id){
		OInfoVO vo = null;
		Connection con = null;
		ResultSet rs = null;
		try{
			String query="select id,pw,name, grade, phone,email,birth,to_char(indate,'yyyy-mm-dd') as indate from oinfo where id=?";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				String userid = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String indate = rs.getString("indate");
				String grade = rs.getString("grade");
				vo = new OInfoVO(userid, pw, name, email, phone, birth, grade);
				vo.setIndate(indate);
			}
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return vo;
	}
	//랭킹DAO
	public ArrayList<ORankVO> selectRank(){
		ArrayList<ORankVO> list = new ArrayList<ORankVO>();
		Connection con = null;
		ResultSet rs = null;
		try{
			String query="select r,userid, score, win, los "
					+ "from "
					+ "(select rownum as r, record.* "
					+ "from (select * from orecord order by score desc) record) where r<=10";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				ORankVO vo = new ORankVO();
				vo.setR(rs.getString("r"));
				vo.setUserid(rs.getString("userid"));
				vo.setScore(rs.getInt("score"));
				vo.setWin(rs.getInt("win"));
				vo.setLos(rs.getInt("los"));
				list.add(vo);
			}
		}catch(ClassNotFoundException e){
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return list;		
	}
	//전체회원조회
	public ArrayList<OInfoVO> totalInfo(OInfoVO vo){
		ArrayList<OInfoVO> result = new ArrayList<OInfoVO>();
		Connection con = null;
		try{
			String query="select id, pw, name, grade, phone,email,birth,to_char(indate,'yyyy-mm-dd') as indate from oinfo"
					+ " where id like ? or name like ? or grade like ? or email like ? or indate like ?";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbConnect,"scott","scott");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%"+vo.getId()+"%");
			ps.setString(2, "%"+vo.getName()+"%");
			ps.setString(3, "%"+vo.getGrade()+"%");
			ps.setString(4, "%"+vo.getEmail()+"%");
			ps.setString(5, "%"+vo.getIndate()+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String userid = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String indate = rs.getString("indate");
				String grade = rs.getString("grade");
				OInfoVO info = new OInfoVO(userid, pw, name, email, phone, birth, grade);
				info.setIndate(indate);
				result.add(info);
			}
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
