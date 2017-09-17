package wordcount.exception;

public class InvalidFileExtensionException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidFileExtensionException(String msg) {
		super(msg);
	}
}
