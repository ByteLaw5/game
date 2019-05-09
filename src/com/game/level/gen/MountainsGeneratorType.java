package com.game.level.gen;

import com.game.block.BlockType;
import com.game.level.IWorld;
import com.game.level.LevelSection;

public class MountainsGeneratorType extends GeneratorType
{
    MountainsGeneratorType(String name)
    {
        super (90, name);
    }

    @Override
    public void generate(IWorld world, LevelSection section, int x)
    {
        int x1 = x & 15;

        for (int y = section.getHeight() - 1; y >= 0; --y)
        {
            BlockType blockType = section.getBlock(x1, y);
            BlockType blockType1 = blockType.isSolid() ? BlockType.EARTH : BlockType.AIR;

            //TODO Code to mess with blockType1 here

            section.setBlock(x1, y, blockType1);
        }
    }
}
