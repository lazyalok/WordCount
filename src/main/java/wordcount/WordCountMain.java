package wordcount;

import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import wordcount.fieldname.Fields;
import wordcount.validation.Validation;

public class WordCountMain {

	private static final Logger LOGGER = Logger.getLogger(WordCountMain.class.getName());

	public static void main(String[] args) throws Exception {

		LOGGER.info("---------------- processing Started.........................");

		WordCountHandler wordCountHandler = new WordCountHandler(
				Validation.validateProvidedArgumentsAndReturnInputArgument(args));
		wordCountHandler.getTokensFromFileAndStoreInDB();

		printMaxCountReportChartToConsole(wordCountHandler);
		printMinCountReportChartToConsole(wordCountHandler);

		LOGGER.info("---------------- processing Completed.........................");

	}

	private static void printMaxCountReportChartToConsole(WordCountHandler wordCountHandler) throws Exception {
		FindIterable<Document> documents = wordCountHandler.showReportForDocumentsHavingMaximumTotalCount();

		System.out.println();
		System.out.println(
				"<-----------------------------------Max Word Count Report Chart----------------------------------->");
		System.out.println(" Word Name                  ||              TotalCount                  ");
		for (Document document : documents) {
			System.out.println(document.getString(Fields.WORD_NAME.getName()) + "\t \t \t \t \t \t"
					+ document.getInteger(Fields.TOTAL_COUNT.getName()));
		}
		System.out.println();
	}

	private static void printMinCountReportChartToConsole(WordCountHandler wordCountHandler) throws Exception {
		FindIterable<Document> documents = wordCountHandler.showReportForDocumentsHavingMinimumTotalCount();

		System.out.println(
				"<-----------------------------------Min Word Count Report Chart----------------------------------->");
		System.out.println(" Word Name                  ||              TotalCount                  ");
		for (Document document : documents) {
			System.out.println(document.getString(Fields.WORD_NAME.getName()) + "\t \t \t \t \t \t "
					+ document.getInteger(Fields.TOTAL_COUNT.getName()));
		}
		System.out.println();
		System.out.println(
				"<------------------------------------------------------------------------------------------------->");
	}

}
