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
	public boolean doAction(ActionType type, Entity e) {
		if (type == ActionType.MOVE && e instanceof EntityPlayer) {
			((EntityPlayer)e).logMessage("You see a " + stack.getItem().getItemName(stack) + " (x" + stack.getAmount() + ")");
		} else if (type == ActionType.PICKUP && e instanceof EntityPlayer) {
			return ((EntityPlayer)e).doPickup(this);
		}
		
		return true;
	}
	
	public ItemStack getItemStack() {
		return stack;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(stack.getItem().getDrawImage(stack), x, y, null);
	}

}
