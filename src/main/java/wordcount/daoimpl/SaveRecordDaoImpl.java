package wordcount.daoimpl;

import static wordcount.fieldname.Fields.TOTAL_COUNT;
import static wordcount.fieldname.Fields.WORD_NAME;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import wordcount.dao.SaveRecordDao;

public final class SaveRecordDaoImpl implements SaveRecordDao {

	@Override
	public void saveNewRecord(String token, MongoCollection<Document> collection) {
		Document document = new Document();
		document.put(WORD_NAME.getName(), token);
		document.put(TOTAL_COUNT.getName(), 1);
		collection.insertOne(document);
	}

}
