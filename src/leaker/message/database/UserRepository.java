package leaker.message.database;

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
import leaker.user.User;

public class UserRepository {
	
	protected static Hashtable<String, String> queriesRegistry = new Hashtable<String, String>();

	protected static String getQuery(String path) throws UnsupportedEncodingException, IOException {
		if (queriesRegistry.containsKey(path)) return queriesRegistry.get(path);
		String string = "";
		InputStream stream = UserRepository.class.getResourceAsStream(path);
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
	
	public static User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("id"));
		user.setLogin(resultSet.getString("login"));
		user.setPassword(resultSet.getString("password"));
		user.setLastname(resultSet.getString("lastname"));
		user.setFirstname(resultSet.getString("firstname"));
		user.setDateIn(resultSet.getDate("date_in"));
		return user;
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
	public static User insertUser(User user) throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		Connection connection = Database.getConnection();
		String query = getQuery("insert_user.sql");
		PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, user.getLogin());
		ps.setObject(2, user.getPassword());
		ps.setObject(3, user.getLastname());
		ps.setObject(4, user.getFirstname());
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			user.setId(generatedKeys.getLong(1));
		} else {
			throw new SQLException("Cannot insert user: no key returned");
		}
		ps.close();
		connection.close();
		return user;
	}
	public static User getUserById(long id) throws SQLException, UnsupportedEncodingException, IOException, ClassNotFoundException, NamingException {
		Connection connection = Database.getConnection();
		String query = getQuery("get_user_by_id.sql");
		PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, id);
		ResultSet result = ps.executeQuery();
		User user = null;
		if (result.next()) {
			user = map(result);
		}
		ps.close();
		connection.close();
		return user;
	}
	public static User getUserByLogin(String login) throws SQLException, UnsupportedEncodingException, IOException, ClassNotFoundException, NamingException {
		Connection connection = Database.getConnection();
		String query = getQuery("get_user_by_login.sql");
		PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, login);
		ResultSet result = ps.executeQuery();
		User user = null;
		if (result.next()) {
			user = map(result);
		}
		ps.close();
		connection.close();
		return user;
	}

	public static void deleteUser(User user) throws ClassNotFoundException, SQLException, NamingException, UnsupportedEncodingException, IOException {
		Connection connection = Database.getConnection();
		String query = getQuery("delete_user.sql");
		PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, user.getId());
		ps.executeUpdate();
		ps.close();
		connection.close();	
	}
}
