package com.game.level;

import com.game.block.BlockType;

public interface IBlockWriter
{
    public void setBlock(int x, int y, BlockType block);
}
