package leaker.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DBSession {
	
	private final String url = "jdbc:mysql://127.0.0.1:3306/leaker";	
	private final String user = "root";
	private final String password = "2701104$reg2rt";

	private static DBSession dbSession = null;
	private Connection connection = null;

	private DBSession() throws SQLException {
		connection = (Connection) DriverManager.getConnection(url, user, password);
	}
	public static DBSession Current() throws SQLException {
		if (dbSession == null) {
			dbSession = new DBSession();
		}
		return dbSession;
	}
	public Connection Connection() {
		return connection;
	}
}
