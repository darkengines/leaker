package leaker.user.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import leaker.database.DBSession;
import leaker.database.EntityManager;

import com.mysql.jdbc.PreparedStatement;

public class UserEntityManager extends EntityManager<User> {
	
	public User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setDisplayName(resultSet.getString("display_name"));
		return user;
	}

	public void installTables() throws UnsupportedEncodingException, IOException, SQLException {
		DBSession session = DBSession.Current();
		String query = getQuery("create_table.sql");
		PreparedStatement statement = (PreparedStatement) session.Connection().prepareStatement(query);
		statement.execute();
		statement.close();
	}

	public User insertUser(User user) throws UnsupportedEncodingException, IOException, SQLException {
		String query = getQuery("insert_user.sql");
		PreparedStatement ps = (PreparedStatement) DBSession.Current().Connection().prepareStatement(query);
		ps.setObject(1, user.getId());
		ps.setObject(2, user.getEmail());
		ps.setObject(3, user.getPassword());
		ps.setObject(4, user.getDisplayName());
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			user.setId(generatedKeys.getLong("id"));
		} else {
			throw new SQLException("Creating user failed, no generated key obtained.");
		}
		ps.close();
		return user;
	}
}
