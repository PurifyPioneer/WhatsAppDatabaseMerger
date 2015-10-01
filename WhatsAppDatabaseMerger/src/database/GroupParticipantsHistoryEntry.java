package database;

public class GroupParticipantsHistoryEntry {

	private int _id;
	private int timestamp; 
	private String gjid; 
	private String jid; 
	private int action;
	private String old_phash;
	private String new_pash;
	
	public GroupParticipantsHistoryEntry(int _id, int timestamp, String gjid, String jid, int action,
			String old_phash, String new_pash) {
		
		set_id(_id);
		setTimestamp(timestamp);
		setGjid(gjid);
		setJid(jid);
		setAction(action);
		setOld_phash(old_phash);
		setNew_pash(new_pash);
		
	}

	public int get_id() {
		return _id;
	}

	private void set_id(int _id) {
		this._id = _id;
	}

	public int getTimestamp() {
		return timestamp;
	}

	private void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getGjid() {
		return gjid;
	}

	private void setGjid(String gjid) {
		this.gjid = gjid;
	}

	public String getJid() {
		return jid;
	}

	private void setJid(String jid) {
		this.jid = jid;
	}

	public int getAction() {
		return action;
	}

	private void setAction(int action) {
		this.action = action;
	}

	public String getOld_phash() {
		return old_phash;
	}

	private void setOld_phash(String old_phash) {
		this.old_phash = old_phash;
	}

	public String getNew_pash() {
		return new_pash;
	}

	private void setNew_pash(String new_pash) {
		this.new_pash = new_pash;
	}

	public boolean compare(GroupParticipantsHistoryEntry groupParticipantsHistoryEntry) {
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + action;
		result = prime * result + ((gjid == null) ? 0 : gjid.hashCode());
		result = prime * result + ((jid == null) ? 0 : jid.hashCode());
		result = prime * result
				+ ((new_pash == null) ? 0 : new_pash.hashCode());
		result = prime * result
				+ ((old_phash == null) ? 0 : old_phash.hashCode());
		result = prime * result + timestamp;
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
		GroupParticipantsHistoryEntry other = (GroupParticipantsHistoryEntry) obj;
		if (_id != other._id)
			return false;
		if (action != other.action)
			return false;
		if (gjid == null) {
			if (other.gjid != null)
				return false;
		} else if (!gjid.equals(other.gjid))
			return false;
		if (jid == null) {
			if (other.jid != null)
				return false;
		} else if (!jid.equals(other.jid))
			return false;
		if (new_pash == null) {
			if (other.new_pash != null)
				return false;
		} else if (!new_pash.equals(other.new_pash))
			return false;
		if (old_phash == null) {
			if (other.old_phash != null)
				return false;
		} else if (!old_phash.equals(other.old_phash))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}
	
}
