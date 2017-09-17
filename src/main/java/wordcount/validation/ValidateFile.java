package wordcount.validation;

import java.io.File;
import java.io.FileNotFoundException;

import wordcount.exception.EmptyFilePathException;

public final class ValidateFile {

	public static void validateFile(String fileName) throws EmptyFilePathException, FileNotFoundException {
		if (fileName == null) {
			throw new EmptyFilePathException("Empty file name is not permitted.Please provide file path and name.");
		}

		File file = getFile(fileName);

		if (!file.exists()) {
			throw new FileNotFoundException("Please check file path.Seems file path is not correct.");
		}
	}

	public static File getFile(String fileName) {
		File file = new File(fileName);
		return file;
	}
}
