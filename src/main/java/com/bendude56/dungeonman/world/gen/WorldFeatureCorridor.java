package com.bendude56.dungeonman.world.gen;

import java.util.Random;

import com.bendude56.dungeonman.world.Tile;
import com.bendude56.dungeonman.world.WorldLocation;

public class WorldFeatureCorridor extends WorldFeature {
	public int corridorLength;
	
	public WorldFeatureCorridor(int corridorLength) {
		this.corridorLength = corridorLength;
	}

	@Override
	public boolean checkLocation(WorldLocation l, int orientation) {
		return l.world.isAvailable(l.adjustLocation(-1, corridorLength, orientation), l.adjustLocation(1, 0, orientation));
	}

	@Override
	public WorldFeatureInfo generateAt(DoorType door, WorldLocation l, int orientation, Random random) {
		WorldFeatureInfo info = new WorldFeatureInfo();
		
		for (int i = 0; i < corridorLength; i++) {
			l.setTile(Tile.stoneFloor);
			
			info.walls.add(l.adjustLocation(1, 0, orientation));
			info.wallOrientations.add((orientation + 1) % 4);
			
			info.walls.add(l.adjustLocation(-1, 0, orientation));
			info.wallOrientations.add((orientation + 3) % 4);
			
			l = l.adjustLocation(0, 1, orientation);
		}
		
		info.walls.add(l);
		info.wallOrientations.add(orientation);
		
		return info;
	}

}
