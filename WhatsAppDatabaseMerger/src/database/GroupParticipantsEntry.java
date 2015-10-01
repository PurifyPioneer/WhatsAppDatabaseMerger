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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + admin;
		result = prime * result + ((gjid == null) ? 0 : gjid.hashCode());
		result = prime * result + ((jid == null) ? 0 : jid.hashCode());
		result = prime * result + pending;
		result = prime * result + sent_sender_key;
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
		GroupParticipantsEntry other = (GroupParticipantsEntry) obj;
		if (_id != other._id)
			return false;
		if (admin != other.admin)
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
		if (pending != other.pending)
			return false;
		if (sent_sender_key != other.sent_sender_key)
			return false;
		return true;
	}
	
}
