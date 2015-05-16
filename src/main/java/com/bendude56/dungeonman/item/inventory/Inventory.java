package com.bendude56.dungeonman.item.inventory;

import java.util.ArrayList;
import java.util.List;

import com.bendude56.dungeonman.item.ItemMetadataKey;
import com.bendude56.dungeonman.item.ItemStack;

/**
 * @author Benjamin C. Thomas
 */

public class Inventory {
    private ArrayList<ItemStack> items;
    
    public Inventory() {
        this.items = new ArrayList<ItemStack>();
    }
    
    /**
     * Calculates the combined weight of all items held by the player.
     */
    public int getCurrentWeight() {
        int weight = 0;
        
        for (ItemStack i : items) {
            weight += i.getAmount() * i.getItem().getWeight(i);
        }
        
        return weight;
    }
    
    /**
     * Adds a stack of items into the player's inventory. If possible, this
     * stack will be combined with similar stacks within the inventory.
     * 
     * @param stack The stack to be added to this inventory.
     */
    public void addItem(ItemStack stack) {
        for (ItemStack i : items) {
            if (i.getItem().getItemId() == stack.getItem().getItemId() && i.getMetadata().equals(stack.getMetadata())) {
                i.setAmount(i.getAmount() + stack.getAmount());
                return;
            }
        }
        
        if (stack.getAmount() > 0) {
            items.add(stack);
        }
    }
    
    /**
     * Removes the specified item stack from this inventory.
     * 
     * @param stack The stack which should be removed.
     */
    public void removeItem(ItemStack stack) {
        items.remove(stack);
    }
    
    /**
     * Gets a list of all items present in this inventory.
     */
    public List<ItemStack> getItems() {
        return items;
    }
    
    /**
     * Attempts to find a key in this inventory with the specified keyId.
     * 
     * @param keyId The unique identifier of the key which should be found.
     * 
     * @return If the key is present, the {@link ItemStack} of the key.
     *         Otherwise, returns null.
     */
    public ItemStack getKey(int keyId) {
        for (ItemStack i : items) {
            if (i.getMetadata() instanceof ItemMetadataKey && ((ItemMetadataKey) i.getMetadata()).getKeyId() == keyId) {
                return i;
            }
        }
        
        return null;
    }
}
