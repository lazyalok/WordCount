package wordcount.DB;

import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import wordcount.DataBaseDetail;
import wordcount.dao.FindRecordDao;
import wordcount.dao.SaveRecordDao;
import wordcount.dao.UpdateWordCountDao;
import wordcount.daoimpl.FindRecordDaoImpl;
import wordcount.daoimpl.SaveRecordDaoImpl;
import wordcount.daoimpl.UpdateWordCountDaoImpl;
import wordcount.exception.DatabaseConnectionException;
import wordcount.exception.HostOrPortMissingException;

/**
 * 
 * @author ALOK
 * 
 *         NOTE- Below tests are as of now executed on real DB(As I am newbie on
 *         mongodb and I have developed this application basis of these tests).
 *         I will mocked the DB connections and other real DB interaction later.
 * 
 *         Purpose of this class is to check Insert, Update and Select queries
 *         on MongoDB database. And we also ensure in this test class that word
 *         counter is incrementing properly.
 * 
 *         DB name - WordCountTest Collection name - WordCount MongoDB Port -
 *         27017
 *
 */
public class DBConnectionAndRecordStoreTest {

	private SaveRecordDao saveRecordDao;
	private MongoCollection<Document> collection;
	private UpdateWordCountDao updateWordCountDao;
	private FindRecordDao findRecordDao;
	private DataBaseDetail dbDetails;
	private MongoDatabase database;

	@Before
	public void setUp() throws HostOrPortMissingException, DatabaseConnectionException {
		updateWordCountDao = new UpdateWordCountDaoImpl();
		saveRecordDao = new SaveRecordDaoImpl();
		findRecordDao = new FindRecordDaoImpl();
		dbDetails = new DataBaseDetail("localhost", 27017, "WordCountTest");
		database = dbDetails.database();
		collection = database.getCollection("WordCount");
	}

	@After
	public void afterTest() {
		collection.drop();
	}

	@Test
	public void checkDatabaseConnection() throws Exception {
		// Given
		DataBaseDetail dbDetails = new DataBaseDetail("localhost", 27017, "WordCountTest");

		// When
		MongoDatabase database = dbDetails.database();

		// Then
		Assert.assertEquals(database.getName(), "WordCountTest");
	}

	@Test
	public void checkIfDocumentExistInCollection() throws Exception {
		// Given
		String token = "DragonMotherKhaleesi";

		// When
		boolean status = findRecordDao.isRecordExist(token, collection);

		// Then
		Assert.assertFalse(status);
	}

	@Test
	public void createDocumentInCollection() throws Exception {
		// Given
		String token = "DragonFatherJonSnow";

		// When
		saveRecordIfNotExist(token);

		// Then
		boolean recordExist = findRecordDao.isRecordExist(token, collection);
		Assert.assertTrue(recordExist);
	}

	@Test
	public void updateCounterInDocumentIfTokenExist() throws Exception {

		// Given
		String token = "DragonFatherJonSnow";

		// When
		saveRecordIfNotExist(token);
		updateRecordIFExist(token);
		Document doc = findRecordDao.findToken(token, collection);

		// Then
		Integer count = doc.getInteger("TotalCount");
		Assert.assertTrue(count > 1);

	}

	@Test
	public void shouldReturnMaximumTotalCount() throws Exception {
		// Given
		String token = "DragonFatherJonSnow";

		// When
		saveRecordIfNotExist(token);
		updateRecordIFExist(token);
		updateRecordIFExist(token);
		Document doc = findRecordDao.findTokenWithMaxCount(collection);
		Integer count = doc.getInteger("TotalCount");

		// Then
		Assert.assertTrue(count == 3);

	}

	@Test
	public void getAllDocumentObjectsHavingMaximumTotalCountRecord() throws Exception {
		
		//make sure existing collection is dropped 
		collection.drop();
		
		// Given
		String token = "DragonFatherJonSnow";

		saveRecordIfNotExist(token);
		updateRecordIFExist(token);
		updateRecordIFExist(token);

		String token2 = "CerseiLannister";

		saveRecordIfNotExist(token2);
		updateRecordIFExist(token2);
		updateRecordIFExist(token2);

		String token3 = "TyrionLannister";

		saveRecordIfNotExist(token3);
		updateRecordIFExist(token3);
		updateRecordIFExist(token3);
		updateRecordIFExist(token3);

		String token4 = "DaenerysTargaryen";

		saveRecordIfNotExist(token4);
		updateRecordIFExist(token4);
		updateRecordIFExist(token4);
		updateRecordIFExist(token4);

		// When
		Document doc = findRecordDao.findTokenWithMaxCount(collection);
		Integer maxTotalCount = doc.getInteger("TotalCount");

		FindIterable<Document> documents = findRecordDao.findAllTokenWithGivenCount(collection, maxTotalCount);
		int size = getSize(documents);

		// Then
		// expecting two records having total count 4. (DaenerysTargaryen and
		// TyrionLannister)
		Assert.assertEquals(2, size);
	}

	@SuppressWarnings("unused")
	private int getSize(FindIterable<Document> documents) {
		int size = 0;
		for (Document document : documents) {
			size++;
		}
		return size;
	}

	private void saveRecordIfNotExist(String token) {
		boolean recordExist = findRecordDao.isRecordExist(token, collection);
		if (!recordExist) {
			saveRecordDao.saveNewRecord(token, collection);
		}
	}

	private void updateRecordIFExist(String token) {
		boolean status = findRecordDao.isRecordExist(token, collection);

		if (status) {
			updateWordCountDao.updateCounterForGivenToken(token, collection);
		}
	}

	/**
	 * Below test I am commenting it out. Earlier I performed validation and
	 * throw exception in service layer, later I have moved the validation on
	 * handler level.
	 * 
	 */
	/*
	 * @Test(expected = HostOrPortMissingException.class) public void
	 * shoudThrowExceptionWhenHostOrPortUnavailable() throws Exception {
	 * DataBaseDetail dbDetails = new DataBaseDetail(null, 27017,
	 * "WordCountTest"); dbDetails.database(); }
	 */

	/*
	 * @Test(expected = DatabaseConnectionException.class) public void
	 * shoudThrowExceptionWhenInvalidHostOrPortProvided() throws Exception {
	 * DataBaseDetail dbDetails = new DataBaseDetail("DeadArmyHost", 007,
	 * "WordCountTest"); dbDetails.database(); }
	 */
}
