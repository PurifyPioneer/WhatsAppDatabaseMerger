package core;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ChatListEntry;
import database.GroupParticipantsEntry;
import database.GroupParticipantsHistoryEntry;
import database.MediaRefsEntry;
import database.MessagesEntry;
import database.MessagesFtsContentEntry;
import database.MessagesFtsSegdirEntry;
import database.MessagesFtsSegmentsEntry;
import database.PropsEntry;
import database.ReceiptsEntry;
import database.SqliteSequenceEntry;

/**
 * Handles all input Databases and
 * organizes creation of new database.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class DatabaseHandler {
	
	private ArrayList<DatabaseContainer> databases;
	
	//TODO BELOW
	private DatabaseContainer fixedDatabase;
	
	private Connection[] connections = null;
	
	public DatabaseHandler() {
		
		findDatabases();
		createConnections();
		readDatabases();
		
		//compareDatabases();
		
		closeConnections();
	}
	
	public void addMessage(MessagesEntry message, int id) {
		databases.get(id).addMessageEntry(message);
	}

	private void findDatabases() {
		
		File databaseFolder = new File("Databases");
		File tempFolder = new File("Temp");
		File outputFolder = new File("Output");
		
		File[] fileList = null;
		
		if (databaseFolder.exists()) {
			fileList  = databaseFolder.listFiles();
		} else {
			System.out.println("No database folder found. Creating Database Folder.");
			databaseFolder.mkdir();
			System.out.println("Please put your databases into the created database folder and rerun the program.");
			System.out.println("Exiting program now.");	
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
		
		if (!(tempFolder.exists())) {
			System.out.println("Temp Folder not found. Creating Temp Folder.");
			tempFolder.mkdir();
			System.out.println("Temp Folder created.");
		} 
		
		if (!(outputFolder.exists())) {
			System.out.println("Output Folder not found. Creating Output Folder.");
			outputFolder.mkdir();
			System.out.println("Output folder created.");
		}
		
		
		// Create Database containers for each file found
		if (fileList.length > 0) {
			databases = new ArrayList<DatabaseContainer>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().endsWith(".db")) {
						databases.add(new DatabaseContainer(databases.size(), "jdbc:sqlite:Databases/" + fileList[i].getName()));
				} else {
					System.out.println("File " + fileList[i].getName() + " is not a database");
					//TODO IF THERE ARE FILE BUT NO DATABASE
				}
			}
		
			System.out.println("Found " + databases.size() + " databases:");
			for (int i = 0; i < databases.size(); i++) {
				System.out.println(databases.get(i).getDatabasePath());
			}
			
		} else {
			System.out.println("No databases found.");
			System.out.println("Exiting program now.");
			try {
				Thread.sleep(2500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.exit(0);
		}

	}
	
	private void createConnections() {
		
		connections = new Connection[databases.size()];
		System.out.println(databases.size() + " Connections waiting to be opened");
		
		int errorsHappened = 0;
		
		for (int i = 0; i < connections.length; i++) {
			try {
				connections[i] = DriverManager.getConnection(databases.get(i).getDatabasePath());
				System.out.println("Connection " + (i + 1) + " created [" + databases.get(i).getDatabasePath() + "]");
			} catch (Exception e) {
				if (WhatsAppDatabaseMerger.stackTraceEnabled) {
					e.printStackTrace();
				}
				System.out.println("Error while creating connection " + i);
				System.out.println("Skipping database " + i + ".");
				errorsHappened++;
			} 
		}
		if (errorsHappened == 0 && (databases.size() != 0)) {
			System.out.println("All connections created successful");
		} else if(databases.size() != 0){
			System.out.println("Created " + (connections.length - errorsHappened) + " connections." );
			System.out.println("Failed to create " + errorsHappened + " connections."); 
			//TODO Log which connections could not be established.
		}
	}
	
	private void readDatabases() {
		
		DatabaseContainer database;
		Statement statement = null;
		ResultSet resultSet = null;
		
		for (int i = 0; i < connections.length; i++) {
			database = databases.get(i);
			System.out.println((connections.length - i) + " Databases left...");
			readChatListFromDatabase(database, statement, resultSet);
			readGroupParticipantsFromDatabase(database, statement, resultSet);
			readGroupParticipantsHistoryFromDatabase(database, statement, resultSet);
			readMediaRefsFromDatabase(database, statement, resultSet);
			readMessagesFromDatabase(database, statement, resultSet);
			readMessagesFtsContentFromDatabase(database, statement, resultSet);
			readMessagesFtsSegdirFromDatabase(database, statement, resultSet);
			readMessagesFtsSegmentsFromDatabase(database, statement, resultSet);
			readPropsFromDatabase(database, statement, resultSet);
			readReceiptsFromDatabase(database, statement, resultSet);
			readSqliteSequenceFromDatabase(database, statement, resultSet);	
		}
		
	}
	
	private void readChatListFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM chat_list");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addChatListEntry(new ChatListEntry(
						resultSet.getInt("_id"), 
						resultSet.getString("key_remote_jid"), 
						resultSet.getInt("message_table_id"),
						resultSet.getString("subject"),
						resultSet.getInt("creation"),
						resultSet.getInt("last_read_message_table_id"),
						resultSet.getInt("last_read_receipt_sent_message_table_id"),
						resultSet.getInt("archived"),
						resultSet.getInt("sort_timestamp"),
						resultSet.getInt("mod_tag"),
						resultSet.getDouble("gen"),
						resultSet.getInt("my_messages")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing chat_list_entry from database " + database.getID());
		}
		
	}
	
	private void readGroupParticipantsFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM group_participants");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addGroupParticipantsEntry(new GroupParticipantsEntry(
						resultSet.getInt("_id"),
						resultSet.getString("gjid"),
						resultSet.getString("jid"),
						resultSet.getInt("admin"),
						resultSet.getInt("pending"),
						resultSet.getInt("sent_sender_key")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing group_participants_entry from database " + database.getID());
		}
	}
	
	private void readGroupParticipantsHistoryFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM group_participants_history");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addGroupParticipantsHistoryEntry(new GroupParticipantsHistoryEntry(
						resultSet.getInt("_id"),
						resultSet.getInt("timestamp"),
						resultSet.getString("gjid"),
						resultSet.getString("jid"),
						resultSet.getInt("action"),
						resultSet.getString("old_phash"),
						resultSet.getString("new_phash")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing group_participants_history_entry from database " + database.getID());
		}
	}
	
	private void readMediaRefsFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM media_refs");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addMediaRefsEntry(new MediaRefsEntry(
						resultSet.getInt("_id"),
						resultSet.getString("path"),
						resultSet.getInt("ref_count")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing media_refs_entry from database " + database.getID());
		}
	}
	
	private void readMessagesFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM messages");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
		
		int messageCount = 0;
		try {
			while (resultSet.next()) {
				database.addMessageEntry(new MessagesEntry(
						resultSet.getInt("_id"),
						resultSet.getString("key_remote_jid"),
						resultSet.getInt("key_from_me"),
						resultSet.getString("key_id"),
						resultSet.getInt("status"),
						resultSet.getInt("needs_push"),
						resultSet.getString("data"),
						resultSet.getLong("timestamp"),
						resultSet.getString("media_url"),
						resultSet.getString("media_mime_type"),
						resultSet.getString("media_wa_type"),
						resultSet.getInt("media_size"),
						resultSet.getString("media_name"),
						resultSet.getDouble("latitude"),
						resultSet.getDouble("longitude"),
						resultSet.getString("thumb_image"),
						resultSet.getString("remote_resource"),
						resultSet.getLong("received_timestamp"),
						resultSet.getLong("send_timestamp"),
						resultSet.getLong("receipt_server_timestamp"),
						resultSet.getLong("receipt_device_timestamp"),
						resultSet.getBytes("raw_data"),
						resultSet.getString("media_hash"),
						resultSet.getInt("recipient_count"),
						resultSet.getInt("media_duration"),
						resultSet.getInt("origin"),
						resultSet.getLong("read_device_timestamp"),
						resultSet.getLong("played_device_timestamp"),
						resultSet.getString("media_caption"),
						resultSet.getString("participant_hash")));
				messageCount++;
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing messages from database " + database.getID());
		}
		System.out.println(messageCount + " Messages added to database " + (database.getID() + 1));
	}
			
	private void readMessagesFtsContentFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM messages_fts_content");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addMessagesFtsContentEntry(new MessagesFtsContentEntry(
						resultSet.getInt("docid"),
						resultSet.getString("c0content")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing messages_fts_content_entry from database " + database.getID());
		}
	}
	
	private void readMessagesFtsSegdirFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM messages_fts_segdir");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addMessagesFtsSegdirEntry(new MessagesFtsSegdirEntry(
						resultSet.getInt("level"),
						resultSet.getInt("idx"),
						resultSet.getInt("start_block"),
						resultSet.getInt("Leaves_end_block"),
						resultSet.getInt("end_block"),
						resultSet.getBytes("root")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing messages_fts_segdir_entry from database " + database.getID());
		}
	}
	
	private void readMessagesFtsSegmentsFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM messages_fts_segments");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addMEssagesFtsSegmentsEntry(new MessagesFtsSegmentsEntry(
						resultSet.getInt("blockid"),
						resultSet.getBytes("block")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing messages_fts_segments_entry from database " + database.getID());
		}
	}
	
	private void readPropsFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM props");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addPropsEntry(new PropsEntry(
						resultSet.getInt("_id"),
						resultSet.getString("key"),
						resultSet.getString("value")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing props_entry from database " + database.getID());
		}
	}

	private void readReceiptsFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM receipts");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addReceiptsEntry(new ReceiptsEntry(
						resultSet.getInt("_id"),
						resultSet.getString("key_remote_jid"),
						resultSet.getString("key_id"),
						resultSet.getString("remote_resource"),
						resultSet.getInt("receipt_device_timestamp"),
						resultSet.getInt("read_device_timestamp"),
						resultSet.getInt("played_device_timestamp")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing eceipts_entry from database " + database.getID());
		}
	}
	
	private void readSqliteSequenceFromDatabase(DatabaseContainer database, Statement statement, ResultSet resultSet) {
		
		//DATEN AUS DATENBANKEN EINLESEN
		try{
			statement = connections[database.getID()].createStatement();
			resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence");
		} catch (Exception e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while reading from database " + database.getID());
		}
	
		try {
			while (resultSet.next()) {
				database.addSqliteSequenceEntry(new SqliteSequenceEntry(
						resultSet.getString("name"),
						resultSet.getString("seq")));
			}
		} catch (SQLException e) {
			if (WhatsAppDatabaseMerger.stackTraceEnabled) {
				e.printStackTrace();
			}
			System.out.println("Error while parsing sqlite_sequence_entry from database " + database.getID());
		}
	}
	
	private void closeConnections() {
			
			for (int i = 0; i < connections.length; i++) {
				if (connections[i] != null) {
					try {
						connections[i].close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Connection " + i + " closed [" + databases.get(i).getDatabasePath() + "]");
				}
			}
		}
		
	private DatabaseContainer getDatabase(int id) {
		if (id < databases.size() && (!(id < 0))) {
			return databases.get(id);
		} else {
			System.err.println("Keine Datenbank mit " + id + " gefunden!");
			return null;
		}	
		
	}
	
	public void compareDatabases() {
		
		DatabaseContainer tempDB = new DatabaseContainer(databases.size() + 2);
		DatabaseContainer workingDB = new DatabaseContainer(databases.size() + 1);
		
		workingDB = databases.get(0);
		
		int maxMessageCount = 0;
		int iPlus = 0;
		
		MessagesEntry messageOne;
		MessagesEntry messageTwo;
		
		boolean messagesIdentical;
		boolean messageFound;
		
		for (int i = 0; i < databases.size(); i++) {
			
			iPlus = (i + 1);
			
			if ((iPlus) < databases.size()) {
				
				maxMessageCount = 0;
				
				if (workingDB.getMessageCount() > databases.get(iPlus).getMessageCount()) {
					maxMessageCount = workingDB.getMessageCount();
				} else {
					maxMessageCount = databases.get(iPlus).getMessageCount();
				}
				
				//Sichergestellt, das alle Nachrichten abgearbeitet werden 
				for (int j = 0; j <= maxMessageCount; j++) {
					
					messageOne = workingDB.getMessage(j);
					messageTwo = databases.get(iPlus).getMessage(j);
					
					messagesIdentical = messageOne.compare(messageTwo);
					
					if (messagesIdentical) {
						tempDB.addMessageEntry(messageTwo);
						messageOne.setMessageAlreadyChecked(true);
						messageTwo.setMessageAlreadyChecked(true);
					} else {
						
						messageFound = databases.get(iPlus).isMessageInDatabase(messageOne);
						
						if (messageFound) {
							tempDB.addMessageEntry(messageTwo);
							messageOne.setMessageAlreadyChecked(true);
							messageTwo.setMessageAlreadyChecked(true);
						} else {
							tempDB.addMessageEntry(messageOne);
							messageOne.setMessageAlreadyChecked(true);
						}
						
					}
				}		
				
				workingDB = tempDB;
				
			} else {
				fixedDatabase = workingDB;
				fixedDatabase.checkRightOrder();
			}
			
			
		}
		
	}
	
}
