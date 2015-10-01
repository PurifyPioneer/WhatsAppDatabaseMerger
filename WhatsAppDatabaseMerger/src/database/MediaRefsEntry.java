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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ref_count;
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
		MediaRefsEntry other = (MediaRefsEntry) obj;
		if (_id != other._id)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (ref_count != other.ref_count)
			return false;
		return true;
	}
	
	
}
