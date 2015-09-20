package core;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.MessagesEntry;

/**
 * This class inserts the messages from the fixed database into the final file.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class DatabaseBuilder {

	//TODO EMPTY create new msgstore.db file
	// maybe create file from sql statements
	private File fixedDatabaseFile;
	
	private DatabaseContainer fixedDatabase;
	private Connection connection = null;

	public DatabaseBuilder(DatabaseContainer fixedDatabase) {

		//PUT DB IN TEMP FIRST .. ALWAYS NEW FILE .. THAN COPY TO OUT AND RENAME !
		
		File outputFolder = new File("Output");
		
		if (!(outputFolder.exists())) {
			outputFolder.mkdir();
			System.out.println("Output folder created.");
		}
		
		fixedDatabaseFile = new File("Output/msgstore.db");
		
		if (!(fixedDatabaseFile.exists())) {
			try {
				fixedDatabaseFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error while creating new datbase file");
			}
			System.out.println("New databasefile created.");		
		}

		setFixedDatabase(fixedDatabase);

		loadDriver();
		connectToDatabase(fixedDatabaseFile.getPath());
		fillNewTable();
		closeConnection();
	}

	private DatabaseContainer getFixedDatabase() {
		return fixedDatabase;
	}

	private void setFixedDatabase(DatabaseContainer fixedDatabase) {
		this.fixedDatabase = fixedDatabase;
	}

	private void fillNewTable() {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM messages WHERE _id != 1");

			// Eintr�ge aus fixed Tabelle in neue Tabelle eintragen

			ArrayList<MessagesEntry> messages = fixedDatabase.getMessages();

			System.out.println(fixedDatabase.getMessageCount());

			for (int i = 0; i < fixedDatabase.getMessageCount(); i++) {
				MessagesEntry m = messages.get(i);
				
				statement.executeUpdate("INSERT INTO messages ("
						+ "_id,"
						+ " key_remote_jid,"
						+ " key_from_me,"
						+ " key_id,"
						+ " status,"
						+ " needs_push,"
						+ " data,"
						+ " timestamp,"
						+ " media_url,"
						+ " media_mime_type,"
						+ " media_wa_type,"
						+ " media_size,"
						+ " media_name,"
						+ " latitude,"
						+ " longitude,"
						+ " thumb_image,"
						+ " remote_resource,"
						+ " received_timestamp,"
						+ " send_timestamp,"
						+ " receipt_server_timestamp,"
						+ " receipt_device_timestamp,"
						+ " raw_data,"
						+ " media_hash,"
						+ " recipient_count,"
						+ " media_duration,"
						+ " origin,"
						+ " read_device_timestamp,"
						+ " played_device_timestamp,"
						+ " media_caption,"
						+ " participant_hash) "
						
						+ "VALUES (" 
						+ Integer.toString(m.get_id()) + ",'" 
						+ m.getKey_remote_jid() + "',"
						+ Integer.toString(m.getKey_from_me()) + "," 
						+ m.getKey_id() + "," 
						+ Integer.toString(m.getStatus()) + "," 
						+ Integer.toString(m.getNeeds_push()) + ","
						+ m.getData() + "," 
						+ Long.toString(m.getTimeStamp()) + "," 
						+ m.getMedia_url() + "," 
						+ m.getMedia_mime_type() + "," 
						+ m.getMedia_wa_type() + "," 
						+ Integer.toString(m.getMedia_size()) + "," 
						+ m.getMedia_name() + ","
						+ Double.toString(m.getLatitude()) + "," 
						+ Double.toString(m.getLongitude()) + "," 
						+ m.getThumb_image() + ","
						+ m.getRemote_resource() + "," 
						+ Long.toString(m.getreceived_timeStamp()) + "," 
						+ Long.toString(m.getSend_timeStamp()) + ","
						+ Long.toString(m.getReceipt_server_timeStamp()) + "," 
						+ Long.toString(m.getReceipt_device_timeStamp()) + ","
						+ m.getRaw_data() + "," 
						+ m.getMedia_hash() + "," 
						+ Integer.toString(m.getrecipient_count()) + ","
						+ Integer.toString(m.getMedia_duration()) + "," 
						+ Integer.toString(m.getOrigin()) + "," 
						+ Long.toString(m.getRead_device_timeStamp()) + ","
						+ Long.toString(m.getPlayed_device_timeStamp()) + "," 
						+ m.getMedia_caption() + "," 
						+ m.getparticipant_hash()
						+ ")");
				
				System.out.println("Message " + (i + 1) + " added");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadDriver() {
		// Lade Treiber
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void connectToDatabase(String fixedDatbaseFilePath) {
		// Verbindungen herstellen
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + fixedDatbaseFilePath);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Verbindung hergestellt [" + fixedDatbaseFilePath + "]");
		
		//TODO FIX THIS AND RIGHT ORDER !
		
		Statement statement;
		try {
			statement = connection.createStatement();
			
			statement.executeUpdate("CREATE TABLE chat_list (_id INTEGER PRIMARY KEY AUTOINCREMENT, key_remote_jid TEXT UNIQUE, message_table_id INTEGER, subject TEXT, creation INTEGER, last_read_message_table_id INTEGER, last_read_receipt_sent_message_table_id INTEGER, archived INTEGER, sort_timestamp INTEGER, mod_tag INTEGER, gen REAL, my_messages INTEGER)");
			statement.executeUpdate("CREATE TABLE group_participants (_id INTEGER PRIMARY KEY AUTOINCREMENT, gjid TEXT NOT NULL, jid TEXT NOT NULL, admin INTEGER, pending INTEGER, sent_sender_key INTEGER)");
			statement.executeUpdate("CREATE TABLE group_participants_history (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp DATETIME NOT NULL, gjid TEXT NOT NULL, jid TEXT NOT NULL, action INTEGER NOT NULL, old_phash TEXT NOT NULL, new_phash TEXT NOT NULL)");
			statement.executeUpdate("CREATE TABLE media_refs (_id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT UNIQUE, ref_count INTEGER)");
			statement.executeUpdate("CREATE TABLE messages (_id INTEGER PRIMARY KEY AUTOINCREMENT, key_remote_jid TEXT NOT NULL, key_from_me INTEGER, key_id TEXT NOT NULL, status INTEGER, needs_push INTEGER, data TEXT, timestamp INTEGER, media_url TEXT, media_mime_type TEXT, media_wa_type TEXT, media_size INTEGER, media_name TEXT, latitude REAL, longitude REAL, thumb_image TEXT, remote_resource TEXT, received_timestamp INTEGER, send_timestamp INTEGER, receipt_server_timestamp INTEGER, receipt_device_timestamp INTEGER, raw_data BLOB, media_hash TEXT, recipient_count INTEGER, media_duration INTEGER, origin INTEGER, read_device_timestamp INTEGER, played_device_timestamp INTEGER, media_caption TEXT, participant_hash TEXT)");
			//TODO FIX ERROR also INVESTIGATE 
			statement.executeUpdate("CREATE VIRTUAL TABLE messages_fts USING FTS3()");
			statement.executeUpdate("CREATE TABLE 'messages_fts_content'(docid INTEGER PRIMARY KEY, 'c0content')");
			statement.executeUpdate("CREATE TABLE 'messages_fts_segdir'(level INTEGER,idx INTEGER,start_block INTEGER,leaves_end_block INTEGER,end_block INTEGER,root BLOB,PRIMARY KEY(level, idx))");
			statement.executeUpdate("CREATE TABLE 'messages_fts_segments'(blockid INTEGER PRIMARY KEY, block BLOB)");
			statement.executeUpdate("CREATE TABLE props (_id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT UNIQUE, value TEXT)");
			statement.executeUpdate("CREATE TABLE receipts (_id INTEGER PRIMARY KEY AUTOINCREMENT, key_remote_jid TEXT NOT NULL, key_id TEXT NOT NULL, remote_resource TEXT, receipt_device_timestamp INTEGER, read_device_timestamp INTEGER, played_device_timestamp INTEGER)");
			//statement.executeUpdate("CREATE TABLE sqlite_sequence(name,seq)");
			
			statement.executeUpdate("CREATE INDEX group_participants_history_index on group_participants_history (gjid)");
			statement.executeUpdate("CREATE UNIQUE INDEX group_participants_index on group_participants (gjid, jid)");
			statement.executeUpdate("CREATE INDEX media_type_index on messages (media_wa_type)");
			statement.executeUpdate("CREATE INDEX media_type_jid_index on messages (key_remote_jid, media_wa_type)");
			statement.executeUpdate("CREATE UNIQUE INDEX messages_key_index on messages (key_remote_jid, key_from_me, key_id)");
			statement.executeUpdate("CREATE INDEX receipts_key_index on receipts (key_remote_jid, key_id)");
			
			statement.executeUpdate("CREATE TRIGGER messages_bd_trigger BEFORE DELETE ON messages BEGIN DELETE FROM messages_fts WHERE docid=old._id; END");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		// Datenbank verbindung schlie�en
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
