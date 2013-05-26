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
	public boolean canUse(ItemMetadata m, Entity e) {
		return e instanceof EntityPlayer;
	}

	@Override
	public boolean onUsed(ItemMetadata m, Entity e) {
		if (canUse(m, e)) {
			onConsumed(m, (EntityPlayer)e);
			return true;
		} else {
			return false;
		}
	}
	
	public abstract void onConsumed(ItemMetadata m, EntityPlayer p);

}
