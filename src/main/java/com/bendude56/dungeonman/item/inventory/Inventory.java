package com.bendude56.dungeonman.item.inventory;

import java.util.ArrayList;
import java.util.List;

import com.bendude56.dungeonman.item.ItemStack;

public class Inventory {
	private int maxWeight;
	private ArrayList<ItemStack> items;
	
	public Inventory(int maxWeight) {
		this.maxWeight = maxWeight;
		this.items = new ArrayList<ItemStack>();
	}
	
	public int getMaxWeight() {
		return maxWeight;
	}
	
	public int getCurrentWeight() {
		int weight = 0;
		
		for (ItemStack i : items) {
			weight += i.getAmount() * i.getItem().getWeight(i);
		}
		
		return weight;
	}
	
	public void addItem(ItemStack stack) {
		for (ItemStack i : items) {
			if (i.getItem().getItemId() == stack.getItem().getItemId() && i.getMetadata().equals(stack.getMetadata())) {
				int a = Math.min(stack.getAmount(), i.getItem().getMaxStack(i) - i.getAmount());
				
				stack.setAmount(stack.getAmount() - a);
				i.setAmount(i.getAmount() + a);
			}
		}
		
		if (stack.getAmount() > 0) {
			items.add(stack);
		}
	}
	
	public void removeItem(ItemStack stack) {
		items.remove(stack);
	}
	
	public List<ItemStack> getItems() {
		return items;
	}
}
