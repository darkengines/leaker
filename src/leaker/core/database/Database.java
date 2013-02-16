package leaker.core.database;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Database {
	
	private static DataSource dataSource = null;
	private static Database instance = null;
	private static MongoClient mongoClient = null;

	private Database() throws SQLException, ClassNotFoundException, NamingException, UnknownHostException {
		Class.forName( "com.mysql.jdbc.Driver" );
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/db");
		mongoClient = new MongoClient();
	}
	public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException, UnknownHostException {
		checkIfDatabaseIsReady();
		return Database.dataSource.getConnection();
	}
	public static MongoClient getMongoDB(String db) throws ClassNotFoundException, UnknownHostException, SQLException, NamingException {
		checkIfDatabaseIsReady();
		return mongoClient;
	}
	private static void checkIfDatabaseIsReady() throws ClassNotFoundException, UnknownHostException, SQLException, NamingException {
		if (instance == null) {
			instance = new Database();
		}
	}
}
