package com.bendude56.dungeonman.item;

import java.awt.Image;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.gfx.ImageUtil;

/**
 * @author Benjamin C. Thomas
 */

public class ItemKey extends Item {
	public static final Image keySprite = ImageUtil.loadImage("/entity/item/key.png");

	public ItemKey(int id) {
		super(id, -1, 0);
	}

	@Override
	public Image getDrawImage(ItemStack stack) {
		return keySprite;
	}

	@Override
	public boolean canUse(ItemStack stack, Entity e) {
		return false;
	}

	@Override
	public String getItemName(ItemStack stack) {
		return "Key";
	}

	@Override
	public int getMaxStack(ItemStack stack) {
		return 1;
	}

	@Override
	public int getWeight(ItemStack stack) {
		return 2;
	}

	@Override
	public boolean onUsed(ItemStack stack, Entity e) {
		return false;
	}

	@Override
	public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
		return true;
	}

}
