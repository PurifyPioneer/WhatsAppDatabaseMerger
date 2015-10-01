package database;

public class MessagesFtsContentEntry {

	private int docid;
	private String c0content;
	
	public MessagesFtsContentEntry(int docid, String c0content) {
		
		setDocid(docid);
		setC0content(c0content);
		
	}

	public int getDocid() {
		return docid;
	}

	private void setDocid(int docid) {
		this.docid = docid;
	}

	public String getC0content() {
		return c0content;
	}

	private void setC0content(String c0content) {
		this.c0content = c0content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((c0content == null) ? 0 : c0content.hashCode());
		result = prime * result + docid;
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
		MessagesFtsContentEntry other = (MessagesFtsContentEntry) obj;
		if (c0content == null) {
			if (other.c0content != null)
				return false;
		} else if (!c0content.equals(other.c0content))
			return false;
		if (docid != other.docid)
			return false;
		return true;
	}
	
}
