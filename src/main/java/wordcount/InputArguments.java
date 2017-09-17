package wordcount;

public final class InputArguments {

	private final String fileName;
	private final DataBaseDetail dataBaseDetails;

	public InputArguments(String fileName, DataBaseDetail dataBaseDetails) {
		this.fileName = fileName;
		this.dataBaseDetails = dataBaseDetails;
	}

	public String getFileName() {
		return fileName;
	}

	public DataBaseDetail getDataBaseDetails() {
		return dataBaseDetails;
	}
	
	

}
