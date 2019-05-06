package com.game.level.gen.layer;

import com.game.level.gen.GeneratorType;

public enum GenLayerInit implements GenLayer
{
    INSTANCE;

    @Override
    public GeneratorType sample(GenLayerSampler nullGenLayer, int sectionX, double noise)
    {
        return GeneratorType.LOWLANDS;
    }
}
