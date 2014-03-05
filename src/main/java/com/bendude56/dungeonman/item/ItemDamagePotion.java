package com.bendude56.dungeonman.item;

import java.awt.Color;

import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * @author Benjamin C. Thomas
 */

public class ItemDamagePotion extends ItemPotion {
	
	private int damageAmount;

	public ItemDamagePotion(String unidentifiedName, Color c, int id, int rarity, int damageAmount, int identifyLevel) {
		super("Potion of Damage (-" + damageAmount + ")", unidentifiedName, c, id, rarity, identifyLevel);
		
		this.damageAmount = damageAmount;
	}

	@Override
	public void onConsumed(ItemStack stack, EntityPlayer p) {
		p.logMessage("You drink the " + getItemName(stack));
		p.logMessage("Urk... You take " + damageAmount + " damage");
		
		p.doDamage(damageAmount);
	}

}
