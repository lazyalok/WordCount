package wordcount.DB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import wordcount.exception.DatabaseConnectionException;

public final class DBConnection {

	private DBConnection() {
	}

	private static MongoDatabase database;

	public static MongoDatabase databaseConnection(String host, Integer port, String databaseName)
			throws DatabaseConnectionException {

		try {

			if (database == null) {
				database = new MongoClient(host, port).getDatabase(databaseName);
			}

		} catch (Exception e) {
			throw new DatabaseConnectionException(
					"Exception occur while connecting to database. Please check host and port details.");
		}

		return database;
	}

}
