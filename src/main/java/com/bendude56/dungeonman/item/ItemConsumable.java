package com.bendude56.dungeonman.item;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * @author Benjamin C. Thomas
 */

public abstract class ItemConsumable extends Item {

	public ItemConsumable(int id, int rarity, int generatorMax) {
		super(id, rarity, generatorMax);
	}

	@Override
	public boolean canUse(ItemStack stack, Entity e) {
		return e instanceof EntityPlayer;
	}

	@Override
	public boolean onUsed(ItemStack stack, Entity e) {
		if (canUse(stack, e)) {
			onConsumed(stack, (EntityPlayer)e);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
		return true;
	}
	
	/**
	 * Executed when this item is consumed by the player.
	 * 
	 * @param p The player who has consumed the item.
	 */
	public abstract void onConsumed(ItemStack stack, EntityPlayer p);

}
