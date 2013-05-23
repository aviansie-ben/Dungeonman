package com.bendude56.dungeonman.item;

import java.awt.Image;

import com.bendude56.dungeonman.entity.Entity;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public abstract class Item {
	
	public abstract Image getDrawImage(ItemMetadata m);
	public abstract boolean canUse(ItemMetadata m, Entity e);
	public abstract String getItemName(ItemMetadata m);
	public abstract int getMaxStack(ItemMetadata m);
	public abstract boolean onUsed(ItemMetadata m, Entity e);

}
