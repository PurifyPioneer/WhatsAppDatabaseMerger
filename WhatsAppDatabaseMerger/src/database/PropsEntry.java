package database;

public class PropsEntry {

	private int _id;
	private String key;
	private String value;
	
	public PropsEntry(int _id, String key, String value) {
		
		set_id(_id);
		setKey(key);
		setValue(value);
		
	}

	public int get_id() {
		return _id;
	}

	private void set_id(int _id) {
		this._id = _id;
	}

	public String getKey() {
		return key;
	}

	private void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	private void setValue(String value) {
		this.value = value;
	}
	
}
