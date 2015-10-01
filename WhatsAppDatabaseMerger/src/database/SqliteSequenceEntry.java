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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SqliteSequenceEntry other = (SqliteSequenceEntry) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	
}
