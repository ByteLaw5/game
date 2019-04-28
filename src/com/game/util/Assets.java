package com.game.util;

import java.awt.image.BufferedImage;

public class Assets {
    private static BufferedImageLoader loader = new BufferedImageLoader();
    private static BufferedImage sprite_sheet = loader.loadImage("/textures/spritesheet.png");

    public static final BufferedImage player = loader.crop(sprite_sheet, 2, 1, 32, 64);
    public static final BufferedImage block = loader.crop(sprite_sheet, 1, 1, 32, 32);
    public static final BufferedImage block_notcollide = loader.crop(sprite_sheet, 3, 1, 32, 32);
}
