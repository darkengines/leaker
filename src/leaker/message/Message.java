package leaker.message;

import java.util.Date;

public class Message {
	private long userId;
	private String content;
	private Date date;
	//private long[] likes;
	//private long[] dislikes;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
