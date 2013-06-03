package com.bendude56.dungeonman;

import java.awt.Color;
import java.awt.Image;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.entity.EntityStats;
import com.bendude56.dungeonman.item.Item;
import com.bendude56.dungeonman.item.ItemDamagePotion;
import com.bendude56.dungeonman.item.ItemHealingPotion;
import com.bendude56.dungeonman.item.ItemMetadata;
import com.bendude56.dungeonman.item.ItemPotion;
import com.bendude56.dungeonman.item.ItemStack;
import com.bendude56.dungeonman.item.inventory.Inventory;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;

public class InventoryTest {
	private static World world;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GameInstance.createNewGame(0, new EntityStats(0, 0, 0, 0, 0, 0, 0));
		world = GameInstance.getActiveInstance().getFloor(1);
	}

	@Test
	public void itemStackTest() {
		ItemStack stack = new ItemStack(Item.goldCoin, new ItemMetadata(), 100);
		
		Assert.assertEquals(Item.goldCoin, stack.getItem());
		Assert.assertEquals(new ItemMetadata(), stack.getMetadata());
		Assert.assertEquals(100, stack.getAmount());
		
		try {
			new ItemStack(Item.potions[0], new ItemMetadata(), 10);
			Assert.fail("IllegalArgumentException not thrown for stack too large");
		} catch (IllegalArgumentException e) { }
	}
	
	@Test
	public void potionEffectTest() {
		EntityPlayer player = new EntityPlayer(new WorldLocation(world, 0, 0), new EntityStats(0, 0, 0, 0, 0, 0, 100));
		player.doDamage(5);
		
		Assert.assertEquals(player.getMaxHp() - 5, player.getHp());
		
		ItemPotion p = new ItemHealingPotion("", Color.white, 0, 0, 1);
		p.onConsumed(new ItemStack(p, new ItemMetadata(), 1), player);
		
		Assert.assertEquals(player.getMaxHp() - 4, player.getHp());
		
		p = new ItemDamagePotion("", Color.white, 0, 0, 1);
		p.onConsumed(new ItemStack(p, new ItemMetadata(), 1), player);
		
		Assert.assertEquals(player.getMaxHp() - 5, player.getHp());
	}
	
	@Test
	public void inventoryTest() {
		Inventory inv = new Inventory();
		ItemTest i = new ItemTest(0, 0);
		
		Assert.assertEquals(0, inv.getCurrentWeight());
		Assert.assertEquals(0, inv.getItems().size());
		
		inv.addItem(new ItemStack(i, new ItemMetadata(), 3));
		
		Assert.assertEquals(30, inv.getCurrentWeight());
		Assert.assertEquals(1, inv.getItems().size());
		
		inv.addItem(new ItemStack(i, new ItemMetadata(), 1));
		
		Assert.assertEquals(40, inv.getCurrentWeight());
		Assert.assertEquals(1, inv.getItems().size());
		
		inv.addItem(new ItemStack(i, new ItemMetadata(), 8));
		
		Assert.assertEquals(120, inv.getCurrentWeight());
		Assert.assertEquals(2, inv.getItems().size());
		
		inv.removeItem(inv.getItems().get(0));
		
		Assert.assertEquals(20, inv.getCurrentWeight());
		Assert.assertEquals(1, inv.getItems().size());
	}

	public class ItemTest extends Item {
		public ItemTest(int id, int rarity) {
			super(id, rarity, 1);
		}

		@Override
		public Image getDrawImage(ItemStack stack) {
			return null;
		}

		@Override
		public boolean canUse(ItemStack stack, Entity e) {
			return false;
		}

		@Override
		public String getItemName(ItemStack stack) {
			return null;
		}

		@Override
		public int getMaxStack(ItemStack stack) {
			return 10;
		}

		@Override
		public int getWeight(ItemStack stack) {
			return 10;
		}

		@Override
		public boolean onUsed(ItemStack stack, Entity e) {
			return false;
		}

		@Override
		public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
			return false;
		}
	}
}
