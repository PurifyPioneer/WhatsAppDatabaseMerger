package core;

/**
 * The representation of an whatsapp
 * message.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class Message {
	
	private boolean messageAlreadyChecked = false;

	private int _id;
	private String key_remote_jid;
	private int key_from_me;
	private String key_id;
	private int status;
	private int needs_push;
	private String data;
	private long timeStamp; // TIMESTAMP
	private String media_url;
	private String media_mime_type;
	private int media_wa_type;
	private int media_size;
	private String media_name;
	private float latitude; // GPS-COORDINATE
	private float longitude; // GPS-COORDINATE
	private String thumb_image;
	private String remote_resource;
	private long received_timeStamp; // TIMESTAMP
	private long send_timeStamp; // TIMESTAMP
	private long receipt_server_timeStamp; // TIMESTAMP
	private long receipt_device_timeStamp; // TIMESTAMP
	private String raw_data;
	private String media_hash;
	private int recipient_count;
	private int media_duration;
	private int origin;
	private long read_device_timeStamp; // TIMESTAMP
	private long played_device_timeStamp; // TIMESTAMP
	private String media_caption;
	private String participant_hash;

	public Message(int _id, String key_remote_jid, int key_from_me, String key_id, int status,
			int needs_push, String data, long timeStamp, String media_url, String media_mime_type, 
			int media_wa_type, int media_size, String media_name, float latitude, float longitude, String thumb_image, String remote_resource,
			long received_timeStamp, long send_timeStamp, long receipt_server_timeStamp, long receipt_device_timeStamp,
			String raw_data, String media_hash, int recipient_count, int media_duaration, int origin, long read_device_timeStamp,
			long played_device_timeStamp, String media_caption, String participant_hash) {
		
		set_id(_id);
		setKey_remote_jid(key_remote_jid);
		setKey_from_me(key_from_me);
		setKey_id(key_id);
		setStatus(status);
		setNeeds_push(needs_push);
		setData(raw_data);
		setTimeStamp(timeStamp);
		setMedia_url(media_url);
		setMedia_mime_type(media_mime_type);
		setMedia_wa_type(media_wa_type);
		setMedia_size(media_size);
		setMedia_name(media_name);
		setLatitude(latitude);
		setLongitude(longitude);
		setThumb_image(thumb_image);
		setRemote_resource(remote_resource);
		setreceived_timeStamp(received_timeStamp);
		setSend_timeStamp(send_timeStamp);
		setReceipt_server_timeStamp(receipt_server_timeStamp);
		setReceipt_device_timeStamp(receipt_device_timeStamp);
		setRaw_data(raw_data);
		setMedia_hash(media_hash);
		setrecipient_count(recipient_count);
		setMedia_duration(media_size);
		setOrigin(origin);
		setRead_device_timeStamp(read_device_timeStamp);
		setPlayed_device_timeStamp(played_device_timeStamp);
		setMedia_caption(media_caption);
		setparticipant_hash(participant_hash);

	}
	
	
	// GETTER AND SETTER ////////////////////////////////////////////////////////////////
	public boolean getMessageAlreadyChecked() {
		return messageAlreadyChecked;
	}
	
	public void setMessageAlreadyChecked(boolean messageAlreadyChecked) {
		this.messageAlreadyChecked = messageAlreadyChecked;
	}

	public int get_id() {
		return _id;
	}

	private void set_id(int _id) {
		this._id = _id;
	}
	
	public void increment_id() {
		set_id(get_id() + 1);
	}

	public String getKey_remote_jid() {
		return key_remote_jid;
	}

	private void setKey_remote_jid(String key_remote_jid) {
		this.key_remote_jid = key_remote_jid;
	}

	public int getKey_from_me() {
		return key_from_me;
	}

	private void setKey_from_me(int key_from_me) {
		this.key_from_me = key_from_me;
	}

	public String getKey_id() {
		return key_id;
	}

	private void setKey_id(String key_id) {
		this.key_id = key_id;
	}

	public int getStatus() {
		return status;
	}

	private void setStatus(int status) {
		this.status = status;
	}

	public int getNeeds_push() {
		return needs_push;
	}

	private void setNeeds_push(int needs_push) {
		this.needs_push = needs_push;
	}

	public String getData() {
		return data;
	}

	private void setData(String data) {
		this.data = data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	private void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMedia_url() {
		return media_url;
	}

	private void setMedia_url(String media_url) {
		this.media_url = media_url;
	}

	public String getMedia_mime_type() {
		return media_mime_type;
	}

	private void setMedia_mime_type(String media_mime_type) {
		this.media_mime_type = media_mime_type;
	}

	public int getMedia_wa_type() {
		return media_wa_type;
	}

	private void setMedia_wa_type(int media_wa_type) {
		this.media_wa_type = media_wa_type;
	}

	public int getMedia_size() {
		return media_size;
	}

	private void setMedia_size(int media_size) {
		this.media_size = media_size;
	}

	public String getMedia_name() {
		return media_name;
	}

	private void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public float getLatitude() {
		return latitude;
	}

	private void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	private void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getThumb_image() {
		return thumb_image;
	}

	private void setThumb_image(String thumb_image) {
		this.thumb_image = thumb_image;
	}

	public String getRemote_resource() {
		return remote_resource;
	}

	private void setRemote_resource(String remote_resource) {
		this.remote_resource = remote_resource;
	}

	public long getreceived_timeStamp() {
		return received_timeStamp;
	}

	private void setreceived_timeStamp(long received_timeStamp) {
		this.received_timeStamp = received_timeStamp;
	}

	public long getSend_timeStamp() {
		return send_timeStamp;
	}

	private void setSend_timeStamp(long send_timeStamp) {
		this.send_timeStamp = send_timeStamp;
	}

	public long getReceipt_server_timeStamp() {
		return receipt_server_timeStamp;
	}

	private void setReceipt_server_timeStamp(long receipt_server_timeStamp) {
		this.receipt_server_timeStamp = receipt_server_timeStamp;
	}

	public long getReceipt_device_timeStamp() {
		return receipt_device_timeStamp;
	}

	private void setReceipt_device_timeStamp(long receipt_device_timeStamp) {
		this.receipt_device_timeStamp = receipt_device_timeStamp;
	}

	public String getRaw_data() {
		return raw_data;
	}

	private void setRaw_data(String raw_data) {
		this.raw_data = raw_data;
	}

	public String getMedia_hash() {
		return media_hash;
	}

	private void setMedia_hash(String media_hash) {
		this.media_hash = media_hash;
	}

	public int getrecipient_count() {
		return recipient_count;
	}

	private void setrecipient_count(int recipient_count) {
		this.recipient_count = recipient_count;
	}

	public int getMedia_duration() {
		return media_duration;
	}

	private void setMedia_duration(int media_duration) {
		this.media_duration = media_duration;
	}

	public int getOrigin() {
		return origin;
	}

	private void setOrigin(int origin) {
		this.origin = origin;
	}

	public long getRead_device_timeStamp() {
		return read_device_timeStamp;
	}

	private void setRead_device_timeStamp(long read_device_timeStamp) {
		this.read_device_timeStamp = read_device_timeStamp;
	}

	public long getPlayed_device_timeStamp() {
		return played_device_timeStamp;
	}

	private void setPlayed_device_timeStamp(long played_device_timeStamp) {
		this.played_device_timeStamp = played_device_timeStamp;
	}

	public String getMedia_caption() {
		return media_caption;
	}

	private void setMedia_caption(String media_caption) {
		this.media_caption = media_caption;
	}

	public String getparticipant_hash() {
		return participant_hash;
	}

	private void setparticipant_hash(String participant_hash) {
		this.participant_hash = participant_hash;
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	public boolean compare(Message message) {
		//TODO Comparison has to be exact.. hashWert // hashwert schnell und exakt
		if (!((this.getTimeStamp() == message.getTimeStamp()) && (this.getData().equalsIgnoreCase(message.getData())))) {
			return true;
		} else if (!((this.getTimeStamp() == message.getTimeStamp()) && (this.getMedia_url().equalsIgnoreCase(message.getMedia_url())))) {
			return true;
		} else if (!((this.getTimeStamp() == message.getTimeStamp()) && (this.getKey_id().equalsIgnoreCase(message.getKey_id())))) {
			return true;
		}
		
		return false;
	}

}
