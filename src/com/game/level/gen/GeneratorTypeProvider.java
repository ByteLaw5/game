package com.game.level.gen;

import com.game.level.gen.layer.GenLayer;
import com.game.level.gen.layer.GenLayerAddMountains;
import com.game.level.gen.layer.GenLayerInit;
import com.game.level.gen.layer.GenLayerSampler;
import com.game.util.noise.NoiseGenerator;
import com.game.util.noise.OctaveNoiseGenerator;

public class GeneratorTypeProvider
{
    private final NoiseGenerator biomeGenerator, mountainGenerator;

    public GeneratorTypeProvider(long seed)
    {
        biomeGenerator = new OctaveNoiseGenerator(seed + 128L, 4).apply(12D);
        mountainGenerator = new OctaveNoiseGenerator(seed - 128L, 2).apply(10D);
    }
    /**
     * @param sectionX
     * @return GeneratorType for the section. Technically this is the generator type from the leftmost block of the section.
     */
    public GeneratorType getGeneratorAtSection(int sectionX)
    {
        double mountainVal = mountainGenerator.eval(sectionX, 0);
        double biomeVal1 = biomeGenerator.eval(sectionX, 0);

        GeneratorType type;

        type = GenLayerInit.INSTANCE.sample(null, sectionX, biomeVal1);
        type = GenLayerAddMountains.INSTANCE.sample(new GenLayerSampler(GenLayerInit.INSTANCE, null, biomeGenerator, 0), sectionX, mountainVal);

        return type;
    }
}
