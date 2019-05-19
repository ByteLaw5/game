package com.game.level.gen;

import com.game.registry.Registry;

public abstract class GeneratorType
{
    public static final GeneratorType LOWLANDS = Registry.GENERATOR.register(0, new LowlandsGeneratorType("lowlands"));
    public static final GeneratorType MOUNTAINS = Registry.GENERATOR.register(1, new MountainsGeneratorType("mountains"));

    private final String name;
    private final int height;

    protected GeneratorType(int height, String name)
    {
        this.name = name.toLowerCase();
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "generator." + name;
    }

    public String getName()
    {
        return name;
    }

    public int getBaseHeight()
    {
        return height;
    }

}
