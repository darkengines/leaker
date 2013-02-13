package leaker.user.service.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import leaker.user.data.User;
import leaker.user.data.UserEntityManager;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUserService {
	public JSONObject insertUser(User user) throws JSONException, UnsupportedEncodingException, IOException, SQLException {
		JSONObject result = new JSONObject();
			UserEntityManager em = new UserEntityManager();
			em.insertUser(user);
			result.put("result", "OK");
		return result;
	}
}
