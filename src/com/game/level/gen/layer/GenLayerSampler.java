package com.game.level.gen.layer;

import com.game.level.gen.GeneratorType;
import com.game.util.noise.NoiseGenerator;

public class GenLayerSampler
{
    private final NoiseGenerator noiseGenerator;
    private final GenLayer layer;
    private final GenLayerSampler prev;
    private final int y;

    public GenLayerSampler(GenLayer layer, GenLayerSampler prev, NoiseGenerator noise, int y)
    {
        this.layer = layer;
        this.noiseGenerator = noise;
        this.prev = prev;
        this.y = y;
    }

    public GeneratorType sample(int sectionX)
    {
        return layer.sample(prev, sectionX, noiseGenerator.eval(sectionX, y));
    }
}
