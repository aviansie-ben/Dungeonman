package com.bendude56.dungeonman.item;

import java.awt.Color;

import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * @author Benjamin C. Thomas
 */

public class ItemHealingPotion extends ItemPotion {
	
	private int healingAmount;

	public ItemHealingPotion(String unidentifiedName, Color c, int id, int rarity, int healingAmount, int identifyLevel) {
		super("Potion of Healing (+" + healingAmount + ")", unidentifiedName, c, id, rarity, identifyLevel);
		
		this.healingAmount = healingAmount;
	}

	@Override
	public void onConsumed(ItemStack stack, EntityPlayer p) {
		p.logMessage("You drink the " + getItemName(stack));
		p.logMessage("You regain " + healingAmount + "hp");
		
		p.doDamage(-healingAmount);
	}

	@Override
	public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
		return true;
	}

}
