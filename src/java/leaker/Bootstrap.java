/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leaker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Quicksort
 */
public class Bootstrap implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	} catch ( ClassNotFoundException e ) {
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
