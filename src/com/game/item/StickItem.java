package com.game.item;

import com.game.util.Assets;

import java.awt.image.BufferedImage;

public class StickItem extends Item {
    public StickItem() {
        super("stick");
    }
    @Override
    public BufferedImage getTexture() {
        return Assets.stick;
    }
}
