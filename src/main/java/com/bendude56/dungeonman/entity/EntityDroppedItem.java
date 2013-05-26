package com.bendude56.dungeonman.entity;

import java.awt.Image;

import com.bendude56.dungeonman.item.ItemStack;
import com.bendude56.dungeonman.world.WorldLocation;

public class EntityDroppedItem extends Entity {
	
	private ItemStack stack;

	public EntityDroppedItem(WorldLocation l, ItemStack stack) {
		super(l);
		
		this.stack = stack;
	}

	@Override
	public void doTurn() {
		// Do nothing
	}

	@Override
	public void doAction(ActionType type, Entity e) {
		// TODO: React to player pickup
	}
	
	public ItemStack getItemStack() {
		return stack;
	}

	@Override
	public Image getDrawImage() {
		return stack.getItem().getDrawImage(stack);
	}

}
