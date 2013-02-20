package leaker.friend.service.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.naming.NamingException;

import leaker.friend.Friend;
import leaker.friend.database.FriendRepository;
import leaker.user.exception.UserAlreadyExistsException;

import org.json.JSONException;

public class FriendService {
	public static void addFriend(Friend friend) throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException, UserAlreadyExistsException {
		FriendRepository.insertFriend(friend);
	}
	public static void deleteFriend(Friend friend) throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		FriendRepository.deleteFriend(friend);
	}
	public static void createTable() throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		FriendRepository.installTables();
	}
	public static void dropTable() throws JSONException, UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		FriendRepository.dropTables();
	}
}
