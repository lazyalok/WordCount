package wordcount.exception;

public class EmptyFilePathException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyFilePathException(String msg) {
		super(msg);
	}

}
