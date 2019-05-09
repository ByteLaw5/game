package com.game.level;

import com.game.block.BlockType;

public interface IBlockReadable
{
    public BlockType getBlock(int x, int y);
}
