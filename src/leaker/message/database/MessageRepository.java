package leaker.message.database;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.NamingException;

import leaker.core.database.Database;
import leaker.message.Message;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MessageRepository {
	
	protected static Hashtable<String, String> queriesRegistry = new Hashtable<String, String>();
	
	public static Message map(DBObject obj){
		Message message = new Message(); 
		message.setUserId((Long)obj.get("user_id"));
		message.setContent((String)obj.get("content"));
		message.setDate((Date)obj.get("date"));
		return message;
	}
	
	public static Iterable<Message> mapMany(DBCursor cursor) {
		ArrayList<Message> messages = new ArrayList<Message>();
		
		while (cursor.hasNext()) {
			messages.add(map(cursor.next()));
		}
		
		return messages;
	}
	
	public static Message insertMessage(Message message) throws ClassNotFoundException, UnknownHostException, SQLException, NamingException {
		DB db = Database.getMongoDB();
		DBCollection collection = db.getCollection("message");
		
		BasicDBObject data = new BasicDBObject("user_id", message.getUserId())
		.append("content", message.getContent())
		.append("date", message.getDate());
		
		collection.insert(data);
		
		return message;
	}
	
	public static Iterable<Message> getMessagesByUserId(long userId) throws ClassNotFoundException, UnknownHostException, SQLException, NamingException {
		DB db = Database.getMongoDB();
		DBCollection collection = db.getCollection("message");
		
		BasicDBObject filter = new BasicDBObject("user_id", new BasicDBObject("$eq", userId));
		return mapMany(collection.find(filter));
	}
}
