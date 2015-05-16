package com.bendude56.dungeonman.world.tile;

/**
 * @author Benjamin C. Thomas
 */

public class TileMetadata {
    
    /**
     * Makes an exact copy of this metadata. Modifying this copy MUST NOT affect
     * the original.
     */
    @Override
    public TileMetadata clone() {
        return new TileMetadata();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(TileMetadata.class);
    }
}
