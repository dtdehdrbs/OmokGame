package omok;

import org.springframework.stereotype.Component;

@Component
public class OReplyVO {
	private int replyseq;
	private String replytitle;
	private String replycontents;
	private String replywriter;
	private int replyviewcount;
	private String replytime;
	private String replytype;
	
	public OReplyVO(int replyseq, String replytitle, String replycontents, String replywriter, int replyviewcount,
			String replytime, String replytype) {
		this.replyseq = replyseq;
		this.replytitle = replytitle;
		this.replycontents = replycontents;
		this.replywriter = replywriter;
		this.replyviewcount = replyviewcount;
		this.replytime = replytime;
		this.replytype = replytype;
	}

	public int getReplyseq() {
		return replyseq;
	}

	public void setReplyseq(int replyseq) {
		this.replyseq = replyseq;
	}

	public String getReplytitle() {
		return replytitle;
	}

	public void setReplytitle(String replytitle) {
		this.replytitle = replytitle;
	}

	public String getReplycontents() {
		return replycontents;
	}

	public void setReplycontents(String replycontents) {
		this.replycontents = replycontents;
	}

	public String getReplywriter() {
		return replywriter;
	}

	public void setReplywriter(String replywriter) {
		this.replywriter = replywriter;
	}

	public int getReplyviewcount() {
		return replyviewcount;
	}

	public void setReplyviewcount(int replyviewcount) {
		this.replyviewcount = replyviewcount;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	public String getReplytype() {
		return replytype;
	}

	public void setReplytype(String replytype) {
		this.replytype = replytype;
	}

	public OReplyVO() {
		super();
	}
	
}
