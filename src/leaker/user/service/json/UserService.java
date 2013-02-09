package leaker.user.service.json;

import leaker.database.EntityManager;
import leaker.user.data.User;

import org.json.JSONObject;

public class UserService {
	public JSONObject insertUser(User user) {
		try {
			EntityManager.getInstance().insertUser(new User());
		} catch(Exception e){
			
		}
	}
}
