package com.bendude56.dungeonman.world.gen;

import java.util.ArrayList;
import java.util.Random;

import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadataDoor;

public abstract class WorldFeature {
	public abstract boolean checkLocation(WorldLocation l, int orientation);
	public abstract WorldFeatureInfo generateAt(DoorType door, WorldLocation l, int orientation, Random random);
	
	public void setDoor(WorldLocation l, DoorType door) {
		if (door == DoorType.NONE) {
			l.setTile(Tile.stoneFloor);
		} else if (door == DoorType.NORMAL){
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
			boolean locked = (random.nextInt(4) == 0);
			
			if (random.nextInt(5) == 0) {
				return (locked) ? DoorType.SECRET_LOCKED : DoorType.SECRET;
			} else {
				return (locked) ? DoorType.LOCKED : DoorType.NORMAL;
			}
		}
	}
}
