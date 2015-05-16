package com.bendude56.dungeonman.item;

/**
 * @author Benjamin C. Thomas
 */

public class ItemMetadata {
    
    /**
     * Makes an exact copy of this metadata. Modifying this copy MUST NOT affect
     * the original.
     */
    @Override
    public ItemMetadata clone() {
        return new ItemMetadata();
    }
    
    @Override
    public boolean equals(Object o) {
        return o.getClass() == ItemMetadata.class;
    }
    
}
