package com.bendude56.dungeonman;

import java.util.HashMap;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.item.Item;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;

public class GameInstance {
	private int nextEntityId = 1;
	private HashMap<Integer, World> floors = new HashMap<Integer, World>();
	private HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	private EntityPlayer player;
	
	public GameInstance() {
		populateItems();
	}
	
	public EntityPlayer getPlayerEntity() {
		return player;
	}
	
	public boolean isFloorGenerated(int floor) {
		return floors.containsKey(floor);
	}
	
	public World getFloor(int floor) {
		return floors.get(floor);
	}
	
	public void generateFloor(int floor) {
		// TODO: Generate world
		floors.put(floor, null);
	}
	
	public int getCurrentFloor(int floor) {
		return player.getWorld().getFloor();
	}
	
	public void teleport(Entity e, WorldLocation l) {
		e.getWorld().removeEntity(e);
		e.setLocation(l);
		e.getWorld().addEntity(e);
	}
	
	public int generateEntityId() {
		return nextEntityId++;
	}
	
	public Item getItem(int id) {
		return items.get(id);
	}
	
	private void populateItems() {
		
	}
}
