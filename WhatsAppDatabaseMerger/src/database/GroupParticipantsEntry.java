package database;

public class GroupParticipantsEntry {

	private int _id;
	private String gjid;
	private String jid;
	private int admin;
	private int pending;
	private int sent_sender_key;
	
	public GroupParticipantsEntry(int _id, String gjid, String jid, int admin, int pending, int sent_sender_key) {
		
		set_id(_id);
		setGjid(gjid);
		setJid(jid);
		setAdmin(admin);
		setPending(pending);
		setSent_sender_key(sent_sender_key);
		
	}
	
	public int get_id() {
		return _id;
	}

	private void set_id(int _id) {
		this._id = _id;
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

	public int getAdmin() {
		return admin;
	}

	private void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getPending() {
		return pending;
	}

	private void setPending(int pending) {
		this.pending = pending;
	}

	public int getSent_sender_key() {
		return sent_sender_key;
	}

	private void setSent_sender_key(int sent_sender_key) {
		this.sent_sender_key = sent_sender_key;
	}
	
	public boolean compare(GroupParticipantsEntry groupParticipantsEntry) {
		return false;
	}
	
}
