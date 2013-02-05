/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModule;

import Database.DBSession;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 *
 * @author Quicksort
 */
public class UserService {
    
    /**
     *
     * @param user
     */
    public void Add(User user) throws IOException, UnsupportedEncodingException, SQLException {
        String query = IO.Utils.ReadAsUTF8("insert_or_update_user.sql");
	PreparedStatement ps = (PreparedStatement) DBSession.Current().Connection().prepareCall(query);
	ps.setObject(1, user.getId());
	ps.setObject(2, user.getEmail());
	ps.setObject(3, user.getPassword());
	ps.setObject(4, user.getDisplayName());
	ps.setObject(5, user.getToken());
    }
    public User Get(User user) {
	return null;
    }
    
    
}
