package core;

/**
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class DatabaseHandler {
	
	private DatabaseContainer[] databases;
	
	private DatabaseContainer fixedDatabase;
	
	public DatabaseHandler(int databaseCount) {
		
		databases = new DatabaseContainer[databaseCount];
		
		for (int i = 0; i < databases.length; i++) {
			databases[i] = new DatabaseContainer(i);
		}
		
	}
	
	public void addMessage(Message message, int id) {
		databases[id].addMessage(message);
	}

	public void compareDatabases() {
		
		DatabaseContainer tempDB = new DatabaseContainer(databases.length + 2);
		DatabaseContainer workingDB = new DatabaseContainer(databases.length + 1);
		
		workingDB = databases[0];
		
		int maxMessageCount = 0;
		int iPlus = 0;
		
		Message messageOne;
		Message messageTwo;
		
		boolean messagesIdentical;
		boolean messageFound;
		
		for (int i = 0; i < databases.length; i++) {
			
			iPlus = (i + 1);
			
			if ((iPlus) < databases.length) {
				
				maxMessageCount = 0;
				
				if (workingDB.getMessageCount() > databases[iPlus].getMessageCount()) {
					maxMessageCount = workingDB.getMessageCount();
				} else {
					maxMessageCount = databases[iPlus].getMessageCount();
				}
				
				//Sichergestellt, das alle Nachrichten abgearbeitet werden 
				for (int j = 0; j <= maxMessageCount; j++) {
					
					messageOne = workingDB.getMessage(j);
					messageTwo = databases[iPlus].getMessage(j);
					
					messagesIdentical = messageOne.compare(messageTwo);
					
					if (messagesIdentical) {
						tempDB.addMessage(messageTwo);
						messageOne.setMessageAlreadyChecked(true);
						messageTwo.setMessageAlreadyChecked(true);
					} else {
						
						messageFound = databases[iPlus].isMessageInDatabase(messageOne);
						
						if (messageFound) {
							tempDB.addMessage(messageTwo);
							messageOne.setMessageAlreadyChecked(true);
							messageTwo.setMessageAlreadyChecked(true);
						} else {
							tempDB.addMessage(messageOne);
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
	
	public DatabaseContainer[] getDatabases() {
		return databases;
	}
	
	public DatabaseContainer getDatabase(int id) {
		if (id < databases.length && (!(id < 0))) {
			return databases[id];
		} else {
			System.err.println("Keine Datenbank mit " + id + " gefunden!");
			return null;
		}
		
		
	}
}
