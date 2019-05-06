package com.game.level;

import com.game.block.BlockType;
import com.game.level.gen.GeneratorType;
import com.game.level.gen.GeneratorTypeProvider;
import com.game.util.noise.NoiseGenerator;
import com.game.util.noise.OctaveNoiseGenerator;

import java.util.HashMap;
import java.util.Map;

public class Level
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

    public long getSeed()
    {
        return seed;
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

            for (int y = section.getHeight() - 1; y >= 0)
            {
                if (y <= height)
                    section.setBlock(x, y, BlockType.EARTH);
            }
        }
    }
}
