package database;

public class MediaRefsEntry {

	private int _id;
	private String path;
	private int ref_count;
	
	public MediaRefsEntry(int _id, String path, int ref_count) {
		
		set_id(_id);
		setPath(path);
		setRef_count(ref_count);
		
	}

	public int get_id() {
		return _id;
	}

	private void set_id(int _id) {
		this._id = _id;
	}

	public String getPath() {
		return path;
	}

	private void setPath(String path) {
		this.path = path;
	}

	public int getRef_count() {
		return ref_count;
	}

	private void setRef_count(int ref_count) {
		this.ref_count = ref_count;
	}
	
	
}
