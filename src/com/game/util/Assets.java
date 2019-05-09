package com.game.util;

import java.awt.image.BufferedImage;

public class Assets {
    private static BufferedImageLoader loader = new BufferedImageLoader();

    public static final BufferedImage[] player = {
            loader.loadImage("/res/textures/mobs/player/walk0.png"),
            loader.loadImage("/res/textures/mobs/player/walk1.png"),
            loader.loadImage("/res/textures/mobs/player/walk2.png"),
            loader.loadImage("/res/textures/mobs/player/idle.png")
    };
    public static final BufferedImage zombie = loader.loadImage("/res/textures/mobs/zombie.png");
    public static final BufferedImage block = loader.loadImage("/res/textures/blocks/dirt.png");
    public static final BufferedImage block_notcollide = loader.loadImage("/res/textures/blocks/dirt0.png");
    public static final BufferedImage grass = loader.loadImage("/res/textures/blocks/grass.png");
    public static final BufferedImage grass_notcollide = loader.loadImage("/res/textures/blocks/grass0.png");
    public static final BufferedImage stone = loader.loadImage("/res/textures/blocks/stone.png");
    public static final BufferedImage stone_notcollide = loader.loadImage("/res/textures/blocks/stone0.png");

    public static final BufferedImage stick = loader.loadImage("/res/textures/items/stick.png");
}
