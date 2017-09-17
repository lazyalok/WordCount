package wordcount.dao;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public interface FindRecordDao {
	public boolean isRecordExist(String token, MongoCollection<Document> collection);

	public Document findToken(String token, MongoCollection<Document> collection);

	public Document findTokenWithMaxCount(MongoCollection<Document> collection);

	public FindIterable<Document> findAllTokenWithGivenCount(MongoCollection<Document> collection, Integer maxCount);

	public Document findTokenWithMinCount(MongoCollection<Document> collection);

}
