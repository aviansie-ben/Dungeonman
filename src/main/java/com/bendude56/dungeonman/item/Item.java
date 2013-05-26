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

public abstract class Item {
	
	private final int id;
	private final int rarity;
	
	public int getItemId() {
		return id;
	}
	
	public int getItemRarity() {
		return rarity;
	}
	
	public Item(int id, int rarity) {
		this.id = id;
		this.rarity = rarity;
	}
	
	public abstract Image getDrawImage(ItemStack stack);
	public abstract boolean canUse(ItemStack stack, Entity e);
	public abstract String getItemName(ItemStack stack);
	public abstract int getMaxStack(ItemStack stack);
	public abstract int getWeight(ItemStack stack);
	public abstract boolean onUsed(ItemStack stack, Entity e);
	public abstract boolean onPickedUp(ItemStack stack, EntityPlayer p);

}
