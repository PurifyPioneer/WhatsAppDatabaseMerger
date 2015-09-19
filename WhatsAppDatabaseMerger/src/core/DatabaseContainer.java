package core;

import java.util.ArrayList;

public class DatabaseContainer {

	private int id;
	
	private ArrayList<Message> messages;
	private int messageCount = 0;
	private String databasePath = "";
	
	public DatabaseContainer(int id) {

		setID(id);
		this.messages = new ArrayList<Message>();
		
	}
	
	public DatabaseContainer(int id, String databasePath) {
		
		setID(id);
		setDatabasePath(databasePath);
		this.messages = new ArrayList<Message>();
		
	}

	public void addMessage(Message message) {
		
		messages.add(message);
		messageCount++;
		
	}
	
	// GETTER AND SETTER //////////////////////////////////////////////////////////////
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	public int getID() {
		return id;
	}
	
	private void setID(int id) {
		this.id = id;
	}
	
	public int getMessageCount() {
		return messageCount;
	}
	
	public void setDatabasePath(String databasePath) {
		this.databasePath = databasePath;
	}
	
	public String getDatabasePath() {
		return this.databasePath;
	}

	public Message getMessage(int index) {
		return getMessages().get(index);
	}
	/////////////////////////////////////////////////////////////////////////////////////

	public boolean isMessageInDatabase(Message message) {
		
		Message thisMessage;
		
		for (int i = 0; i < messages.size(); i++) {
			
			thisMessage = messages.get(i);
			
			if (!(thisMessage.getMessageAlreadyChecked())) {
				if (thisMessage.compare(message)) {
					 return true;
				}
			}
			
		}
		
		return false;
	}

	
	public void checkRightOrder() {
		
		// Gefixed Databases nach rihtiger reihenfolge �berpr�fen
		// Sortiert nach timestamps
		
		int size = messages.size();
		
		Message messageOne;
		Message messageTwo;
		long messageOneTimeStamp = 0;
		long messageTwoTimeStamp = 0;
		Message messageToCheck;
		
		for (int i = 0; i < size; i++) {
			
			messageOne = messages.get(i);
			messageTwo = messages.get(i + 1);
			
			messageOneTimeStamp = messageOne.getTimeStamp();
			messageTwoTimeStamp = messageTwo.getTimeStamp();
			
			// Wenn time stamp von nachricht nr. 1 gr��er ist als der von nummer 2
			// oder wenn gleich.. weitere pr�fung
			if (messageOneTimeStamp >= messageTwoTimeStamp) {
				
				for (int j = 0; j < size; j++) {
					
					if (messageOneTimeStamp <= messages.get(j).getTimeStamp()) {
						
						messageToCheck = messages.get(j);
						int messageOneID = messageOne.get_id();
						int messageToCheckID = messageToCheck.get_id();
						long messageToCheckTimeStamp = messageToCheck.getTimeStamp();
						
						if (messageOneTimeStamp < messageToCheckTimeStamp) {
							
							messages.remove(i);
							messages.add(j, messageOne);
							
						} else if (messageOneTimeStamp == messageToCheckTimeStamp) {
							
							if (messageOneID < messageToCheckID) {
								messages.remove(i);
								messages.add(j, messageOne);
							} else if (messageOneID > messageToCheckID) {
								messages.remove(i);
								messages.add((j + 1),messageOne);
							} else {
								//TODO WENN ID UND TIMESTAMP GLEICH
								//SONDERFALL
								//UNWAHRSCHEINLICH
								System.out.println("checkRightOrder() ERROR: TIMESTAP UND ID GLEICH !");
							}
							
						}
					
						
					} else {
						
						//Wenn nicht gefunden entferne nachricht und f�ge als letzte hinzu
						messages.remove(i);
						messages.add(messageOne);
						
					}
					
				}
				
			}
				
		}
			
		// IDs anpassend, sodass jede id nur einmal vorkommt und alle ids in richtiger reihenfolge (aufsteigend) sind
		for (int i = 0; i < messages.size(); i++) {
			
			messageOne = messages.get(i);
			messageTwo = messages.get(i + 1);
			
			int messageOneID = messageOne.get_id();
			int messageTwoID = messageTwo.get_id();
			
			if (messageOneID >= messageTwoID) {
				do {
					messageTwo.increment_id();
				} while (messageOneID >= messageTwoID);
				
			}
			
		}	
	}
	
}
