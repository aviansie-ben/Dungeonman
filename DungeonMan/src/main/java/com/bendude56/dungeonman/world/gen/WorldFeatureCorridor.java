package com.bendude56.dungeonman.world.gen;

import com.bendude56.dungeonman.world.Tile;
import com.bendude56.dungeonman.world.TileWall;
import com.bendude56.dungeonman.world.WorldLocation;

public class WorldFeatureCorridor extends WorldFeature {
	public int corridorLength;
	
	public WorldFeatureCorridor(int corridorLength) {
		this.corridorLength = corridorLength;
	}

	@Override
	public boolean checkLocation(WorldLocation l, int orientation) {
		for (int i = 0; i < corridorLength; i++) {
			l = l.adjustLocation(0, 1, orientation);
			
			// The corridor must not cross or go directly beside an existing room
			if (!(l.getTile() instanceof TileWall) || !(l.adjustLocation(1, 0, orientation).getTile() instanceof TileWall) || !(l.adjustLocation(-1, 0, orientation).getTile() instanceof TileWall)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public WorldFeatureInfo generateAt(DoorType door, WorldLocation l, int orientation) {
		WorldFeatureInfo info = new WorldFeatureInfo();
		
		l.setTile(Tile.stoneFloor);
		
		for (int i = 0; i < corridorLength; i++) {
			l = l.adjustLocation(0, 1, orientation);
			
			l.setTile(Tile.stoneFloor);
			
			info.walls.add(l.adjustLocation(1, 0, orientation));
			info.wallOrientations.add((orientation + 1) % 4);
			
			info.walls.add(l.adjustLocation(-1, 0, orientation));
			info.wallOrientations.add((orientation + 3) % 4);
		}
		
		info.walls.add(l.adjustLocation(0, 1, orientation));
		info.wallOrientations.add(orientation);
		
		return info;
	}

}
