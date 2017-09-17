package wordcount.daoimpl;

import static wordcount.fieldname.Fields.TOTAL_COUNT;
import static wordcount.fieldname.Fields.WORD_NAME;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import wordcount.dao.FindRecordDao;

public final class FindRecordDaoImpl implements FindRecordDao {

	@Override
	public boolean isRecordExist(String token, MongoCollection<Document> collection) {
		if (findToken(token, collection) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Document findToken(String token, MongoCollection<Document> collection) {
		FindIterable<Document> findIterable = collection.find(new Document(WORD_NAME.getName(), token));
		return findIterable.first();
	}

	@Override
	public Document findTokenWithMaxCount(MongoCollection<Document> collection) {
		FindIterable<Document> findIterable = collection.find().sort(new Document(TOTAL_COUNT.getName(), -1)).limit(1);
		return findIterable.first();
	}

	@Override
	public Document findTokenWithMinCount(MongoCollection<Document> collection) {
		FindIterable<Document> findIterable = collection.find().sort(new Document(TOTAL_COUNT.getName(), +1)).limit(1);
		return findIterable.first();
	}

	@Override
	public FindIterable<Document> findAllTokenWithGivenCount(MongoCollection<Document> collection, Integer count) {
		FindIterable<Document> findIterable = collection.find(new Document(TOTAL_COUNT.getName(), count));
		return findIterable;
	}
}
