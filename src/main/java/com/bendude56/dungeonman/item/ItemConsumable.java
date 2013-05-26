package com.bendude56.dungeonman.item;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public abstract class ItemConsumable extends Item {

	public ItemConsumable(int id, int rarity) {
		super(id, rarity);
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
	
	public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
		return true;
	}
	
	public abstract void onConsumed(ItemStack stack, EntityPlayer p);

}
