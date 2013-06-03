package com.bendude56.dungeonman.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.EntityDroppedItem;
import com.bendude56.dungeonman.item.Item;
import com.bendude56.dungeonman.item.ItemMetadata;
import com.bendude56.dungeonman.item.ItemStack;
import com.bendude56.dungeonman.world.WorldLocation;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class ItemPopulator {
	private List<WorldLocation> possibleLocations;
	private Random random;
	
	public ItemPopulator(List<WorldLocation> possibleLocations, Random random) {
		this.possibleLocations = possibleLocations;
		this.random = random;
	}
	
	public void generateAllItems() {
		for (WorldLocation l : possibleLocations) {
			if (random.nextInt(3) == 0) {
				l.world.addEntity(new EntityDroppedItem(l, selectItem()));
			}
		}
	}
	
	public ItemStack selectItem() {
		ArrayList<Item> items = new ArrayList<Item>();
		
		for (Entry<Integer, Item> item : GameInstance.getActiveInstance().getItems().entrySet()) {
			for (int i = 0; i < item.getValue().getItemRarity(); i++) {
				items.add(item.getValue());
			}
		}
		
		Item item = items.get(random.nextInt(items.size()));
		
		return new ItemStack(item, new ItemMetadata(), (item.getGeneratorMaxStack() == 1) ? 1 : 1 + random.nextInt(item.getGeneratorMaxStack() - 1));
	}
}
