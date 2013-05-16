package com.bendude56.dungeonman.world;

import java.util.ArrayList;

import com.bendude56.dungeonman.entity.Entity;

public class World {
	
	public Tile[][] tiles;
	public int width, height;
	public int entryX, entryY;
	public int dungeonLevel;
	
	public ArrayList<Entity> entities;
}
