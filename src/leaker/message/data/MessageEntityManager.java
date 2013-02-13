package leaker.message.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import leaker.database.DBSession;
import leaker.database.EntityManager;

import com.mysql.jdbc.PreparedStatement;

public class MessageEntityManager extends EntityManager<Message> {
	
	public Message map(ResultSet resultSet) throws SQLException {
		Message message = new Message();
		message.setId(resultSet.getLong("id"));
		message.setContent(resultSet.getString("email"));
		message.setAuthorId(resultSet.getLong("user_id"));
		return message;
	}

	public void installTables() throws UnsupportedEncodingException, IOException, SQLException {
		DBSession session = DBSession.Current();
		String query = getQuery("create_table.sql");
		PreparedStatement statement = (PreparedStatement) session.Connection().prepareStatement(query);
		statement.execute();
		statement.close();
	}

	public Message insertMessage(Message message) throws UnsupportedEncodingException, IOException, SQLException {
		String query = getQuery("insert_message.sql");
		PreparedStatement ps = (PreparedStatement) DBSession.Current().Connection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, message.getId());
		ps.setObject(2, message.getContent());
		ps.setObject(3, message.getAuthorId());
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			message.setId(generatedKeys.getLong(1));
		} else {
			throw new SQLException("Creating user failed, no generated key obtained.");
		}
		ps.close();
		return message;
	}
}
