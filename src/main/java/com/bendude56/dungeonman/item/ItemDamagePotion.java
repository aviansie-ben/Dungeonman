package com.bendude56.dungeonman.item;

import java.awt.Color;

import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class ItemDamagePotion extends ItemPotion {
	
	private int damageAmount;

	public ItemDamagePotion(String unidentifiedName, Color c, int id, int rarity, int damageAmount) {
		super("Potion of Damage (-" + damageAmount + ")", unidentifiedName, c, id, rarity);
		
		this.damageAmount = damageAmount;
	}

	@Override
	public void onConsumed(ItemStack stack, EntityPlayer p) {
		p.logMessage("You drink the " + getItemName(stack));
		p.logMessage("Urk... You take " + damageAmount + " damage");
		
		p.doDamage(damageAmount);
	}

}
