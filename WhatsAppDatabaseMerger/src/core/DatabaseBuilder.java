package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	private File fixedDatabaseFile;

	private DatabaseContainer fixedDatabase;
	private Connection connection = null;

	private boolean databaseInitialized = false;

	public DatabaseBuilder(DatabaseContainer fixedDatabase) {

		initializeDatabaseFile();
		setFixedDatabase(fixedDatabase);

		connectToDatabase(fixedDatabaseFile.getPath());
		initializeDatabase();
		fillDatabase();
		closeConnection();

		moveDatabaseFileToOutputFolder();
	}

	private DatabaseContainer getFixedDatabase() {
		return fixedDatabase;
	}

	private void setFixedDatabase(DatabaseContainer fixedDatabase) {
		this.fixedDatabase = fixedDatabase;
	}

	public boolean isDatabaseInitialized() {
		return databaseInitialized;
	}

	private void setDatabaseInitialized(boolean databaseInitialized) {
		this.databaseInitialized = databaseInitialized;
	}

	private void initializeDatabaseFile() {

		File tempFolder = new File("Temp");

		if (!(tempFolder.exists())) {
			tempFolder.mkdir();
			System.out.println("Temp folder not found. Created it.");
		}

		fixedDatabaseFile = new File("Temp/msgstore_temp.db");

		if (!(fixedDatabaseFile.exists())) {
			try {
				fixedDatabaseFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error while creating new datbase file");
			}
			System.out.println("New databasefile created.");
		} else if (fixedDatabaseFile.exists()) {
			fixedDatabaseFile.delete();
			System.out.println("Old temporary database file deleted.");
			try {
				fixedDatabaseFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error while creating new datbase file");
			}
			System.out.println("New databasefile created.");
		}

	}

	private void moveDatabaseFileToOutputFolder() {

		// TODO NEEDS FURTHER WORK

		File outputFolder = new File("Output");

		if (!(outputFolder.exists() || (!outputFolder.isDirectory()))) {
			outputFolder.mkdir();
			System.out.println("Output folder not found. Created it.");

			try {
				System.err.println("1");
				// fixedDatabaseFile.renameTo(Paths.get("Temp/msgstore.db").toFile());
				Files.move(fixedDatabaseFile.toPath(),
						Paths.get(outputFolder.getPath(), "/", fixedDatabaseFile.getName()),
						StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			File[] fileList = outputFolder.listFiles();

			int num = 0;

			if (outputFolder.isDirectory()) {
				for (int i = 0; i < fileList.length; i++) {
					if (fileList[i].getName().endsWith(".db")) {
						num++;
					}
				}
			}
			num++;

			if (num == 0) {
				try {
					System.err.println("2");
					// fixedDatabaseFile.renameTo(Paths.get("Temp/msgstore.db").toFile());
					Files.move(fixedDatabaseFile.toPath(),
							Paths.get(outputFolder.getPath(), "/", fixedDatabaseFile.getName()),
							StandardCopyOption.REPLACE_EXISTING);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					System.err.println("3");
					// fixedDatabaseFile.renameTo(Paths.get("Temp/msgstore_" +
					// num + ".db").toFile());
					Files.move(fixedDatabaseFile.toPath(),
							Paths.get(outputFolder.getPath(), "/", fixedDatabaseFile.getName()),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	private void connectToDatabase(String fixedDatbaseFilePath) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + fixedDatbaseFilePath);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Verbindung hergestellt [" + fixedDatbaseFilePath + "]");
	}

	private void initializeDatabase() {

		try {
			if (connection.isValid(0)) {
				Statement statement;
				try {
					statement = connection.createStatement();

					statement.executeUpdate(
							"CREATE TABLE chat_list (_id INTEGER PRIMARY KEY AUTOINCREMENT, key_remote_jid TEXT UNIQUE, message_table_id INTEGER, subject TEXT, creation INTEGER, last_read_message_table_id INTEGER, last_read_receipt_sent_message_table_id INTEGER, archived INTEGER, sort_timestamp INTEGER, mod_tag INTEGER, gen REAL, my_messages INTEGER)");
					statement.executeUpdate(
							"CREATE TABLE group_participants (_id INTEGER PRIMARY KEY AUTOINCREMENT, gjid TEXT NOT NULL, jid TEXT NOT NULL, admin INTEGER, pending INTEGER, sent_sender_key INTEGER)");
					statement.executeUpdate(
							"CREATE TABLE group_participants_history (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp DATETIME NOT NULL, gjid TEXT NOT NULL, jid TEXT NOT NULL, action INTEGER NOT NULL, old_phash TEXT NOT NULL, new_phash TEXT NOT NULL)");
					statement.executeUpdate(
							"CREATE TABLE media_refs (_id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT UNIQUE, ref_count INTEGER)");
					statement.executeUpdate(
							"CREATE TABLE messages (_id INTEGER PRIMARY KEY AUTOINCREMENT, key_remote_jid TEXT NOT NULL, key_from_me INTEGER, key_id TEXT NOT NULL, status INTEGER, needs_push INTEGER, data TEXT, timestamp INTEGER, media_url TEXT, media_mime_type TEXT, media_wa_type TEXT, media_size INTEGER, media_name TEXT, latitude REAL, longitude REAL, thumb_image TEXT, remote_resource TEXT, received_timestamp INTEGER, send_timestamp INTEGER, receipt_server_timestamp INTEGER, receipt_device_timestamp INTEGER, raw_data BLOB, media_hash TEXT, recipient_count INTEGER, media_duration INTEGER, origin INTEGER, read_device_timestamp INTEGER, played_device_timestamp INTEGER, media_caption TEXT, participant_hash TEXT)");
					statement.executeUpdate("CREATE VIRTUAL TABLE messages_fts USING FTS3()");

					/*
					 * Tabellen werden automatisch erstellt durch VIRTUAL TABLE
					 * statement.executeUpdate(
					 * "CREATE TABLE 'messages_fts_content'(docid INTEGER PRIMARY KEY, 'c0content')"
					 * ); statement.executeUpdate(
					 * "CREATE TABLE 'messages_fts_segdir'(level INTEGER,idx INTEGER,start_block INTEGER,leaves_end_block INTEGER,end_block INTEGER,root BLOB,PRIMARY KEY(level, idx))"
					 * ); statement.executeUpdate(
					 * "CREATE TABLE 'messages_fts_segments'(blockid INTEGER PRIMARY KEY, block BLOB)"
					 * );
					 */

					statement.executeUpdate(
							"CREATE TABLE props (_id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT UNIQUE, value TEXT)");
					statement.executeUpdate(
							"CREATE TABLE receipts (_id INTEGER PRIMARY KEY AUTOINCREMENT, key_remote_jid TEXT NOT NULL, key_id TEXT NOT NULL, remote_resource TEXT, receipt_device_timestamp INTEGER, read_device_timestamp INTEGER, played_device_timestamp INTEGER)");

					/*
					 * Tabelle wird ebendfalls automatisch erstellt durch sqlite
					 * statement.executeUpdate(
					 * "CREATE TABLE sqlite_sequence(name,seq)");
					 */

					statement.executeUpdate(
							"CREATE INDEX group_participants_history_index on group_participants_history (gjid)");
					statement.executeUpdate(
							"CREATE UNIQUE INDEX group_participants_index on group_participants (gjid, jid)");
					statement.executeUpdate("CREATE INDEX media_type_index on messages (media_wa_type)");
					statement.executeUpdate(
							"CREATE INDEX media_type_jid_index on messages (key_remote_jid, media_wa_type)");
					statement.executeUpdate(
							"CREATE UNIQUE INDEX messages_key_index on messages (key_remote_jid, key_from_me, key_id)");
					statement.executeUpdate("CREATE INDEX receipts_key_index on receipts (key_remote_jid, key_id)");

					statement.executeUpdate(
							"CREATE TRIGGER messages_bd_trigger BEFORE DELETE ON messages BEGIN DELETE FROM messages_fts WHERE docid=old._id; END");

					setDatabaseInitialized(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void fillDatabase() {
		// fillChatListTable();
		// fillGroupParticipantsTable();
		// fillGroupParticipantsHistoryTable();
		// fillMediarRefsTable();
		fillMessagesTable();
		// fillMessagesFtsContentTable();
		// fillMessagesFtsSegdirTable();
		// fillMessagesFtsSegmentsTable();
		// fillPropsTable();
		// fillReceiptsTable();
		// fillSqliteSequenceTable();
	}

	private void fillChatListTable() {
		// TODO Auto-generated method stub

	}

	private void fillGroupParticipantsTable() {
		// TODO Auto-generated method stub

	}

	private void fillGroupParticipantsHistoryTable() {
		// TODO Auto-generated method stub

	}

	private void fillMediarRefsTable() {
		// TODO Auto-generated method stub

	}

	private void fillMessagesTable() {
		
		PreparedStatement preparedStatement;
		try {

			// Eintaege aus fixed Tabelle in neue Tabelle eintragen

			ArrayList<MessagesEntry> messages = fixedDatabase.getMessages();

			System.out.println("Fixed Database Message Count: " + fixedDatabase.getMessageCount());
			
			preparedStatement = connection.prepareStatement(
					"INSERT OR IGNORE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			connection.setAutoCommit(false);

			MessagesEntry m = null;
			int x = 0;
			for (int i = 0; i < messages.size(); i++) {
				m = messages.get(i);
			
				if (i % 10000 == 0) {
					System.out.println("Inserted: " + i);
				}
				
				// TODO FIX ERROR WHERE 0 NEEDS to be NULL

				preparedStatement.setInt(1, m.get_id());
				preparedStatement.setString(2, m.getKey_remote_jid());
				preparedStatement.setInt(3, m.getKey_from_me());
				preparedStatement.setString(4, m.getKey_id());
				preparedStatement.setInt(5, m.getStatus());
				preparedStatement.setInt(6, m.getNeeds_push());
				preparedStatement.setString(7, m.getData());
				preparedStatement.setLong(8, m.getTimeStamp());
				preparedStatement.setString(9, m.getMedia_url());
				preparedStatement.setString(10, m.getMedia_mime_type());
				preparedStatement.setString(11, m.getMedia_wa_type());
				preparedStatement.setInt(12, m.getMedia_size());
				preparedStatement.setString(13, m.getMedia_name());
				preparedStatement.setDouble(14, m.getLatitude());
				preparedStatement.setDouble(15, m.getLongitude());

				// TODO THUMB IMAGE NEEDS TO BE BLOB !?
				preparedStatement.setString(16, m.getThumb_image());
				preparedStatement.setString(17, m.getRemote_resource());
				preparedStatement.setDouble(18, m.getreceived_timeStamp());
				preparedStatement.setDouble(19, m.getSend_timeStamp());
				preparedStatement.setDouble(20, m.getReceipt_server_timeStamp());
				preparedStatement.setDouble(21, m.getReceipt_device_timeStamp());

				Blob blob = m.getRaw_data();
				byte[] blobToByte = new byte[0];
				if (blob != null) {
					blobToByte = blob.getBytes(1, (int) blob.length());
				}

				preparedStatement.setBytes(22, blobToByte);
				preparedStatement.setString(23, m.getMedia_hash());
				preparedStatement.setInt(24, m.getrecipient_count());

				preparedStatement.setInt(25, m.getMedia_duration());
				preparedStatement.setInt(26, m.getOrigin());
				preparedStatement.setLong(27, m.getRead_device_timeStamp());
				preparedStatement.setLong(28, m.getPlayed_device_timeStamp());
				preparedStatement.setString(29, m.getMedia_caption());
				preparedStatement.setString(30, m.getparticipant_hash());

				try {
					x += preparedStatement.executeUpdate();
					
				} catch (Exception e) {	
					e.printStackTrace();
					System.out.println(m);
				}
			}
			
			System.out.println(x);
			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillMessagesFtsContentTable() {
		// TODO Auto-generated method stub

	}

	private void fillMessagesFtsSegdirTable() {
		// TODO Auto-generated method stub

	}

	private void fillMessagesFtsSegmentsTable() {
		// TODO Auto-generated method stub

	}

	private void fillPropsTable() {
		// TODO Auto-generated method stub

	}

	private void fillReceiptsTable() {
		// TODO Auto-generated method stub

	}

	private void fillSqliteSequenceTable() {
		// TODO Auto-generated method stub

	}

	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
