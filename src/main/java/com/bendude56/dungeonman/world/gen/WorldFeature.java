package com.bendude56.dungeonman.world.gen;

import java.util.ArrayList;
import java.util.Random;

import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadataDoor;

/**
 * @author Benjamin C. Thomas
 */

public abstract class WorldFeature {
    /**
     * Checks whether the provided location and orientation provide a clear path
     * for this feature to be placed at.
     * 
     * @param l The location to check.
     * @param orientation The orientation of the feature to check.
     * 
     * @return True if the area is free, false otherwise.
     */
    public abstract boolean checkLocation(WorldLocation l, int orientation);
    
    /**
     * Generates this feature with the specified parameters.
     * 
     * @param door The type of door to be generated with this feature.
     * @param l The location at which to generate the feature.
     * @param orientation The orientation that the feature should be generated
     *            with.
     * @param random The randomizer to provide random locations with.
     * 
     * @return A structure containing information about the feature's final
     *         parameters.
     */
    public abstract WorldFeatureInfo generateAt(DoorType door, WorldLocation l, int orientation, Random random);
    
    /**
     * Sets the specified door type at the requested location.
     * 
     * @param l The location to be set.
     * @param door The type of door that should be created.
     */
    public void setDoor(WorldLocation l, DoorType door) {
        if (door == DoorType.NONE) {
            l.setTile(Tile.stoneFloor);
        } else if (door == DoorType.NORMAL) {
            l.setTile(Tile.door);
            l.setMetadata(new TileMetadataDoor(-1));
        } else if (door == DoorType.SECRET) {
            l.setTile(Tile.secretDoor);
            l.setMetadata(new TileMetadataDoor(-1));
        } else if (door == DoorType.LOCKED) {
            l.setTile(Tile.door);
            l.setMetadata(new TileMetadataDoor(l.world.getGameInstance().generateKeyId()));
        } else if (door == DoorType.SECRET_LOCKED) {
            l.setTile(Tile.secretDoor);
            l.setMetadata(new TileMetadataDoor(l.world.getGameInstance().generateKeyId()));
        }
    }
    
    public static class WorldFeatureInfo {
        public ArrayList<WorldLocation> walls = new ArrayList<WorldLocation>();
        public ArrayList<Integer> wallOrientations = new ArrayList<Integer>();
        public ArrayList<WorldLocation> possibleItems = new ArrayList<WorldLocation>();
        public ArrayList<WorldLocation> possibleStairs = new ArrayList<WorldLocation>();
        public ArrayList<WorldLocation> possibleMonsters = new ArrayList<WorldLocation>();
    }
    
    public enum DoorType {
        NONE, NORMAL, SECRET, LOCKED, SECRET_LOCKED;
        
        public static DoorType getRandomType(Random random) {
            boolean locked = (random.nextInt(6) == 0);
            
            if (!locked && random.nextInt(2) == 0) {
                return DoorType.NONE;
            } else if (random.nextInt(10) == 0) {
                return (locked) ? DoorType.SECRET_LOCKED : DoorType.SECRET;
            } else {
                return (locked) ? DoorType.LOCKED : DoorType.NORMAL;
            }
        }
    }
}
