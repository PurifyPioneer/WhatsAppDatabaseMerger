package database;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

/**
 * The representation of an whatsapp message.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class MessagesEntry {

	private boolean messageAlreadyChecked = false;

	// GET TYPE FROM SQL CREATE STAEMENT !
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
	private String media_wa_type;
	private int media_size;
	private String media_name;
	private double latitude; // GPS-COORDINATE
	private double longitude; // GPS-COORDINATE
	private String thumb_image;
	private String remote_resource;
	private long received_timeStamp; // TIMESTAMP
	private long send_timeStamp; // TIMESTAMP
	private long receipt_server_timeStamp; // TIMESTAMP
	private long receipt_device_timeStamp; // TIMESTAMP
	private Blob raw_data;
	private String media_hash;
	private int recipient_count;
	private int media_duration;
	private int origin;
	private long read_device_timeStamp; // TIMESTAMP
	private long played_device_timeStamp; // TIMESTAMP
	private String media_caption;
	private String participant_hash;

	public MessagesEntry(int _id, String key_remote_jid, int key_from_me, String key_id, int status, int needs_push,
			String data, long timeStamp, String media_url, String media_mime_type, String media_wa_type, int media_size,
			String media_name, double latitude, double longitude, String thumb_image, String remote_resource,
			long received_timeStamp, long send_timeStamp, long receipt_server_timeStamp, long receipt_device_timeStamp,
			byte[] raw_data, String media_hash, int recipient_count, int media_duaration, int origin,
			long read_device_timeStamp, long played_device_timeStamp, String media_caption, String participant_hash) {

		set_id(_id);
		setKey_remote_jid(key_remote_jid);
		setKey_from_me(key_from_me);
		setKey_id(key_id);
		setStatus(status);
		setNeeds_push(needs_push);
		setData(data);
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
		setMedia_duration(media_duaration);
		setOrigin(origin);
		setRead_device_timeStamp(read_device_timeStamp);
		setPlayed_device_timeStamp(played_device_timeStamp);
		setMedia_caption(media_caption);
		setparticipant_hash(participant_hash);

	}

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

	//FIX PUBLIC PRIVATE IF POSSIBLE
	public void setKey_id(String key_id) {
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

	public String getMedia_wa_type() {
		return media_wa_type;
	}

	private void setMedia_wa_type(String media_wa_type) {
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

	public Double getLatitude() {
		return latitude;
	}

	private void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	private void setLongitude(double longitude) {
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

	public Blob getRaw_data() {
		return raw_data;
	}

	private void setRaw_data(byte[] raw_data) {
		if (raw_data != null) {
			try {
				this.raw_data = new SerialBlob(raw_data);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

	/**
	 * This method checks if a message is contained in a specific database.
	 * returns true if so and also marks the message as checked.
	 * 
	 * @param database
	 *            to check if message is in this specific database
	 * @return
	 */
	public boolean existsInDatabase(int index, ArrayList<MessagesEntry> database) {

		if (index >= database.size()) {
			throw new NumberFormatException("Index cant be greater than database size.");
		}
		
		for (int i = index; i < database.size(); i++) {
			if (this.equals(database.get(i))) {
				database.get(i).setMessageAlreadyChecked(true);
				return true;
			}
		}
		for (int i = 0; i < index; i++) {
			if (this.equals(database.get(i))) {
				database.get(i).setMessageAlreadyChecked(true);
				return true;
			}
		}

		return false;
	}
	
	public boolean existsInDatabase(ArrayList<MessagesEntry> database) {
		
		for (int i = 0; i < database.size(); i++) {
			if (this.equals(database.get(i))) {
				database.get(i).setMessageAlreadyChecked(true);
				return true;
			}
		}
		return false;
	}
	
	public int existsInDatabaseCount(ArrayList<MessagesEntry> database) {

		int foundCounter = 0;
		
		for (int i = 0; i < database.size(); i++) {
			if (this.equals(database.get(i))) {
				database.get(i).setMessageAlreadyChecked(true);
				foundCounter++;
			}
		}

		return foundCounter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + key_from_me;
		result = prime * result + ((key_id == null) ? 0 : key_id.hashCode());
		result = prime * result + ((key_remote_jid == null) ? 0 : key_remote_jid.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((media_caption == null) ? 0 : media_caption.hashCode());
		result = prime * result + media_duration;
		result = prime * result + ((media_hash == null) ? 0 : media_hash.hashCode());
		result = prime * result + ((media_mime_type == null) ? 0 : media_mime_type.hashCode());
		result = prime * result + ((media_name == null) ? 0 : media_name.hashCode());
		result = prime * result + media_size;
		result = prime * result + ((media_url == null) ? 0 : media_url.hashCode());
		result = prime * result + ((media_wa_type == null) ? 0 : media_wa_type.hashCode());
		// MEssage already checked darf kein indikator sein ! result = prime *
		// result + (messageAlreadyChecked ? 1231 : 1237);
		result = prime * result + needs_push;
		result = prime * result + origin;
		result = prime * result + ((participant_hash == null) ? 0 : participant_hash.hashCode());
		result = prime * result + (int) (played_device_timeStamp ^ (played_device_timeStamp >>> 32));
		result = prime * result + ((raw_data == null) ? 0 : raw_data.hashCode());
		result = prime * result + (int) (read_device_timeStamp ^ (read_device_timeStamp >>> 32));
		result = prime * result + (int) (receipt_device_timeStamp ^ (receipt_device_timeStamp >>> 32));
		result = prime * result + (int) (receipt_server_timeStamp ^ (receipt_server_timeStamp >>> 32));
		result = prime * result + (int) (received_timeStamp ^ (received_timeStamp >>> 32));
		result = prime * result + recipient_count;
		result = prime * result + ((remote_resource == null) ? 0 : remote_resource.hashCode());
		result = prime * result + (int) (send_timeStamp ^ (send_timeStamp >>> 32));
		result = prime * result + status;
		result = prime * result + ((thumb_image == null) ? 0 : thumb_image.hashCode());
		result = prime * result + (int) (timeStamp ^ (timeStamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MessagesEntry other = (MessagesEntry) obj;
		// id should not be indicator
		// if (_id != other._id) {
		// return false;
		// }
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (key_from_me != other.key_from_me) {
			return false;
		}

		if (key_id == null) {
			if (other.key_id != null) {
				return false;
			}
		} else if (!key_id.equals(other.key_id)) {
			return false;
		}
		if (key_remote_jid == null) {
			if (other.key_remote_jid != null) {
				return false;
			}
		} else if (!key_remote_jid.equals(other.key_remote_jid)) {
			return false;
		}

		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude)) {
			return false;
		}
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude)) {
			return false;
		}
		if (media_caption == null) {
			if (other.media_caption != null) {
				return false;
			}
		} else if (!media_caption.equals(other.media_caption)) {
			return false;
		}
		if (media_duration != other.media_duration) {
			return false;
		}
		if (media_hash == null) {
			if (other.media_hash != null) {
				return false;
			}
		} else if (!media_hash.equals(other.media_hash)) {
			return false;
		}
		if (media_mime_type == null) {
			if (other.media_mime_type != null) {
				return false;
			}
		} else if (!media_mime_type.equals(other.media_mime_type)) {
			return false;
		}
		if (media_name == null) {
			if (other.media_name != null) {
				return false;
			}
		} else if (!media_name.equals(other.media_name)) {
			return false;
		}
		if (media_size != other.media_size) {
			return false;
		}
		if (media_url == null) {
			if (other.media_url != null) {
				return false;
			}
		} else if (!media_url.equals(other.media_url)) {
			return false;
		}
		if (media_wa_type == null) {
			if (other.media_wa_type != null) {
				return false;
			}
		} else if (!media_wa_type.equals(other.media_wa_type)) {
			return false;
		}
		if (needs_push != other.needs_push) {
			return false;
		}
		if (origin != other.origin) {
			return false;
		}
		if (participant_hash == null) {
			if (other.participant_hash != null) {
				return false;
			}
		} else if (!participant_hash.equals(other.participant_hash)) {
			return false;
		}
		if (played_device_timeStamp != other.played_device_timeStamp) {
			return false;
		}
		if (raw_data == null) {
			if (other.raw_data != null) {
				return false;
			}
		} else if (!raw_data.equals(other.raw_data)) {
			return false;
		}
		if (read_device_timeStamp != other.read_device_timeStamp) {
			return false;
		}
		if (receipt_device_timeStamp != other.receipt_device_timeStamp) {
			return false;
		}
		if (receipt_server_timeStamp != other.receipt_server_timeStamp) {
			return false;
		}
		if (received_timeStamp != other.received_timeStamp) {
			return false;
		}
		if (recipient_count != other.recipient_count) {
			return false;
		}
		if (remote_resource == null) {
			if (other.remote_resource != null) {
				return false;
			}
		} else if (!remote_resource.equals(other.remote_resource)) {
			return false;
		}
		if (send_timeStamp != other.send_timeStamp) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (thumb_image == null) {
			if (other.thumb_image != null) {
				return false;
			}
		} else if (!thumb_image.equals(other.thumb_image)) {
			return false;
		}
		if (timeStamp != other.timeStamp) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MessagesEntry [\n messageAlreadyChecked=" + messageAlreadyChecked
				+ ",\n _id=" + _id + ",\n key_remote_jid=" + key_remote_jid + ",\n key_from_me=" + key_from_me
				+ ",\n key_id=" + key_id + ",\n status=" + status + ",\n needs_push=" + needs_push + ",\n data=" + data
				+ ",\n timeStamp=" + timeStamp + ",\n media_url=" + media_url + ",\n media_mime_type=" + media_mime_type
				+ ",\n media_wa_type=" + media_wa_type + ",\n media_size=" + media_size + ",\n media_name=" + media_name
				+ ",\n latitude=" + latitude + ",\n longitude=" + longitude + ",\n thumb_image=" + thumb_image
				+ ",\n remote_resource=" + remote_resource + ",\n received_timeStamp=" + received_timeStamp
				+ ",\n send_timeStamp=" + send_timeStamp + ",\n receipt_server_timeStamp=" + receipt_server_timeStamp
				+ ",\n receipt_device_timeStamp=" + receipt_device_timeStamp + ",\n raw_data=" + raw_data
				+ ",\n media_hash=" + media_hash + ",\n recipient_count=" + recipient_count + ",\n media_duration="
				+ media_duration + ",\n origin=" + origin + ",\n read_device_timeStamp=" + read_device_timeStamp
				+ ",\n played_device_timeStamp=" + played_device_timeStamp + ",\n media_caption=" + media_caption
				+ ",\n participant_hash=" + participant_hash + " ]";
	}

}
