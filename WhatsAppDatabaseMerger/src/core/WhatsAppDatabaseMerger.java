package core;

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
public class WhatsAppDatabaseMerger{
	
	private static final String APPLICATION_NAME = "WhatsApp Database Merger";
	private static final String VERSION = "0.0.1a";
	
	//TODO IMPLEMENT LOGGING
	//private static final Logger logger = Logger.getLogger(WhatsAppDatabaseMerger.class.getName());
	
	//TODO FIX PUBLIC PRIVATE
	public static boolean stackTraceEnabled = true;
	@SuppressWarnings("unused")
	private static boolean loggingEnabled = false;
	
	//TODO maybe implement multi language support
	
	public static void main(String[] args) {

		System.out.println(APPLICATION_NAME + ": [Version: " + VERSION + "]");
		
		// Check for start arguments
		handleStartArguments(args);

		loadSQLDriver();
		
		// Create database image for each database(connection)
		@SuppressWarnings("unused")
		DatabaseHandler databaseHandler;
		databaseHandler = new DatabaseHandler();
		
		@SuppressWarnings("unused")
		DatabaseBuilder databaseBuilder;
		databaseBuilder = new DatabaseBuilder(null);
		
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

}
