package com.bendude56.dungeonman.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bendude56.dungeonman.entity.EntityDroppedItem;
import com.bendude56.dungeonman.item.Item;
import com.bendude56.dungeonman.item.ItemMetadataKey;
import com.bendude56.dungeonman.item.ItemStack;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.TileMetadataDoor;

/**
 * @author Benjamin C. Thomas
 */

public class KeyGenerator {
    private World world;
    private List<WorldLocation> possibleItems;
    private Random random;
    
    private ArrayList<WorldLocation> checkedLocations;
    private ArrayList<WorldLocation> lastLocations;
    private ArrayList<WorldLocation> itemLocations;
    private ArrayList<WorldLocation> newLocations;
    private WorldLocation door;
    
    private ArrayList<Integer> unlockedDoors = new ArrayList<Integer>();
    
    public KeyGenerator(World world, List<WorldLocation> possibleItems, Random random) {
        this.world = world;
        this.possibleItems = possibleItems;
        this.random = random;
    }
    
    /**
     * Follows paths and generates keys for any locked doors where a player
     * would be able to reach them.
     */
    public void generateAllKeys() {
        int key = -1;
        
        do {
            unlockedDoors.add(key = generateKeyForNextDoor());
        } while (key > 0);
    }
    
    private int generateKeyForNextDoor() {
        checkedLocations = new ArrayList<WorldLocation>();
        lastLocations = new ArrayList<WorldLocation>();
        itemLocations = new ArrayList<WorldLocation>();
        newLocations = new ArrayList<WorldLocation>();
        door = null;
        
        lastLocations.add(world.getEntryLocation());
        
        while (lastLocations.size() > 0) {
            newLocations = new ArrayList<WorldLocation>();
            for (WorldLocation l : lastLocations) {
                checkLocation(l.adjustLocation(0, 1));
                checkLocation(l.adjustLocation(0, -1));
                checkLocation(l.adjustLocation(1, 0));
                checkLocation(l.adjustLocation(-1, 0));
            }
            
            lastLocations = newLocations;
        }
        
        if (door == null) {
            // No doors were found to unlock
            return -1;
        } else {
            // Select a location for the key
            try {
                int k = random.nextInt(itemLocations.size());
                
                WorldLocation key = itemLocations.get(k);
                int keyId = ((TileMetadataDoor) door.getMetadata()).getKeyId();
                
                world.addEntity(new EntityDroppedItem(key, new ItemStack(Item.key, new ItemMetadataKey(keyId), 1)));
                itemLocations.remove(k);
                
                return keyId;
            } catch (IllegalArgumentException e) {
                // If there are no possible locations for the key, just leave
                // the door unlocked
                door.setMetadata(new TileMetadataDoor(-1));
                return Integer.MAX_VALUE;
            }
        }
    }
    
    private void checkLocation(WorldLocation l) {
        if (!checkedLocations.contains(l)) {
            if (l.getTile().canItemAlgorithmPass()) {
                newLocations.add(l);
                if (possibleItems.contains(l)) {
                    itemLocations.add(l);
                }
            } else if (l.getMetadata() instanceof TileMetadataDoor) {
                if (!((TileMetadataDoor) l.getMetadata()).isLocked()
                        || unlockedDoors.contains(((TileMetadataDoor) l.getMetadata()).getKeyId())) {
                    newLocations.add(l);
                    if (possibleItems.contains(l)) {
                        itemLocations.add(l);
                    }
                } else if (door == null) {
                    door = l;
                }
            }
        }
        
        checkedLocations.add(l);
    }
}
