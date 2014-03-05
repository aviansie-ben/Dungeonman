package com.bendude56.dungeonman.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.bendude56.dungeonman.item.ItemStack;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.Tile;

/**
 * @author Benjamin C. Thomas
 */

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
	
	/**
	 * Gets the item that is represented by this entity.
	 * 
	 * @return An {@link ItemStack} representing the item which was dropped to
	 *         create this entity.
	 */
	public ItemStack getItemStack() {
		return stack;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		Image i = stack.getItem().getDrawImage(stack);
		int yo, xo;
		
		yo = (Tile.TILE_HEIGHT - i.getHeight(null)) / 2;
		xo = (Tile.TILE_WIDTH - i.getWidth(null)) / 2;
		
		g.drawImage(i, x + xo, y + yo, null);
	}

}
