/**
 * 
 */
/**
 * @author 2800931
 *
 */
package leaker.session.service.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.UUID;

import javax.naming.NamingException;

import leaker.user.database.UserRepository;
import leaker.user.exception.BadCredentialException;
import leaker.session.Session;
import leaker.session.database.SessionRepository;
import leaker.user.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionService {
	public static JSONObject insertSession(Session session) throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		JSONObject result = new JSONObject();	
		Session insertedSession = SessionRepository.insertSession(session);
		User user = UserRepository.getUserById(session.getUserId());
		result.put("id", user.getId());
		result.put("login", user.getLogin());
		result.put("key", insertedSession.getToken());
		return result;
	}
	public static void createTable() throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
			SessionRepository.installTables();
	}
	public static void dropTable() throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
			SessionRepository.dropTables();
	}
	public static JSONObject login(User user) throws BadCredentialException, JSONException, UnsupportedEncodingException, ClassNotFoundException, IOException, SQLException, NamingException {
		JSONObject result = new JSONObject();
		User returnedUser = UserRepository.getUserByCredential(user);
		if(returnedUser == null) {
			throw new BadCredentialException(user);
		}
		Session session = new Session();
		session.setUserId(returnedUser.getId());
		session.setToken(UUID.randomUUID().toString());
		Session insertedSession = SessionRepository.insertSession(session);
		result.put("id", returnedUser.getId());
		result.put("login", returnedUser.getLogin());
		result.put("key", insertedSession.getToken());
		return result;
	}
	
}
