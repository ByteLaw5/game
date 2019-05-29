package com.game.item;

import com.game.util.Assets;

import java.awt.image.BufferedImage;

public class EmptyItem extends Item {
    public EmptyItem() {
        super("empty");
    }
    @Override
    public BufferedImage getTexture() {
        return Assets.empty;
    }

    @Override
    public int getMaxStack() {
        return 0;
    }
}
