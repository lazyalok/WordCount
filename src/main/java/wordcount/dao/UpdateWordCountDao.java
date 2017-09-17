package wordcount.dao;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface UpdateWordCountDao {

	public void updateCounterForGivenToken(String token, MongoCollection<Document> collection);
}
