package play;

import org.springframework.stereotype.Component;

@Component
public class PlayVO {
	private String id;
	private int posX;
	private int posY;
	private int status;
	
	public PlayVO() {}
	public PlayVO(String id, int posX, int posY, int status) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
