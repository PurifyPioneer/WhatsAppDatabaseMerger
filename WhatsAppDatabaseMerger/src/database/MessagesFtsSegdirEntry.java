package database;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

public class MessagesFtsSegdirEntry {

	private int level;
	private int idx;
	private int start_block;
	private int leaves_end_block;
	private int end_block;
	private Blob root;
	
	public MessagesFtsSegdirEntry(int level, int idx, int start_block, int leaves_end_block, int end_block, byte[] root) {
		
		setLevel(level);
		setIdx(idx);
		setStart_block(start_block);
		setLeaves_end_block(leaves_end_block);
		setEnd_block(end_block);
		setRoot(root);
		
	}

	public int getLevel() {
		return level;
	}

	private void setLevel(int level) {
		this.level = level;
	}

	public int getIdx() {
		return idx;
	}

	private void setIdx(int idx) {
		this.idx = idx;
	}

	public int getStart_block() {
		return start_block;
	}

	private void setStart_block(int start_block) {
		this.start_block = start_block;
	}

	public int getLeaves_end_block() {
		return leaves_end_block;
	}

	private void setLeaves_end_block(int leaves_end_block) {
		this.leaves_end_block = leaves_end_block;
	}

	public int getEnd_block() {
		return end_block;
	}

	private void setEnd_block(int end_block) {
		this.end_block = end_block;
	}

	public Blob getRoot() {
		return root;
	}

	private void setRoot(byte[] root) {
		if (root != null) {
			try {
				this.root = new SerialBlob(root);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end_block;
		result = prime * result + idx;
		result = prime * result + leaves_end_block;
		result = prime * result + level;
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result + start_block;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessagesFtsSegdirEntry other = (MessagesFtsSegdirEntry) obj;
		if (end_block != other.end_block)
			return false;
		if (idx != other.idx)
			return false;
		if (leaves_end_block != other.leaves_end_block)
			return false;
		if (level != other.level)
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (start_block != other.start_block)
			return false;
		return true;
	}
	
}
