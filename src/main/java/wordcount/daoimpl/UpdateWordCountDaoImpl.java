package wordcount.daoimpl;

import static wordcount.fieldname.Fields.INC;
import static wordcount.fieldname.Fields.TOTAL_COUNT;
import static wordcount.fieldname.Fields.WORD_NAME;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import wordcount.dao.UpdateWordCountDao;

public final class UpdateWordCountDaoImpl implements UpdateWordCountDao {

	@Override
	public void updateCounterForGivenToken(String token, MongoCollection<Document> collection) {
		Document updateDocument = new Document();
		updateDocument.append(INC.getName(), new Document().append(TOTAL_COUNT.getName(), 1));
		updateCounter(token, updateDocument, collection);
	}

	private void updateCounter(String token, Document updateDocument, MongoCollection<Document> collection) {
		 collection.updateOne(new Document(WORD_NAME.getName(), token), updateDocument);
	}

}
