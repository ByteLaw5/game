package com.game.level;

import com.game.block.BlockType;
import com.game.objects.Block;

import java.util.Arrays;

public class LevelSection
{
    private static final int HEIGHT = 128;

    private BlockType[][] blocks = new BlockType[16][HEIGHT];

    public LevelSection()
    {
        for (int i = 0; i < 16; ++i)
            for (int j = 0; j < HEIGHT; ++j)
                blocks[i][j] = BlockType.AIR;
    }

    public BlockType getBlock(int x, int y)
    {
        return blocks[x][y];
    }

    public void setBlock(int x, int y, BlockType block)
    {
        blocks[x][y] = block;
    }

    public int getHeight()
    {
        return HEIGHT;
    }
}
