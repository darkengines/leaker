package leaker.friend;

public class Friend {
	private long id;
	private long leftUserId;
	private long rightUserId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLeftUserId() {
		return leftUserId;
	}
	public void setLeftUserId(long leftUserId) {
		this.leftUserId = leftUserId;
	}
	public long getRightUserId() {
		return rightUserId;
	}
	public void setRightUserId(long rightUserId) {
		this.rightUserId = rightUserId;
	}
}
