/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Database.DBSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Quicksort
 */
public class Setup {
    public static void CreateTable() throws SQLException, UnsupportedEncodingException, IOException {
	DBSession session = DBSession.Current();
	InputStream stream = Setup.class.getResourceAsStream("create_table.sql");
	InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
	String query = "";
	char[] buffer = new char[1024];
	int read = 0;
	while ((read = reader.read(buffer, 0, 1024)) != -1) {
	    query += (new String(buffer));
	}
	reader.close();
	stream.close();
	PreparedStatement statement = session.Connection().prepareStatement(query);
	statement.execute();
    }
}
