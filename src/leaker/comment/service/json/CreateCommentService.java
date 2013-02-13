package leaker.comment.service.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import leaker.comment.data.Comment;
import leaker.comment.data.CommentEntityManager;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateCommentService {
	public JSONObject insertMessage(Comment comment) throws JSONException, UnsupportedEncodingException, IOException, SQLException {
		JSONObject result = new JSONObject();
		CommentEntityManager em = new CommentEntityManager();
		em.insertComment(comment);
		result.put("result", "OK");
		return result;
	}
}
