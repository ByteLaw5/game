package com.game.level.gen.layer;

import com.game.level.gen.GeneratorType;

public enum GenLayerAddMountains implements GenLayer
{
    INSTANCE;

    @Override
    public GeneratorType sample(GenLayerSampler prev, int sectionX, double noise)
    {
        if (noise > 0.3D)
            return GeneratorType.MOUNTAINS;
        else
            return prev.sample(sectionX);
    }
}
