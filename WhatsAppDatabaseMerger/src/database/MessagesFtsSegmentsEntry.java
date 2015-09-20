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
	
}
