package com.bendude56.dungeonman.item;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.gfx.ImageUtil;

public abstract class ItemPotion extends ItemConsumable {
	public static final Image potionSprite = ImageUtil.loadImage("/entity/item/potion.png");
	
	public static final String[] adjectives = new String[] { "Bubbly", "Thick", "Oily", "Foggy", "Hazy", "Fizzy", "Murky" };
	public static final String[] colorNames = new String[] { "Black", "White", "Green", "Purple", "Blue", "Red", "Cyan", "Yellow" };
	public static final Color[] colors = new Color[] { Color.black, Color.white, Color.green, Color.magenta, Color.blue, Color.red, Color.cyan, Color.yellow };
	
	private Image sprite;
	private String name;
	private String unidentifiedName;
	
	public ItemPotion(String name, String unidentifiedName, Color c, int id, int rarity) {
		super(id, rarity);
		
		this.name = name;
		this.unidentifiedName = unidentifiedName;
		this.sprite = ImageUtil.colorPotion(potionSprite, c);
	}

	@Override
	public Image getDrawImage(ItemStack stack) {
		return sprite;
	}

	@Override
	public String getItemName(ItemStack stack) {
		return GameInstance.getActiveInstance().isItemIdentified(this) ? name : unidentifiedName;
	}

	@Override
	public int getMaxStack(ItemStack stack) {
		return 1;
	}
	
	public static ItemPotion generateNewPotion(int id, int difficulty, Random random) {
		String adjective = adjectives[random.nextInt(adjectives.length)];
		int color = random.nextInt(colors.length);
		int type = random.nextInt(2);
		
		if (type == 0) {
			// Healing potion
			return new ItemHealingPotion(adjective + " " + colorNames[color] + " Potion", colors[color], id, 100, random.nextInt(96) + 5);
		} else if (type == 1) {
			// Damage potion
			return new ItemDamagePotion(adjective + " " + colorNames[color] + " Potion", colors[color], id, 100, random.nextInt(96) + 5);
		} else {
			// Should never happen
			return null;
		}
	}

	@Override
	public int getWeight(ItemStack stack) {
		return 200;
	}

}
