package database;

public class SqliteSequenceEntry {

	private String name;
	private String seq;
	
	public SqliteSequenceEntry(String name, String seq) {
		
		setName(name);
		setSeq(seq);
		
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getSeq() {
		return seq;
	}

	private void setSeq(String seq) {
		this.seq = seq;
	}
	
}
