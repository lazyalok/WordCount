package wordcount.DB;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public final class DBCollection {

	private final MongoDatabase database;
	private final String collectionName;

	DBCollection(MongoDatabase database, String collectionName) {
		this.database = database;
		this.collectionName = collectionName;
	}

	public MongoCollection<Document> collection() {
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}

}

// WordCountDocument
// MongoDatabase database = DBConnection.databaseConnection("localhost",
// 27017, "WordCountTest");
