package core;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This programm can be used to
 * combine different WhatsApp-backups.
 * 
 * For this programm to function it is
 * essential that you have your decrypted
 * Whatsapp message database (msgstore.db)!
 * 
 * Eventually this programm will support the extraction
 * and decryption of your Whatsapp-backup but
 * time will tell and there are other great programms
 * for this purpose.
 * 
 * Please note that this programm at the moment only handels
 * your messages, so you may need to readd contacs.
 * But this is only a minor concern and will adressed later.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class WhatsAppDatabaseMerger {

	private static ArrayList<String> paths = new ArrayList<String>();
	private static Connection[] connections;
	private static DatabaseHandler databaseHandler;
	
	private final static String VERSION = "0.0.1a";

	private static boolean stackTraceEnabled = false;
	
	//TODO Implement Logging functionality
	//TODO GPS Coordinates fix
	//TODO maybe implement multi language support
	
	public static void main(String[] args) {

		System.out.println("WhatsApp Database Merger: [Version: " + VERSION + "]");
		
		// Check for startarguments
		if (args.length != 0) {
			if (args[0].equalsIgnoreCase("-e") || args[0].equals("-error") || args[0].equals("-st") || args[0].equals("-stacktrace") ) {
				stackTraceEnabled = true;
				System.out.println("Stacktrace has been enabled.");
			} else {
				System.out.println("Provided startarguments could not be understood.");
				System.out.println("Continuing without startarguments.");
			}
		}

		loadSQLDriver();
		
		//TODO
		//Check file system..
		//if all folders are existent
		//if not create them
		
		// Look for databases in folder /databases
		findDatabases();
		
		// Create conncetion
		createConnections();
		
		// Create database image for each database(connection)
		databaseHandler = new DatabaseHandler(connections.length);
		readMessagesFromDatabase();

		//messagesHandler.compareDatabases();
		
		// Verbindungen schlieﬂen
		closeConnections();
		
		System.out.println("You can exit the programm now!");
	}

	

	private static void loadSQLDriver() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Driver loaded successful.");
		} catch (ClassNotFoundException e) {
			if (stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Driver could not be loaded.");
		}		
	}

	private static void findDatabases() {
		
		File databaseFolder = new File("Databases");
		File tempFolder = new File("Temp");
		File outputFolder = new File("Output");
		
		File[] fileList = null;
		
		if (databaseFolder.exists()) {
			fileList  = databaseFolder.listFiles();
		} else {
			System.out.println("No database folder found.");
			databaseFolder.mkdir();
			System.out.println("Please put your databases into the created databaase folder.");
			return;
		
		}
		
		if (!(tempFolder.exists())) {
			tempFolder.mkdir();
			System.out.println("Temp Folder created.");
		} 
		
		if (!(outputFolder.exists())) {
			outputFolder.mkdir();
			System.out.println("Output folder created.");
		}
		
		
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].getName().endsWith(".db")) {
				paths.add("jdbc:sqlite:Databases/" + fileList[i].getName());
			}
		}
		
		System.out.println("Found " + paths.size() + " databases:");
		for (int i = 0; i < paths.size(); i++) {
			System.out.println(paths.get(i));
		}
	}

	private static void createConnections() {
		
		connections = new Connection[paths.size()];
		System.out.println(paths.size() + " Connections waiting to be opened");
		
		for (int i = 0; i < connections.length; i++) {
			try {
				connections[i] = DriverManager.getConnection(paths.get(i));
				System.out.println("Connection " + (i + 1) + " created [" + paths.get(i) + "]");
			} catch (Exception e) {
				if (stackTraceEnabled) {
					e.printStackTrace();
				}
				System.out.println("Error while creating connection " + i);
			} 
		}
		System.out.println("All connections created successful");
	}

	private static void readMessagesFromDatabase() {
		
		Statement statement = null;
		ResultSet rs = null;
		
		//DATEN AUS DATENBANKEN EINLESEN 
		for (int i = 0; i < connections.length; i++) {
			try{
				statement = connections[i].createStatement();
				rs = statement.executeQuery("SELECT * FROM messages");
			} catch (Exception e) {
				if (stackTraceEnabled) {
					e.printStackTrace();
				}
				System.out.println("Error while reading from database " + i);
			}
			
			int messageCount = 0;
			try {
				while (rs.next()) {
					databaseHandler.getDatabase(i).addMessage(new Message(
							rs.getInt("_id"),
							rs.getString("key_remote_jid"),
							rs.getInt("key_from_me"),
							rs.getString("key_id"),
							rs.getInt("status"),
							rs.getInt("needs_push"),
							rs.getString("data"),
							rs.getLong("timestamp"),
							rs.getString("media_url"),
							rs.getString("media_mime_type"),
							rs.getInt("media_wa_type"),
							rs.getInt("media_size"),
							rs.getString("media_name"),
							rs.getFloat("latitude"),
							rs.getFloat("longitude"),
							rs.getString("thumb_image"),
							rs.getString("remote_resource"),
							rs.getLong("received_timestamp"),
							rs.getLong("send_timestamp"),
							rs.getLong("receipt_server_timestamp"),
							rs.getLong("receipt_device_timestamp"),
							rs.getString("raw_data"),
							rs.getString("media_hash"),
							rs.getInt("recipient_count"),
							rs.getInt("media_duration"),
							rs.getInt("origin"),
							rs.getLong("read_device_timestamp"),
							rs.getLong("played_device_timestamp"),
							rs.getString("media_caption"),
							rs.getString("participant_hash")));
					messageCount++;
				}
				databaseHandler.getDatabase(i).setDatabasePath(paths.get(i));
			} catch (SQLException e) {
				if (stackTraceEnabled) {
					e.printStackTrace();
				}
				System.out.println("Error while parsing messages from database " + i);
			}
			System.out.println(messageCount + " Messages added to database " + (i + 1));
			System.out.println((connections.length - (i + 1)) + " Databases left...");
			
		}		
	}
	
	private static void closeConnections() {
		
		for (int i = 0; i < connections.length; i++) {
			if (connections[i] != null) {
				try {
					connections[i].close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Connection " + i + " closed [" + paths.get(i) + "]");
			}
		}
	}
}
