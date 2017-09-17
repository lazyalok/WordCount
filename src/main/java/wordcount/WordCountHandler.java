package wordcount;

import static wordcount.fieldname.DBInfo.COLLECTION_NAME;
import static wordcount.validation.ValidateFile.getFile;
import static wordcount.validation.ValidateFile.validateFile;

import java.io.BufferedReader;
import java.io.FileReader;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import wordcount.dao.FindRecordDao;
import wordcount.dao.SaveRecordDao;
import wordcount.dao.UpdateWordCountDao;
import wordcount.daoimpl.FindRecordDaoImpl;
import wordcount.daoimpl.SaveRecordDaoImpl;
import wordcount.daoimpl.UpdateWordCountDaoImpl;
import wordcount.exception.DatabaseConnectionException;
import wordcount.fieldname.Fields;
import wordcount.service.SaveRecordService;
import wordcount.serviceimpl.SaveRecordServiceImpl;

public final class WordCountHandler {

	private SaveRecordDao saveRecordDao;
	private UpdateWordCountDao updateWordCountDao;
	private FindRecordDao findRecordDao;
	private DataBaseDetail dataBaseDetail;
	private SaveRecordService saveRecordService;
	private final String fileName;

	WordCountHandler(InputArguments inputArguments) {
		this.fileName = inputArguments.getFileName();
		this.dataBaseDetail = inputArguments.getDataBaseDetails();
		this.updateWordCountDao = new UpdateWordCountDaoImpl();
		this.saveRecordDao = new SaveRecordDaoImpl();
		this.findRecordDao = new FindRecordDaoImpl();
	}

	public void getTokensFromFileAndStoreInDB() throws Exception {

		validateFile(fileName);
		this.saveRecordService = new SaveRecordServiceImpl(saveRecordDao, updateWordCountDao, findRecordDao,
				collection());

		BufferedReader br = new BufferedReader(new FileReader(getFile(fileName)));
		String read = null;
		while ((read = br.readLine()) != null) {
			String[] splited = read.split("\\s+");
			for (String token : splited) {
				saveRecordService.saveAndUpdateRecordInDB(token);
			}
		}
		br.close();
	}

	public FindIterable<Document> showReportForDocumentsHavingMaximumTotalCount() throws Exception {

		MongoCollection<Document> collection = collection();
		Document doc = findRecordDao.findTokenWithMaxCount(collection);
		Integer maxTotalCount = doc.getInteger(Fields.TOTAL_COUNT.getName());

		return findRecordDao.findAllTokenWithGivenCount(collection, maxTotalCount);

	}

	public FindIterable<Document> showReportForDocumentsHavingMinimumTotalCount() throws Exception {

		MongoCollection<Document> collection = collection();
		Document doc = findRecordDao.findTokenWithMinCount(collection);
		Integer minCount = doc.getInteger(Fields.TOTAL_COUNT.getName());

		return findRecordDao.findAllTokenWithGivenCount(collection, minCount);

	}

	private MongoCollection<Document> collection() throws DatabaseConnectionException {
		MongoDatabase database = dataBaseDetail.database();
		MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME.getName());
		return collection;
	}
}
