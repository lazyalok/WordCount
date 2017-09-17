package wordcount.dao;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface SaveRecordDao {
	public void saveNewRecord(String token, MongoCollection<Document> collection);
}
