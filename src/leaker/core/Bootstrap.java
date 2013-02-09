package leaker.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
