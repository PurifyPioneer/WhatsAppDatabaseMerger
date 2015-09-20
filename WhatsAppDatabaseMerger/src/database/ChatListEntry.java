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
	
}
