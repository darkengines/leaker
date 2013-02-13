package leaker.comment.data;

public class Comment {
	private long id;
	private String content;
	private long author_id;
	private long message_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getAuthorId() {
		return author_id;
	}
	public void setAuthorId(long author_id) {
		this.author_id = author_id;
	}
	public long getMessageId() {
		return message_id;
	}
	public void setMessageId(long message_id) {
		this.message_id = message_id;
	}
}
