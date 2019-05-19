package com.game.item;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class Item {
    protected static final Map<String, Item> ITEMS = new HashMap<>();

    private String regName;
    protected Item(String registryName) {
        regName = registryName;
        ITEMS.put(regName, this);
    }
    public String getRegistryName() {
        return regName;
    }
    public abstract BufferedImage getTexture();
    public int getMaxStack() {
        return 256;
    }
}