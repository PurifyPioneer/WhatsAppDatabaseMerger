package database;

public class ReceiptsEntry {

	private int _id;
	private String key_remote_jid;
	private String key_id;
	private String remote_resource;
	private long receipt_device_timestamp;
	private long read_device_timestamp;
	private long played_device_timestamp;

	public ReceiptsEntry(int _id, String key_remote_jid, String key_id, String remote_resource, int receipt_device_timestamp,
			int read_device_timestamp, int played_device_timestamp) {
		
		set_id(_id);
		setKey_remote_jid(key_remote_jid);
		setKey_id(key_id);
		setRemote_resource(remote_resource);
		setReceipt_device_timestamp(receipt_device_timestamp);
		setRead_device_timestamp(read_device_timestamp);
		setPlayed_device_timestamp(played_device_timestamp);
		
	}
	
	public int get_id() {
		return _id;
	}

	private void set_id(int _id) {
		this._id = _id;
	}

	public String getKey_remote_jid() {
		return key_remote_jid;
	}

	private void setKey_remote_jid(String key_remote_jid) {
		this.key_remote_jid = key_remote_jid;
	}

	public String getKey_id() {
		return key_id;
	}

	private void setKey_id(String key_id) {
		this.key_id = key_id;
	}

	public String getRemote_resource() {
		return remote_resource;
	}

	private void setRemote_resource(String remote_resource) {
		this.remote_resource = remote_resource;
	}

	public long getReceipt_device_timestamp() {
		return receipt_device_timestamp;
	}

	private void setReceipt_device_timestamp(long receipt_device_timestamp) {
		this.receipt_device_timestamp = receipt_device_timestamp;
	}

	public long getRead_device_timestamp() {
		return read_device_timestamp;
	}

	private void setRead_device_timestamp(long read_device_timestamp) {
		this.read_device_timestamp = read_device_timestamp;
	}

	public long getPlayed_device_timestamp() {
		return played_device_timestamp;
	}

	private void setPlayed_device_timestamp(long played_device_timestamp) {
		this.played_device_timestamp = played_device_timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + ((key_id == null) ? 0 : key_id.hashCode());
		result = prime * result
				+ ((key_remote_jid == null) ? 0 : key_remote_jid.hashCode());
		result = prime
				* result
				+ (int) (played_device_timestamp ^ (played_device_timestamp >>> 32));
		result = prime
				* result
				+ (int) (read_device_timestamp ^ (read_device_timestamp >>> 32));
		result = prime
				* result
				+ (int) (receipt_device_timestamp ^ (receipt_device_timestamp >>> 32));
		result = prime * result
				+ ((remote_resource == null) ? 0 : remote_resource.hashCode());
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
		ReceiptsEntry other = (ReceiptsEntry) obj;
		if (_id != other._id)
			return false;
		if (key_id == null) {
			if (other.key_id != null)
				return false;
		} else if (!key_id.equals(other.key_id))
			return false;
		if (key_remote_jid == null) {
			if (other.key_remote_jid != null)
				return false;
		} else if (!key_remote_jid.equals(other.key_remote_jid))
			return false;
		if (played_device_timestamp != other.played_device_timestamp)
			return false;
		if (read_device_timestamp != other.read_device_timestamp)
			return false;
		if (receipt_device_timestamp != other.receipt_device_timestamp)
			return false;
		if (remote_resource == null) {
			if (other.remote_resource != null)
				return false;
		} else if (!remote_resource.equals(other.remote_resource))
			return false;
		return true;
	}
	
}
