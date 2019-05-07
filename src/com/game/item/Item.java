package com.game.item;

import java.awt.image.BufferedImage;

public abstract class Item {
    private String regName;
    protected Item(String registryName) {
        regName = registryName;
    }
    public String getRegistryName() {
        return regName;
    }
    public abstract BufferedImage getTexture();
    public int getMaxStack() {
        return 256;
    }
}