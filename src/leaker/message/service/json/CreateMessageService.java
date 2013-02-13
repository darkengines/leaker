package leaker.message.service.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import leaker.message.data.Message;
import leaker.message.data.MessageEntityManager;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateMessageService {
	public JSONObject insertMessage(Message message) throws JSONException, UnsupportedEncodingException, IOException, SQLException {
		JSONObject result = new JSONObject();
		MessageEntityManager em = new MessageEntityManager();
		em.insertMessage(message);
		result.put("result", "OK");
		return result;
	}
}
