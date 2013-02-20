package leaker.friend.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.NamingException;

import leaker.core.database.Database;
import leaker.friend.Friend;

public class FriendRepository {
	
	protected static Hashtable<String, String> queriesRegistry = new Hashtable<String, String>();

	protected static String getQuery(String path) throws UnsupportedEncodingException, IOException {
		if (queriesRegistry.containsKey(path)) return queriesRegistry.get(path);
		String string = "";
		InputStream stream = FriendRepository.class.getResourceAsStream(path);
		InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[1024];
		while (reader.read(buffer, 0, 1024) != -1) {
			string += (new String(buffer));
		}
		reader.close();
		stream.close();
		queriesRegistry.put(path, string);
		return string;
	}
	
	public static Friend map(ResultSet resultSet) throws SQLException {
		Friend friend = new Friend();
		friend.setId(resultSet.getLong("id"));
		friend.setLeftUserId(resultSet.getLong("left_user_id"));
		friend.setRightUserId(resultSet.getLong("right_user_id"));
		return friend;
	}

	public static void installTables() throws SQLException, UnsupportedEncodingException, IOException, ClassNotFoundException, NamingException {
		Connection connection = Database.getConnection();
		String query = getQuery("create_table.sql");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.execute();
		statement.close();
		connection.close();
	}
	public static void dropTables() throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		Connection connection = Database.getConnection();
		String query = getQuery("drop_table.sql");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.execute();
		statement.close();
		connection.close();
	}
	public static Friend insertFriend(Friend friend) throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		Connection connection = Database.getConnection();
		String query = getQuery("insert_friend.sql");
		PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, friend.getLeftUserId());
		ps.setObject(2, friend.getRightUserId());
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			friend.setId(generatedKeys.getLong(1));
		}
		ps.close();
		connection.close();
		return friend;
	}
	public static void deleteFriend(Friend friend) throws ClassNotFoundException, SQLException, NamingException, UnsupportedEncodingException, IOException {
		Connection connection = Database.getConnection();
		String query = getQuery("delete_friend.sql");
		PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, friend.getId());
		ps.executeUpdate();
		ps.close();
		connection.close();	
	}
}
