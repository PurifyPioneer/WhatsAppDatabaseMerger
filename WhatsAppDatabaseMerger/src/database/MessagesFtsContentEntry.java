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
	
}
