package core;

/**
 * This program can be used to
 * combine different WhatsApp-backups.
 * 
 * For this program to function it is
 * essential that you have your decrypted
 * Whatsapp database (msgstore.db)!
 * 
 * Eventually this program will support the extraction
 * and decryption of your Whatsapp backup but
 * time will tell and there are other great programs
 * for this purpose.
 * 
 * Please note that this program at the moment only handles
 * your messages, so you may need to re-add contacts by yourself.
 * But this is only a minor concern and will be addressed later.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class WhatsAppDatabaseMerger{
	
	// Title and Version
	private static final String APPLICATION_NAME = "WhatsApp Database Merger";
	private static final String VERSION = "0.0.1a";

	// Indicators if Stacktrace/Logging are enabled
	private static boolean stackTraceEnabled = true;
	private static boolean loggingEnabled = false;
	
	public static void main(String[] args) {

		System.out.println(APPLICATION_NAME + ": [Version: " + VERSION + "]");
		
		// Check for start arguments
		handleStartArguments(args);

		// Load SQL Driver
		loadSQLDriver();
		
		// Create database image for each database(connection)
		DatabaseHandler databaseHandler;
		databaseHandler = new DatabaseHandler();
		
		//Create new Database
		@SuppressWarnings("unused")
		DatabaseBuilder databaseBuilder;
		databaseBuilder = new DatabaseBuilder(databaseHandler.getFixedDatabase());
		
		System.out.println("You can exit the programm now!");
	}

	private static void handleStartArguments(String[] args) {
		
		if (args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("-e") || args[0].equals("-error") || args[0].equals("-st") || args[0].equals("-stacktrace") ) {
					stackTraceEnabled = true;
					System.out.println("Stacktrace has been enabled.");
				} else if (args[i].equalsIgnoreCase("-l") || args[i].equalsIgnoreCase("-log")) {
					loggingEnabled = true;
					System.out.println("Logging has been enabled");
				} else {
					System.out.println("Provided startargument could not be understood. (Startargument " + i + ")");
				}
				
			}

		}
		
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
			System.out.println("Exiting programm now.");
			
			//TODO Implement better technnique for scheduled tasks
			try {
				Thread.sleep(2500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.exit(-1);
		}		
	}
	
	public static boolean isStackTraceEnabled() {
		return WhatsAppDatabaseMerger.stackTraceEnabled;
	}
	
	public static boolean isLoggingEnabled() {
		return WhatsAppDatabaseMerger.loggingEnabled;
	}

}
