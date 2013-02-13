package leaker.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public abstract class EntityManager<T> implements IEntityManager<T> {

	protected Hashtable<String, String> queriesRegistry = new Hashtable<String, String>();

	protected String getQuery(String path) throws UnsupportedEncodingException, IOException {
		if (queriesRegistry.containsKey(path)) return queriesRegistry.get(path);
		String string = "";
		InputStream stream = this.getClass().getResourceAsStream(path);
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
}
