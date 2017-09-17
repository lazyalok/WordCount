package wordcount.exception;

import com.mongodb.MongoClientException;

public class DatabaseConnectionException extends MongoClientException{

	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException(String msg) {
		super(msg);
	}
}
