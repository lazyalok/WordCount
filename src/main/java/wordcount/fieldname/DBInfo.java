package wordcount.fieldname;

public enum DBInfo {

	DB_NAME("WordCountStore"),
	COLLECTION_NAME("WordCount");

	public String name;

	DBInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
