package play;

import org.springframework.stereotype.Component;

@Component
public class RoomVO {
	private String id;
	private int prseq;
	private String prtitle;
	private int prpopular;
	private String indate;
	
	
	public RoomVO() {
	}
	public RoomVO(String id, int prseq, String prtitle, int prpopular, String indate) {
		this.id = id;
		this.prseq = prseq;
		this.prtitle = prtitle;
		this.prpopular = prpopular;
		this.indate = indate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPrseq() {
		return prseq;
	}
	public void setPrseq(int prseq) {
		this.prseq = prseq;
	}
	public String getPrtitle() {
		return prtitle;
	}
	public void setPrtitle(String prtitle) {
		this.prtitle = prtitle;
	}
	public int getPrpopular() {
		return prpopular;
	}
	public void setPrpopular(int prpopular) {
		this.prpopular = prpopular;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	
	
}
