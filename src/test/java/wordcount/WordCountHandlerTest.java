package wordcount;

import java.io.FileNotFoundException;

import org.junit.Test;

import wordcount.exception.EmptyFilePathException;

/**
 * 
 * @author ALOK
 * 
 *         NOTE- Below tests are as of now executed on real DB(As I am newbie on
 *         mongodb and I have developed this application basis of these tests).
 *         I will mocked the DB connections and other real DB interaction later.
 * 
 *         Purpose of this class is to check database connection with correct DB
 *         info and the last test ensure that every thing is working fine from
 *         end to end flow.
 *
 */
public class WordCountHandlerTest {

	@Test(expected = FileNotFoundException.class)
	public void shouldThrowFileNotFoundExceptionWhenFilePathNotFound() throws Exception {
		WordCountHandler wordCount = new WordCountHandler(
				new InputArguments("JonSnow.txt", new DataBaseDetail("localhost", 27017, "WordCountStoreTest")));
		wordCount.getTokensFromFileAndStoreInDB();
	}

	@Test(expected = EmptyFilePathException.class)
	public void shouldThrowExceptionWhenNullFileNameProvide() throws Exception {

		WordCountHandler wordCount = new WordCountHandler(
				new InputArguments(null, new DataBaseDetail("localhost", 27017, "WordCountStoreTest")));
		wordCount.getTokensFromFileAndStoreInDB();
	}

	@Test
	public void getTokensFromFileAndStoreInDBTest() throws Exception {
		WordCountHandler wordCount = new WordCountHandler(new InputArguments("src/main/resources/names.txt",
				new DataBaseDetail("localhost", 27017, "WordCountStoreTest")));
		wordCount.getTokensFromFileAndStoreInDB();
	}

}
