package wordcount.serviceimpl;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import wordcount.dao.FindRecordDao;
import wordcount.dao.SaveRecordDao;
import wordcount.dao.UpdateWordCountDao;
import wordcount.service.SaveRecordService;

public final class SaveRecordServiceImpl implements SaveRecordService {

	private SaveRecordDao saveRecordDao;
	private UpdateWordCountDao updateWordCountDao;
	private FindRecordDao findRecordDao;
	private MongoCollection<Document> collection;

	public SaveRecordServiceImpl(SaveRecordDao saveRecordDao, UpdateWordCountDao updateWordCountDao,
			FindRecordDao findRecordDao, MongoCollection<Document> collection) {
		this.saveRecordDao = saveRecordDao;
		this.updateWordCountDao = updateWordCountDao;
		this.findRecordDao = findRecordDao;
		this.collection = collection;
	}

	@Override
	public void saveAndUpdateRecordInDB(String token) {

		boolean status = findRecordDao.isRecordExist(token, collection);
		
		if (status) {
			updateWordCountDao.updateCounterForGivenToken(token, collection);
		} else {
			saveRecordDao.saveNewRecord(token, collection);
		}

	}
}
