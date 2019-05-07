package com.game.block;

import com.game.objects.Block;
import com.game.registry.Registry;

import java.awt.image.BufferedImage;

public class BlockType
{
    public static final BlockType AIR = Registry.BLOCK.register(0, new AirBlockType());
    public static final BlockType EARTH = Registry.BLOCK.register(1, new EarthBlockType());
    public static final BlockType EARTH_BACKGROUND = Registry.BLOCK.register(2, new BackgroundEarthBlockType());
    public static final BlockType GRASS = Registry.BLOCK.register(3, new GrassBlockType());
    public static final BlockType GRASS_BACKGROUND = Registry.BLOCK.register(4, new BackgroundGrassBlockType());
    public static final BlockType STONE = Registry.BLOCK.register(5, new StoneBlockType());
    public static final BlockType STONE_BACKGROUND = Registry.BLOCK.register(6, new BackgroundStoneBlockType());

    private final boolean solid;
    private final BufferedImage texture;
    private boolean visible = true;

    protected BlockType(BufferedImage texture, boolean collidable)
    {
        this.texture = texture;
        this.solid = collidable;
    }

    protected BlockType setVisible(boolean visible)
    {
        this.visible = visible;
        return this;
    }

    public boolean isSolid()
    {
        return solid;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public BufferedImage getTexture()
    {
        return texture;
    }
}
