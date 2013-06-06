package com.bendude56.dungeonman.world.tile;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class TileMetadataDoor extends TileMetadata {
	private final int keyId;
	
	public TileMetadataDoor(int keyId) {
		this.keyId = keyId;
	}
	
	public boolean isLocked() {
		return keyId > 0;
	}
	
	public int getKeyId() {
		return keyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + keyId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		TileMetadataDoor other = (TileMetadataDoor)obj;
		if (keyId != other.keyId)
			return false;
		return true;
	}
	
	public TileMetadata clone() {
		return new TileMetadataDoor(keyId);
	}
}
