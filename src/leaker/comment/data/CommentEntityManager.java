package leaker.comment.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import leaker.database.DBSession;
import leaker.database.EntityManager;

import com.mysql.jdbc.PreparedStatement;

public class CommentEntityManager extends EntityManager<Comment> {
	
	public Comment map(ResultSet resultSet) throws SQLException {
		Comment comment = new Comment();
		comment.setId(resultSet.getLong("id"));
		comment.setContent(resultSet.getString("email"));
		comment.setAuthorId(resultSet.getLong("user_id"));
		return comment;
	}

	public void installTables() throws UnsupportedEncodingException, IOException, SQLException {
		DBSession session = DBSession.Current();
		String query = getQuery("create_table.sql");
		PreparedStatement statement = (PreparedStatement) session.Connection().prepareStatement(query);
		statement.execute();
		statement.close();
	}

	public Comment insertComment(Comment comment) throws UnsupportedEncodingException, IOException, SQLException {
		String query = getQuery("insert_comment.sql");
		PreparedStatement ps = (PreparedStatement) DBSession.Current().Connection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setObject(1, comment.getContent());
		ps.setObject(2, comment.getAuthorId());
		ps.setObject(3, comment.getMessageId());
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			comment.setId(generatedKeys.getLong(1));
		} else {
			throw new SQLException("Creating user failed, no generated key obtained.");
		}
		ps.close();
		return comment;
	}
}
