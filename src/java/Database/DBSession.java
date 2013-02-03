/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Quicksort
 */
public class DBSession {
    
    private final String url = "jdbc:mysql://127.0.0.1:3306/leaker";	
    private final String utilisateur = "root";
    private final String motDePasse = "2701104$reg2rt";
    
    private static DBSession dbSession = null;
    private Connection connection = null;
    
    private DBSession() throws SQLException {
	connection = (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
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
