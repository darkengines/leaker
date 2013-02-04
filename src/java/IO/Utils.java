/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import UserModule.UserModule;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author root
 */
public class Utils {
    public static String ReadAsUTF8(String resource) throws UnsupportedEncodingException, IOException {
        InputStream stream = UserModule.class.getResourceAsStream("create_table.sql");
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        String string = "";
        char[] buffer = new char[1024];
        int read = 0;
        while ((read = reader.read(buffer, 0, 1024)) != -1) {
            string += (new String(buffer));
        }
        reader.close();
        stream.close();
        return string;
    }
}
