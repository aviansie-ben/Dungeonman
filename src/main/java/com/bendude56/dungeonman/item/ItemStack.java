package com.bendude56.dungeonman.item;

/**
 * @author Benjamin C. Thomas
 */

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

	/**
	 * Gets the metadata associated with this stack of items. All of the items
	 * within one stack will have the same metadata.
	 */
	public ItemMetadata getMetadata() {
		return metadata;
	}

	/**
	 * Sets the metadata of this stack of items. This will affect all items in
	 * the stack.
	 */
	public void setMetadata(ItemMetadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * Gets the amount of the specified item that are present in this stack.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the specified item represented by this stack of
	 * items.
	 */
	public void setAmount(int amount) {
		if (amount > item.getMaxStack(this))
			throw new IllegalArgumentException("Amount is greater than max stack!");
		
		this.amount = amount;
	}

	/**
	 * Gets the item type represented by this stack of items.
	 */
	public Item getItem() {
		return item;
	}
}
