package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

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
 * Representation of a whatsapp database. (msgstore database)
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class DatabaseContainer {

	// TODO SEPARATE Methods

	private int id;

	private ArrayList<ChatListEntry> chatList;
	private ArrayList<GroupParticipantsEntry> groupParticipants;
	private ArrayList<GroupParticipantsHistoryEntry> groupParticipantsHistory;
	private ArrayList<MediaRefsEntry> mediaRefs;
	private ArrayList<MessagesEntry> messages;
	private ArrayList<MessagesFtsContentEntry> messagesFtsContent;
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
		setMessagesFtsContent(new ArrayList<MessagesFtsContentEntry>());
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
		getMessagesFtsContent().add(messagesFtsContentEntry);
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

	public ArrayList<MessagesFtsContentEntry> getMessagesFtsContent() {
		return messagesFtsContent;
	}

	private void setMessagesFtsContent(ArrayList<MessagesFtsContentEntry> messagesFtsContent) {
		this.messagesFtsContent = messagesFtsContent;
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

	/**
	 * @param The
	 *            database you want to compare to.
	 * @return The compared database (including all fixes[messages, ..])
	 */
	public DatabaseContainer compareDatabase(DatabaseContainer database) {

		DatabaseContainer tempDB = new DatabaseContainer(Integer.MAX_VALUE);

		ArrayList<ChatListEntry> chatList = compareChatList(database.getChatList());
		ArrayList<GroupParticipantsEntry> groupParticipants = compareGroupParticipants(database.getGroupParticipants());
		ArrayList<GroupParticipantsHistoryEntry> groupParticipantsHistory = compareGroupParticipantsHistory(
				database.getGroupParticipantsHistory());
		ArrayList<MediaRefsEntry> mediaRefs = compareMediaRefs(database.getMediaRefs());
		ArrayList<MessagesEntry> messages = compareMessages(database.getMessages());
		ArrayList<MessagesFtsContentEntry> messagesFtsContent = compareMessagesFtsContent(
				database.getMessagesFtsContent());
		ArrayList<MessagesFtsSegdirEntry> messagesFtsSegdir = compareMessagesFtsSegdir(database.getMessagesFtsSegdir());
		ArrayList<MessagesFtsSegmentsEntry> messagesFtsSegments = compareMessagesFtsSegments(
				database.getMessagesFtsSegments());
		ArrayList<PropsEntry> props = compareProps(database.getProps());
		ArrayList<ReceiptsEntry> receipts = compareReceipts(database.getReceipts());
		ArrayList<SqliteSequenceEntry> sqliteSequence = compareSqliteSequence(database.getSqliteSequence());

		tempDB.setChatList(chatList);
		tempDB.setGroupParticipants(groupParticipants);
		tempDB.setGroupParticipantsHistory(groupParticipantsHistory);
		tempDB.setMediaRefs(mediaRefs);
		tempDB.setMessages(messages);
		tempDB.setMessagesFtsContent(messagesFtsContent);
		tempDB.setMessagesFtsSegdir(messagesFtsSegdir);
		tempDB.setMessagesFtsSegments(messagesFtsSegments);
		tempDB.setProps(props);
		tempDB.setReceipts(receipts);
		tempDB.setSqliteSequence(sqliteSequence);

		return tempDB;
	}

	private ArrayList<ChatListEntry> compareChatList(ArrayList<ChatListEntry> chatList2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<GroupParticipantsEntry> compareGroupParticipants(
			ArrayList<GroupParticipantsEntry> groupParticipants2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<GroupParticipantsHistoryEntry> compareGroupParticipantsHistory(
			ArrayList<GroupParticipantsHistoryEntry> groupParticipantsHistory2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<MediaRefsEntry> compareMediaRefs(ArrayList<MediaRefsEntry> mediaRefs2) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO FIX DUPLICATES ERROR
	private ArrayList<MessagesEntry> compareMessages(ArrayList<MessagesEntry> messagesTwo) {

		// Nachrichten vergleichen, so dass am ende eine tabelle rauskommt in
		// der jede nachricht einmal enthalten ist.

		ArrayList<MessagesEntry> smallDB;
		ArrayList<MessagesEntry> bigDB;

		MessagesEntry messageOne = null;
		MessagesEntry messageTwo = null;

		if (this.getMessages().size() >= messagesTwo.size()) {
			bigDB = this.getMessages();
			smallDB = messagesTwo;
		} else {
			bigDB = messagesTwo;
			smallDB = this.getMessages();
		}

		ArrayList<MessagesEntry> tempDB = new ArrayList<MessagesEntry>(bigDB.size());

		// Nachrichten vergleichen
		// TODO implement better count
		
		//TODO HOW DOES WHATSAPP GENERATE KEY_ID ?!
		for (int i = 0; i < smallDB.size(); i++) {

			if (i % 100 == 0) {
				System.out.println("Compared: " + i);
			}

			if (!smallDB.get(i).getMessageAlreadyChecked()) {
				messageOne = smallDB.get(i);
				messageTwo = bigDB.get(i);
			}

			if (messageOne.equals(messageTwo)) {
				if (!messageOne.getMessageAlreadyChecked()) {
					tempDB.add(messageOne);
					messageOne.setMessageAlreadyChecked(true);
					messageTwo.setMessageAlreadyChecked(true);
				}
			} else {
				if (!messageOne.getMessageAlreadyChecked()) {
					tempDB.add(messageOne);
					messageOne.setMessageAlreadyChecked(true);
					messageOne.existsInDatabase(i, bigDB);

				}
			}
		}

		MessagesEntry messageToCheck;
		for (int i = 0; i < bigDB.size(); i++) {
			messageToCheck = bigDB.get(i);

			if (i % 10000 == 0) {
				System.out.println("Checked: " + i);
			}

			if (!messageToCheck.getMessageAlreadyChecked()) {
				if (messageToCheck.existsInDatabase(tempDB)) {
				} else {
					tempDB.add(messageToCheck);
				}
				messageToCheck.setMessageAlreadyChecked(true);
			}
		}

		tempDB = checkDatabase(tempDB);

		return bigDB;
	}

	private ArrayList<MessagesEntry> checkDatabase(ArrayList<MessagesEntry> tempDB) {

		class MessageTimeStampComparator implements Comparator<MessagesEntry> {

			@Override
			public int compare(MessagesEntry m1, MessagesEntry m2) {

				long m1TimeStamp = m1.getTimeStamp();
				long m2TimeStamp = m2.getTimeStamp();

				if (m1TimeStamp > m2TimeStamp) {
					if (m2.getStatus() != 6) {
						return 1;
					} else {
						return 0;
					}
				} else if (m1TimeStamp < m2TimeStamp) {
					if (m2.getStatus() != 6) {
						return -1;
					} else {
						return 0;
					}
				}

				return 0;
			}
		}

		Collections.sort(tempDB, new MessageTimeStampComparator());

		MessagesEntry messageOne;
		MessagesEntry messageTwo;

		int messageOneID;
		int messageTwoID;

		for (int i = 1; i < tempDB.size(); i++) {

			messageOne = tempDB.get(i - 1);
			messageTwo = tempDB.get(i);

			messageOneID = messageOne.get_id();
			messageTwoID = messageTwo.get_id();

			if (messageOneID >= messageTwoID) {
				do {
					messageTwo.increment_id();
				} while (messageOneID >= messageTwo.get_id());
			}
		}

		return tempDB;
	}

	private ArrayList<MessagesFtsContentEntry> compareMessagesFtsContent(
			ArrayList<MessagesFtsContentEntry> messagesFtsContent2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<MessagesFtsSegdirEntry> compareMessagesFtsSegdir(
			ArrayList<MessagesFtsSegdirEntry> messagesFtsSegdir2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<MessagesFtsSegmentsEntry> compareMessagesFtsSegments(
			ArrayList<MessagesFtsSegmentsEntry> messagesFtsSegments2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<PropsEntry> compareProps(ArrayList<PropsEntry> props2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<ReceiptsEntry> compareReceipts(ArrayList<ReceiptsEntry> receipts2) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<SqliteSequenceEntry> compareSqliteSequence(ArrayList<SqliteSequenceEntry> sqliteSequence2) {
		// TODO Auto-generated method stub
		return null;
	}

}
