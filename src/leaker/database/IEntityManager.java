package leaker.database;

import java.sql.ResultSet;

public interface IEntityManager<T> {
	T map(ResultSet resultSet) throws Exception;
}
