package leaker.user.service.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.naming.NamingException;

import leaker.user.User;
import leaker.user.database.UserRepository;
import leaker.web.user.exception.UserAlreadyExistsException;

import org.json.JSONException;

public class UserService {
	public static void insertUser(User user) throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException, UserAlreadyExistsException {
		if (UserRepository.getUserByLogin(user.getLogin()) != null) {
			throw new UserAlreadyExistsException(user);
		}
		UserRepository.insertUser(user);
	}
	public static void deleteUser(User user) throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		UserRepository.deleteUser(user);
	}
	public static void createTable() throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		UserRepository.installTables();
	}
	public static void dropTable() throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		UserRepository.dropTables();
	}
}
