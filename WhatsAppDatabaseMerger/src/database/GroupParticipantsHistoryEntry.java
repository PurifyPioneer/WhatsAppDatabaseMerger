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
	
}
