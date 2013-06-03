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
	public static ItemGoldCoin goldCoin;
	public static ItemPotion[] potions;
	public static ItemKey key;
	
	private final int id;
	private final int rarity;
	private final int generatorMax;
	
	public final int getItemId() {
		return id;
	}
	
	public final int getItemRarity() {
		return rarity;
	}
	
	public final int getGeneratorMaxStack() {
		return generatorMax;
	}
	
	public Item(int id, int rarity, int generatorMax) {
		this.id = id;
		this.rarity = rarity;
		this.generatorMax = generatorMax;
	}
	
	public abstract Image getDrawImage(ItemStack stack);
	public abstract boolean canUse(ItemStack stack, Entity e);
	public abstract String getItemName(ItemStack stack);
	public abstract int getMaxStack(ItemStack stack);
	public abstract int getWeight(ItemStack stack);
	public abstract boolean onUsed(ItemStack stack, Entity e);
	public abstract boolean onPickedUp(ItemStack stack, EntityPlayer p);

}
