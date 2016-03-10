package omok;

import org.springframework.stereotype.Component;

@Component
public class OBoardVO {
	private int seq;
	private String boardtitle;
	private String boardwriter;
	private String boardcontents;
	private String boardtime;
	private int boardviewcount;
	private String boardtype;
	public OBoardVO(){};
	public OBoardVO(int seq, String boardtitle, String boardwriter, String boardcontents, String boardtime, int boardviewcount, String boardtype){
		super();
		this.seq = seq;
		this.boardtitle = boardtitle;
		this.boardwriter = boardwriter;
		this.boardcontents = boardcontents;
		this.boardtime = boardtime;
		this.boardviewcount = boardviewcount;
		this.boardtype=boardtype;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public String getBoardwriter() {
		return boardwriter;
	}
	public void setBoardwriter(String boardwriter) {
		this.boardwriter = boardwriter;
	}
	public String getBoardcontents() {
		return boardcontents;
	}
	public void setBoardcontents(String boardcontents) {
		this.boardcontents = boardcontents;
	}
	public String getBoardtime() {
		return boardtime;
	}
	public void setBoardtime(String boardtime) {
		this.boardtime = boardtime;
	}
	public int getBoardviewcount() {
		return boardviewcount;
	}
	public void setBoardviewcount(int boardviewcount) {
		this.boardviewcount = boardviewcount;
	}
	
}