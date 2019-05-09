package com.game.level;

public interface IWorld extends IBlockReadable, IBlockWriter
{
    public long getSeed();
    public int getHeightAtPos(int x);
}
