package wordcount;

import com.mongodb.client.MongoDatabase;

import wordcount.DB.DBConnection;
import wordcount.exception.DatabaseConnectionException;

public final class DataBaseDetail {

	private final String host;
	private final Integer port;
	private final String dbName;

	public DataBaseDetail(String host, Integer port, String dbName) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
	}

	public MongoDatabase database() throws DatabaseConnectionException {
		return DBConnection.databaseConnection(host, port, dbName);
	}

}
