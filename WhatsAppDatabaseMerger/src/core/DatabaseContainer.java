package core;

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
 * Representation of a whatsapp
 * database. (msgstore database)
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class DatabaseContainer {

	private int id;
	
	private ArrayList<ChatListEntry> chatList;
	private ArrayList<GroupParticipantsEntry> groupParticipants;
	private ArrayList<GroupParticipantsHistoryEntry> groupParticipantsHistory;
	private ArrayList<MediaRefsEntry> mediaRefs;
	private ArrayList<MessagesEntry> messages;
	private ArrayList<MessagesFtsContentEntry> messagesFtscontent;
	private ArrayList<MessagesFtsSegdirEntry> messagesFtsSegdir;
	private ArrayList<MessagesFtsSegmentsEntry> messagesFtsSegments;
	private ArrayList<PropsEntry> props;
	private ArrayList<ReceiptsEntry> receipts;
	private ArrayList<SqliteSequenceEntry> sqliteSequence;
	
	private String databasePath = null;
	
	public DatabaseContainer(int id) {

		setID(id);
		setChatList(new ArrayList<ChatListEntry>());
		setGroupParticipants(new ArrayList<GroupParticipantsEntry>());
		setGroupParticipantsHistory(new ArrayList<GroupParticipantsHistoryEntry>());
		setMediaRefs(new ArrayList<MediaRefsEntry>());
		setMessages(new ArrayList<MessagesEntry>());
		setMessagesFtscontent(new ArrayList<MessagesFtsContentEntry>());
		setMessagesFtsSegdir(new ArrayList<MessagesFtsSegdirEntry>());
		setMessagesFtsSegments(new ArrayList<MessagesFtsSegmentsEntry>());
		setProps(new ArrayList<PropsEntry>());
		setReceipts(new ArrayList<ReceiptsEntry>());
		setSqliteSequence(new ArrayList<SqliteSequenceEntry>());
		
		
	}
	
	public DatabaseContainer(int id, String databasePath) {
		
		this(id);
		setDatabasePath(databasePath);
		
	}

	public void addChatListEntry(ChatListEntry chatListEntry) {
		getChatList().add(chatListEntry);
	}
	
	public void addGroupParticipantsEntry(GroupParticipantsEntry groupParticipantsEntry) {
		getGroupParticipants().add(groupParticipantsEntry);
	}
	
	public void addGroupParticipantsHistoryEntry(GroupParticipantsHistoryEntry GroupParticipantsHistoryEntry) {
		getGroupParticipantsHistory().add(GroupParticipantsHistoryEntry);
	}
	
	public void addMediaRefsEntry(MediaRefsEntry MediaRefsEntry) {
		getMediaRefs().add(MediaRefsEntry);
	}
	
	public void addMessageEntry(MessagesEntry messageEntry) {	
		getMessages().add(messageEntry);	
	}
	
	public void addMessagesFtsContentEntry(MessagesFtsContentEntry messagesFtsContentEntry) {
		getMessagesFtscontent().add(messagesFtsContentEntry);
	}
	
	public void addMessagesFtsSegdirEntry(MessagesFtsSegdirEntry messagesFtsSegdirEntry) {
		getMessagesFtsSegdir().add(messagesFtsSegdirEntry);
	}
	
	public void addMEssagesFtsSegmentsEntry(MessagesFtsSegmentsEntry messagesFtsSegmentsEntry) {
		getMessagesFtsSegments().add(messagesFtsSegmentsEntry);
	}
	
	public void addPropsEntry(PropsEntry PropsEntry) {
		getProps().add(PropsEntry);
	}
	
	public void addReceiptsEntry(ReceiptsEntry receiptsEntry) {
		getReceipts().add(receiptsEntry);
	}
	
	public void addSqliteSequenceEntry(SqliteSequenceEntry SqlLiteSequenceEntry) {
		getSqliteSequence().add(SqlLiteSequenceEntry);
	}
	
	public int getID() {
		return id;
	}
	
	private void setID(int id) {
		this.id = id;
	}
	
	public int getMessageCount() {
		return getMessages().size();
	}
	
	private void setDatabasePath(String databasePath) {
		this.databasePath = databasePath;
	}
	
	public String getDatabasePath() {
		return this.databasePath;
	}

	public MessagesEntry getMessage(int index) {
		return getMessages().get(index);
	}

	public ArrayList<ChatListEntry> getChatList() {
		return chatList;
	}

	private void setChatList(ArrayList<ChatListEntry> chatList) {
		this.chatList = chatList;
	}

	public ArrayList<GroupParticipantsEntry> getGroupParticipants() {
		return groupParticipants;
	}

	private void setGroupParticipants(ArrayList<GroupParticipantsEntry> groupParticipants) {
		this.groupParticipants = groupParticipants;
	}

	public ArrayList<GroupParticipantsHistoryEntry> getGroupParticipantsHistory() {
		return groupParticipantsHistory;
	}

	private void setGroupParticipantsHistory(ArrayList<GroupParticipantsHistoryEntry> groupParticipantsHistory) {
		this.groupParticipantsHistory = groupParticipantsHistory;
	}

	public ArrayList<MediaRefsEntry> getMediaRefs() {
		return mediaRefs;
	}

	private void setMediaRefs(ArrayList<MediaRefsEntry> mediaRefs) {
		this.mediaRefs = mediaRefs;
	}

	public ArrayList<MessagesEntry> getMessages() {
		return messages;
	}
	
	private void setMessages(ArrayList<MessagesEntry> messages) {
		this.messages = messages;
	}

	public ArrayList<MessagesFtsContentEntry> getMessagesFtscontent() {
		return messagesFtscontent;
	}

	private void setMessagesFtscontent(ArrayList<MessagesFtsContentEntry> messagesFtscontent) {
		this.messagesFtscontent = messagesFtscontent;
	}

	public ArrayList<MessagesFtsSegdirEntry> getMessagesFtsSegdir() {
		return messagesFtsSegdir;
	}

	private void setMessagesFtsSegdir(ArrayList<MessagesFtsSegdirEntry> messagesFtsSegdir) {
		this.messagesFtsSegdir = messagesFtsSegdir;
	}

	public ArrayList<MessagesFtsSegmentsEntry> getMessagesFtsSegments() {
		return messagesFtsSegments;
	}

	private void setMessagesFtsSegments(ArrayList<MessagesFtsSegmentsEntry> messagesFtsSegments) {
		this.messagesFtsSegments = messagesFtsSegments;
	}

	public ArrayList<PropsEntry> getProps() {
		return props;
	}

	private void setProps(ArrayList<PropsEntry> props) {
		this.props = props;
	}

	public ArrayList<ReceiptsEntry> getReceipts() {
		return receipts;
	}

	private void setReceipts(ArrayList<ReceiptsEntry> receipts) {
		this.receipts = receipts;
	}

	public ArrayList<SqliteSequenceEntry> getSqliteSequence() {
		return sqliteSequence;
	}

	private void setSqliteSequence(ArrayList<SqliteSequenceEntry> sqliteSequence) {
		this.sqliteSequence = sqliteSequence;
	}

	public boolean isMessageInDatabase(MessagesEntry message) {
		
		MessagesEntry thisMessage;
		
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
		
		MessagesEntry messageOne;
		MessagesEntry messageTwo;
		long messageOneTimeStamp = 0;
		long messageTwoTimeStamp = 0;
		MessagesEntry messageToCheck;
		
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
