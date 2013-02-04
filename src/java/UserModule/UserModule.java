/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModule;

import Database.DBSession;
import Module.IModule;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quicksort
 */
public class UserModule implements IModule {

    @Override
    public void Install() {
        try {
            DBSession session = DBSession.Current();
            String query = IO.Utils.ReadAsUTF8("create_table.sql");
            PreparedStatement statement = session.Connection().prepareStatement(query);
            statement.execute();
        } catch (IOException ex) {
            Logger.getLogger(UserModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Reinstall() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Uninstall() {
        try {
            DBSession session = DBSession.Current();
            String query = IO.Utils.ReadAsUTF8("drop_table.sql");
            PreparedStatement statement = session.Connection().prepareStatement(query);
            statement.execute();
        } catch (IOException ex) {
            Logger.getLogger(UserModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
