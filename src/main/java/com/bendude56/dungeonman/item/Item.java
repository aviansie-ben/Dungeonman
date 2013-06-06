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
	
	public Item(int id, int rarity, int generatorMax) {
		this.id = id;
		this.rarity = rarity;
		this.generatorMax = generatorMax;
	}
	
	/**
	 * Gets the unique item ID representing this item.
	 */
	public final int getItemId() {
		return id;
	}
	
	/**
	 * Gets the item's relative rarity. Higher numbers are generate more often.
	 */
	public final int getItemRarity() {
		return rarity;
	}
	
	/**
	 * Gets the maximum amount of this item that can be generated in one stack
	 * by the random dungeon generator.
	 */
	public final int getGeneratorMaxStack() {
		return generatorMax;
	}
	
	/**
	 * Gets the image used to represent the supplied {@link ItemStack}
	 */
	public abstract Image getDrawImage(ItemStack stack);
	
	/**
	 * Checks whether the specified entity is capable of using this
	 * {@link ItemStack}.
	 * 
	 * @param e The entity to check against.
	 * 
	 * @return True if the item is usable, false otherwise.
	 */
	public abstract boolean canUse(ItemStack stack, Entity e);
	
	/**
	 * Gets the name that should be displayed by the specified
	 * {@link ItemStack}.
	 */
	public abstract String getItemName(ItemStack stack);
	
	/**
	 * Gets the maximum amount of this item that can be stored in one stack.
	 */
	public abstract int getMaxStack(ItemStack stack);
	
	/**
	 * Gets the relative weight of one of this specified item.
	 */
	public abstract int getWeight(ItemStack stack);
	
	/**
	 * Executes when the specified entity uses the item.
	 * 
	 * @param e The entity which has used the item.
	 * 
	 * @return True if the use was successful, false otherwise. In general,
	 *         this method should return the same value as that returned by
	 *         {@link #canUse(ItemStack, Entity)}.
	 */
	public abstract boolean onUsed(ItemStack stack, Entity e);
	
	/**
	 * Executes when the player picks up the specified stack of items off of
	 * the ground.
	 * 
	 * @param p The player entity which has picked up the item.
	 * 
	 * @return True if the item was picked up, false otherwise.
	 */
	public abstract boolean onPickedUp(ItemStack stack, EntityPlayer p);

}
