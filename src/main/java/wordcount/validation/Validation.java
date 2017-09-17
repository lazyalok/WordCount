package wordcount.validation;

import static wordcount.fieldname.DBInfo.DB_NAME;

import java.util.logging.Logger;

import wordcount.DataBaseDetail;
import wordcount.InputArguments;
import wordcount.exception.ArgumentMissingException;
import wordcount.exception.InvalidFileExtensionException;

public class Validation {

	private static final Logger LOGGER = Logger.getLogger(Validation.class.getName());

	public static InputArguments validateProvidedArgumentsAndReturnInputArgument(String[] args) throws Exception {

		printAllArguments(args);

		if (args.length != 3) {
			LOGGER.severe("Input arguments are invalid: " + args);
			throw new ArgumentMissingException(
					"Please check provided arguments.Three arguments are required fileName, hostName and port."
							+ " Command line arguments should look like:"
							+ " java –Xmx8192m -jar C:\\challenge.jar C:\\dump.txt localhost 27017");
		}

		return arguments(args);

	}

	private static void printAllArguments(String[] args) {
		int i = 0;
		for (String argument : args) {
			LOGGER.info("Input argument " + i++ + " : " + argument);
		}
	}

	private static InputArguments arguments(String[] args) throws InvalidFileExtensionException {
		String fileName = args[0];
		String host = args[1];
		String port = args[2];

		validateFile(fileName);

		return inputArguments(fileName, host, port);
	}

	private static void validateFile(String fileName) throws InvalidFileExtensionException {
		if (!isTxtFile(fileName)) {
			LOGGER.severe("File extension is not txt.");
			throw new InvalidFileExtensionException("Please use txt file extension.");
		}
	}

	private static boolean isTxtFile(String fileName) {
		int i = fileName.lastIndexOf('.');

		if (i > 0) {
			String extension = fileName.substring(i + 1);

			if (extension.equalsIgnoreCase("txt")) {
				return true;
			}
		}
		return false;
	}

	private static InputArguments inputArguments(String fileName, String hostName, String port) {
		return new InputArguments(fileName, new DataBaseDetail(hostName, Integer.valueOf(port), DB_NAME.getName()));
	}
}
