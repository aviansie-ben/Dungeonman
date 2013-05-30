package com.bendude56.dungeonman;

import java.util.HashMap;
import java.util.Random;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.entity.EntityStats;
import com.bendude56.dungeonman.item.Item;
import com.bendude56.dungeonman.item.ItemGoldCoin;
import com.bendude56.dungeonman.item.ItemKey;
import com.bendude56.dungeonman.item.ItemPotion;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.gen.SimpleDungeonGenerator;

public class GameInstance {
	private static GameInstance activeInstance;
	
	public static GameInstance getActiveInstance() {
		return activeInstance;
	}
	
	public static GameInstance createNewGame(int difficulty, EntityStats stats) {
		activeInstance = new GameInstance();
		activeInstance.difficulty = difficulty;
		activeInstance.populateItems();
		activeInstance.generateFloor(1);
		
		activeInstance.player = new EntityPlayer(activeInstance.getFloor(1).getEntryLocation(), stats);
		activeInstance.getFloor(1).addEntity(activeInstance.player);
		
		return activeInstance;
	}
	
	public static World getActiveWorld() {
		return activeInstance.getFloor(activeInstance.getCurrentFloor());
	}
	
	private int nextEntityId = 1;
	private int nextKeyId = 1;
	private HashMap<Integer, World> floors = new HashMap<Integer, World>();
	private HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	private EntityPlayer player;
	private HashMap<Integer, Boolean> itemIdentified = new HashMap<Integer, Boolean>();
	private int difficulty;
	private Random random = new Random();
	
	public GameInstance() {
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
		floors.put(floor, new World(200, 200, floor));
		new SimpleDungeonGenerator(floors.get(floor)).generateLevel(difficulty);
	}
	
	public int getCurrentFloor() {
		return player.getWorld().getFloor();
	}
	
	public boolean isItemIdentified(Item item) {
		return itemIdentified.get(item.getItemId());
	}
	
	public void setItemIdentified(Item item, boolean identified) {
		itemIdentified.put(item.getItemId(), identified);
	}
	
	public void teleport(Entity e, WorldLocation l) {
		e.getWorld().removeEntity(e);
		e.setLocation(l);
		e.getWorld().addEntity(e);
	}
	
	public int generateEntityId() {
		return nextEntityId++;
	}
	
	public int generateKeyId() {
		return nextKeyId++;
	}
	
	public Item getItem(int id) {
		return items.get(id);
	}
	
	private void populateItems() {
		int numPotions = 10 + random.nextInt(11);
		items.put(1, Item.goldCoin = new ItemGoldCoin(1));
		items.put(2, Item.key = new ItemKey(2));
		
		Item.potions = new ItemPotion[numPotions];
		for (int i = 0; i < numPotions; i++) {
			items.put(3 + i, Item.potions[i] = ItemPotion.generateNewPotion(3 + i, difficulty, random));
		}
	}
}
