package com.bendude56.dungeonman.item;

import java.awt.Image;

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

public class ItemKey extends Item {

	public ItemKey(int id, int rarity) {
		super(id, rarity);
	}

	@Override
	public Image getDrawImage(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canUse(ItemStack stack, Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getItemName(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxStack(ItemStack stack) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWeight(ItemStack stack) {
		return 2;
	}

	@Override
	public boolean onUsed(ItemStack stack, Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
		// TODO Auto-generated method stub
		return false;
	}

}
