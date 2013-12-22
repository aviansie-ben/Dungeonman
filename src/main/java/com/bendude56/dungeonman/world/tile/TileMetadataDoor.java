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
	
	/**
	 * Gets a boolean value representing whether or not this door is locked.
	 */
	public boolean isLocked() {
		return keyId > 0;
	}
	
	/**
	 * Gets the identifier of the key needed to open this door. Calling this on
	 * a door which is not locked will return a keyId <= 0.
	 */
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
	
	@Override
	public TileMetadata clone() {
		return new TileMetadataDoor(keyId);
	}
}
