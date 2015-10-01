package database;

public class ChatListEntry{

	private int _id;
	private String key_remote_jid;
	private int message_table_id;
	private String subject;
	private int creation;
	private int last_read_table_id;
	private int last_read_receipt_sent_messages_table_id;
	private int archived;
	private int sort_timestamp;
	private int mod_tag;
	private double gen;
	private int my_messages;
	
	public ChatListEntry(int _id, String key_remote_jid, int message_table_id, String subject, int creation, int last_read_table_id, 
			int last_read_receipt_sent_messages_table_id, int archived, int sort_timestamp, int mod_tag, double gen, int my_messages) {
		
			set_id(_id);
			setKey_remote_jid(key_remote_jid);
			setMessage_table_id(message_table_id);
			setSubject(subject);
			setCreation(creation);
			setLast_read_table_id(last_read_table_id);
			setLast_read_receipt_sent_messages_table_id(last_read_receipt_sent_messages_table_id);
			setArchived(archived);
			setSort_timestamp(sort_timestamp);
			setMod_tag(mod_tag);
			setGen(gen);
			setMy_messages(my_messages);

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

	public int getMessage_table_id() {
		return message_table_id;
	}

	private void setMessage_table_id(int message_table_id) {
		this.message_table_id = message_table_id;
	}

	public String getSubject() {
		return subject;
	}

	private void setSubject(String subject) {
		this.subject = subject;
	}

	public int getCreation() {
		return creation;
	}

	private void setCreation(int creation) {
		this.creation = creation;
	}

	public int getLast_read_table_id() {
		return last_read_table_id;
	}

	private void setLast_read_table_id(int last_read_table_id) {
		this.last_read_table_id = last_read_table_id;
	}

	public int getLast_read_receipt_sent_messages_table_id() {
		return last_read_receipt_sent_messages_table_id;
	}

	private void setLast_read_receipt_sent_messages_table_id(int last_read_receipt_sent_messages_table_id) {
		this.last_read_receipt_sent_messages_table_id = last_read_receipt_sent_messages_table_id;
	}

	public int getArchived() {
		return archived;
	}

	private void setArchived(int archived) {
		this.archived = archived;
	}

	public int getSort_timestamp() {
		return sort_timestamp;
	}

	private void setSort_timestamp(int sort_timestamp) {
		this.sort_timestamp = sort_timestamp;
	}

	public int getMod_tag() {
		return mod_tag;
	}

	private void setMod_tag(int mod_tag) {
		this.mod_tag = mod_tag;
	}

	public double getGen() {
		return gen;
	}

	private void setGen(double gen) {
		this.gen = gen;
	}

	public int getMy_messages() {
		return my_messages;
	}

	private void setMy_messages(int my_messages) {
		this.my_messages = my_messages;
	}

	public boolean compare(ChatListEntry chatListEntry) {
		//TODO
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + archived;
		result = prime * result + creation;
		long temp;
		temp = Double.doubleToLongBits(gen);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((key_remote_jid == null) ? 0 : key_remote_jid.hashCode());
		result = prime * result + last_read_receipt_sent_messages_table_id;
		result = prime * result + last_read_table_id;
		result = prime * result + message_table_id;
		result = prime * result + mod_tag;
		result = prime * result + my_messages;
		result = prime * result + sort_timestamp;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		ChatListEntry other = (ChatListEntry) obj;
		if (_id != other._id)
			return false;
		if (archived != other.archived)
			return false;
		if (creation != other.creation)
			return false;
		if (Double.doubleToLongBits(gen) != Double.doubleToLongBits(other.gen))
			return false;
		if (key_remote_jid == null) {
			if (other.key_remote_jid != null)
				return false;
		} else if (!key_remote_jid.equals(other.key_remote_jid))
			return false;
		if (last_read_receipt_sent_messages_table_id != other.last_read_receipt_sent_messages_table_id)
			return false;
		if (last_read_table_id != other.last_read_table_id)
			return false;
		if (message_table_id != other.message_table_id)
			return false;
		if (mod_tag != other.mod_tag)
			return false;
		if (my_messages != other.my_messages)
			return false;
		if (sort_timestamp != other.sort_timestamp)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
}
