package com.bendude56.dungeonman.item;

/**
 * @author Benjamin C. Thomas
 */

public class ItemMetadataKey extends ItemMetadata {
    private int keyId;
    private int floor;
    
    public ItemMetadataKey(int keyId, int floor) {
        this.keyId = keyId;
        this.floor = floor;
    }
    
    /**
     * Gets the unique identifier of the key represented by this metadata.
     */
    public int getKeyId() {
        return keyId;
    }
    
    public int getFloor() {
        return floor;
    }
    
    @Override
    public ItemMetadata clone() {
        return new ItemMetadataKey(keyId, floor);
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemMetadataKey other = (ItemMetadataKey) obj;
        if (keyId != other.keyId)
            return false;
        return true;
    }
}
