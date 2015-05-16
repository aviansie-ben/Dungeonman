package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.bendude56.dungeonman.gfx.ImageUtil;

/**
 * @author Benjamin C. Thomas
 */

public class TileGravestone extends TileFloor {
    public static Image simpleSprite = ImageUtil.loadImage("/tile/gravestone-simple.png");
    
    public TileGravestone() {
        super(5);
    }
    
    @Override
    public void render(Graphics g, int x, int y, TileState state) {
        super.render(g, x, y, state);
        
        g.drawImage(simpleSprite, x, y, null);
    }
    
    @Override
    public Color getColor(TileState state) {
        return Color.lightGray;
    }
    
}
