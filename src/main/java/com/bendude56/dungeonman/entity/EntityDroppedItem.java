package com.bendude56.dungeonman.entity;

import java.awt.Graphics;

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
	public void render(Graphics g, int x, int y) {
		g.drawImage(stack.getItem().getDrawImage(stack), x, y, null);
	}

}
