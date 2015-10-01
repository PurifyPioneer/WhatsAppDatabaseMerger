package database;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

public class MessagesFtsSegmentsEntry {

	private int blockid;
	private Blob block;
	
	public MessagesFtsSegmentsEntry(int blockid, byte[] block) {
		
		setBlockid(blockid);
		setBlock(block);
		
	}

	public int getBlockid() {
		return blockid;
	}

	private void setBlockid(int blockid) {
		this.blockid = blockid;
	}

	public Blob getBlock() {
		return block;
	}

	private void setBlock(byte[] block) {
		if (block != null) {
			try {
				this.block = new SerialBlob(block);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + blockid;
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
		MessagesFtsSegmentsEntry other = (MessagesFtsSegmentsEntry) obj;
		if (block == null) {
			if (other.block != null)
				return false;
		} else if (!block.equals(other.block))
			return false;
		if (blockid != other.blockid)
			return false;
		return true;
	}
	
}
