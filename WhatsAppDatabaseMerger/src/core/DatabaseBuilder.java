package core;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

			ArrayList<Message> messages = fixedDatabase.getMessages();

			System.out.println(fixedDatabase.getMessageCount());

			for (int i = 0; i < fixedDatabase.getMessageCount(); i++) {
				Message m = messages.get(i);
				
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
						+ Integer.toString(m.getMedia_wa_type()) + "," 
						+ Integer.toString(m.getMedia_size()) + "," 
						+ m.getMedia_name() + ","
						+ Float.toString(m.getLatitude()) + "," 
						+ Float.toString(m.getLongitude()) + "," 
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
