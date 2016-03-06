package omok;

public class ORankVO {
	private String r;
	private String userid;
	private int score;
	private int win;
	private int los;
	
	public ORankVO() {
		super();
	}
	public ORankVO(String r,String userid, int score, int win, int los) {
		super();
		this.r = r;
		this.score = score;
		this.win = win;
		this.los = los;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLos() {
		return los;
	}
	public void setLos(int los) {
		this.los = los;
	}
	
}
