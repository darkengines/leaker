package leaker.session.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.NamingException;

import leaker.core.database.Database;
import leaker.session.Session;
import leaker.user.User;
import leaker.user.database.UserRepository;

public class SessionRepository {

	protected static Hashtable<String, String> queriesRegistry = new Hashtable<String, String>();

	protected static String getQuery(String path) throws UnsupportedEncodingException, IOException {
		if (queriesRegistry.containsKey(path)) return queriesRegistry.get(path);
		String string = "";
		InputStream stream = SessionRepository.class.getResourceAsStream(path);
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
	
	public static Session map(ResultSet resultSet) throws Exception {
		Session session = new Session();
		session.setId(resultSet.getLong("id"));
		session.setUserId(resultSet.getLong("user_id"));
		session.setToken(resultSet.getString("token"));
		session.setDate(resultSet.getDate("date"));
		session.setExpired(resultSet.getBoolean("expired"));
		return session;
	}
	public static void installTables() throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		String query = getQuery("create_table.sql");
		PreparedStatement statement = Database.getConnection().prepareStatement(query);
		statement.execute();
		statement.close();
	}
	public static void dropTables() throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		String query = getQuery("drop_table.sql");
		PreparedStatement statement = Database.getConnection().prepareStatement(query);
		statement.execute();
		statement.close();
	}
	public static Session insertSession(Session session) throws UnsupportedEncodingException, IOException, SQLException, ClassNotFoundException, NamingException {
		String query = getQuery("insert_session.sql");
		PreparedStatement ps = Database.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, session.getUserId());
		ps.setObject(2, session.getToken());
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			session.setId(generatedKeys.getLong(1));
		}
		ps.close();
		return session;
	}
	public static Session getSessionByToken(String token) throws Exception {
		Session session = null;
		String query = getQuery("get_session_by_token.sql");
		PreparedStatement ps = Database.getConnection().prepareStatement(query);
		ps.setObject(1, token);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			session = map(result);
		}
		return session;
	}
	public static User GetUserBySession(Session session) throws UnsupportedEncodingException, ClassNotFoundException, SQLException, IOException, NamingException {
		User returnedUser = null;
		UserRepository.getUserById(session.getUserId());
		return returnedUser;
	}
}
