package com.bendude56.dungeonman.world;

import java.util.ArrayList;

import com.bendude56.dungeonman.entity.Entity;

public class World {
	
	public Tile[][] tiles;
	public int width, height;
	public int entryX, entryY;
	public int dungeonLevel;
	
	public ArrayList<Entity> entities;
	
	public boolean isAvailable(WorldLocation l1, WorldLocation l2) {
		return isAvailable(l1.x, l1.y, l2.x, l2.y);
	}
	
	public boolean isAvailable(int x1, int y1, int x2, int y2) {
		int temp;
		
		// Make sure x1 < x2 and y1 < y2
		if (x1 > x2) {
			temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2) {
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		// No space is available outside of world bounds
		if (y1 < 0 || y2 >= height || x1 < 0 || x2 >= width) {
			return false;
		}
		
		// If any tile within the bounds isn't a wall, the area is unavailable
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				if (!(tiles[x][y] instanceof TileWall))
					return false;
			}
		}
		
		return true;
	}
	
}
