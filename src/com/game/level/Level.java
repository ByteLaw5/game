package com.game.level;

import com.game.GameBase;
import com.game.block.BlockType;
import com.game.level.data.LevelDataLoader;
import com.game.level.gen.GeneratorType;
import com.game.level.gen.GeneratorTypeProvider;
import com.game.util.math.MathUtils;
import com.game.util.noise.NoiseGenerator;
import com.game.util.noise.OctaveNoiseGenerator;

import java.util.HashMap;
import java.util.Map;

public class Level implements IWorld
{
    private Map<Integer, LevelSection> world = new HashMap<>();
    private final long seed;
    private final GeneratorTypeProvider generatorTypeProvider;
    private final NoiseGenerator height_generator;

    public Level(long seed)
    {
        this.seed = seed;
        this.generatorTypeProvider = new GeneratorTypeProvider(seed);

        height_generator = new OctaveNoiseGenerator(seed, 3).apply(31D);
    }

    @Override
    public int getHeightAtPos(int x)
    {
        int sectionX = MathUtils.floor(x / 16D);
        int h;
        LevelSection section;

        if (world.containsKey(sectionX))
        {
            section = getSection(sectionX);
            h = getSectionHeightAtX(section, x & 15);
        }
        else
        {
            LevelDataLoader.loadSection(this, GameBase.getStaticLoadedFile(), sectionX);
            section = this.getSection(sectionX);
            h = getSectionHeightAtX(section, x & 15);
            this.unloadSection(sectionX);
        }

        return h;
    }

    private int getSectionHeightAtX(LevelSection section, int x)
    {
        for (int y = section.getHeight() - 1; y >= 0; --y)
        {
            BlockType b = section.getBlock(x, y);

            if (b.isSolid())
                return y;
        }

        return 0;
    }

    @Override
    public long getSeed()
    {
        return seed;
    }

    @Override
    public void setBlock(int x, int y, BlockType block)
    {
        int sectionX = MathUtils.floor(x / 16D);

        if (world.containsKey(sectionX))
        {
            getSection(sectionX).setBlock(x & 15, y, block);
        }
        else
        {
            LevelDataLoader.loadSection(this, GameBase.getStaticLoadedFile(), sectionX);
            this.getSection(sectionX).setBlock(x & 15, y, block);
            LevelDataLoader.writeSection(this, GameBase.getStaticLoadedFile(), sectionX);
            this.unloadSection(sectionX);
        }
    }

    @Override
    public BlockType getBlock(int x, int y)
    {
        int sectionX = MathUtils.floor(x / 16D);

        if (world.containsKey(sectionX))
        {
            return getSection(sectionX).getBlock(x & 15, y);
        }
        else
        {
            LevelDataLoader.loadSection(this, GameBase.getStaticLoadedFile(), sectionX);
            BlockType blockType = this.getSection(sectionX).getBlock(x & 15, y);
            this.unloadSection(sectionX);
            return blockType;
        }
    }

    public boolean isSectionLoaded(int x)
    {
        return world.containsKey(x);
    }

    public void unloadSection(int x)
    {
        world.remove(x);
    }

    public LevelSection getSection(int x)
    {
        if (world.containsKey(x))
            return world.get(x);
        else
        {
            LevelSection section = new LevelSection();
            world.put(x, section);

            return section;
        }
    }

    public void generate(int sectionX)
    {
        LevelSection section = getSection(sectionX);

        GeneratorType next = generatorTypeProvider.getGeneratorAtSection(sectionX + 1);
        GeneratorType current = generatorTypeProvider.getGeneratorAtSection(sectionX);
        for (int x = 0; x < 16; ++x)
        {
            double noise = height_generator.eval(sectionX * 16 + x, 0);
            int baseHeight = (int) (((15 - x) * current.getBaseHeight() + x * next.getBaseHeight()) / 15D);

            int height = (int) (baseHeight + (noise < 0 ? 3 : 15) * noise);

            for (int y = section.getHeight() - 1; y >= 0; --y)
            {
                if (y <= height)
                    section.setBlock(x, y, BlockType.EARTH);
                else
                    section.setBlock(x, y, BlockType.AIR);
            }

            current.generate(this, section, sectionX * 16 + x);
        }
    }
}
