package com.game.level.gen.layer;

import com.game.level.gen.GeneratorType;

public interface GenLayer
{
    public GeneratorType sample(GenLayerSampler previous, int sectionX, double noise);
}
