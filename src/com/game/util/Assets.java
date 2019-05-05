package com.game.util;

import java.awt.image.BufferedImage;

public class Assets {
    private static BufferedImageLoader loader = new BufferedImageLoader();

    public static final BufferedImage player = loader.loadImage("/res/textures/mobs/player.png");
    public static final BufferedImage zombie = loader.loadImage("/res/textures/mobs/zombie.png");
    public static final BufferedImage block = loader.loadImage("/res/textures/blocks/dirt.png");
    public static final BufferedImage block_notcollide = loader.loadImage("/res/textures/blocks/dirt0.png");
}
