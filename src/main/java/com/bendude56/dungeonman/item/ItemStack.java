package com.bendude56.dungeonman.item;

public class ItemStack {
	private final Item item;
	private ItemMetadata metadata;
	private int amount;
	
	public ItemStack(Item item, ItemMetadata metadata, int amount) {
		this.item = item;
		this.metadata = metadata;
		
		if (amount > item.getMaxStack(this))
			throw new IllegalArgumentException("Amount is greater than max stack!");
		
		this.amount = amount;
	}

	public ItemMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(ItemMetadata metadata) {
		this.metadata = metadata;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		if (amount > item.getMaxStack(this))
			throw new IllegalArgumentException("Amount is greater than max stack!");
		
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}
}