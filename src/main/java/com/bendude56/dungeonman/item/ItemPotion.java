package com.bendude56.dungeonman.item;

import java.awt.Color;
import java.awt.Image;

import com.bendude56.dungeonman.gfx.ImageUtil;

public abstract class ItemPotion extends ItemConsumable {
	public static final Image potionSprite = ImageUtil.loadImage("/entity/item/potion.png");
	
	public static final String[] adjectives = new String[] { "Bubbly", "Thick", "Oily", "Foggy", "Ominous", "Fizzy", "Murky" };
	public static final String[] colorNames = new String[] { "Black", "White", "Green", "Purple", "Blue", "Red", "Cyan", "Yellow" };
	public static final Color[] colors = new Color[] { Color.black, Color.white, Color.green, Color.magenta, Color.blue, Color.red, Color.cyan, Color.yellow };
	
	private Image sprite;
	private String name;
	
	public ItemPotion(String name, Color c, int id, int rarity) {
		super(id, rarity);
		sprite = ImageUtil.colorPotion(potionSprite, c);
	}

	@Override
	public Image getDrawImage(ItemMetadata m) {
		return sprite;
	}

	@Override
	public String getItemName(ItemMetadata m) {
		return name;
	}

	@Override
	public int getMaxStack(ItemMetadata m) {
		return 1;
	}
	
	public static ItemPotion generateNewPotion(int id, int difficulty) {
		// TODO: Generate potion
		return null;
	}

}
