package com.bendude56.dungeonman.item;

import java.awt.Image;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.gfx.ImageUtil;

/**
 * @author Benjamin C. Thomas
 */

public class ItemGoldCoin extends Item {
    private static final Image fewCoinSprite = ImageUtil.loadImage("/entity/item/coin1.png");
    private static final Image manyCoinSprite = ImageUtil.loadImage("/entity/item/coin2.png");
    
    public ItemGoldCoin(int id) {
        super(id, 500, 500);
    }
    
    @Override
    public Image getDrawImage(ItemStack stack) {
        return (stack.getAmount() > 100) ? manyCoinSprite : fewCoinSprite;
    }
    
    @Override
    public boolean canUse(ItemStack stack, Entity e) {
        return false;
    }
    
    @Override
    public String getItemName(ItemStack stack) {
        return "Gold Coin";
    }
    
    @Override
    public int getMaxStack(ItemStack stack) {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public int getWeight(ItemStack stack) {
        return 1;
    }
    
    @Override
    public boolean onUsed(ItemStack stack, Entity e) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean onPickedUp(ItemStack stack, EntityPlayer p) {
        return true;
    }
    
}
