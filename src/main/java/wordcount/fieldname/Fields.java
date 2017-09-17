package wordcount.fieldname;

public enum Fields {

	WORD_NAME("WordName"), INC("$inc"), TOTAL_COUNT("TotalCount");

	public String name;

	Fields(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
