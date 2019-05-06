package com.game.registry;

import com.game.block.BlockType;
import com.game.level.gen.GeneratorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry<T>
{
    private static final List<String> registry_names = new ArrayList<>();

    public static final Registry<BlockType> BLOCK = new Registry<BlockType>("block");
    public static final Registry<GeneratorType> GENERATOR = new Registry<GeneratorType>("generator");

    private Map<Integer, T> reg_map = new HashMap<>();
    private Map<T, Integer> reverse_map = new HashMap<>();
    private final String name;

    protected Registry(String name)
    {
        if (registry_names.contains(name.toLowerCase()))
            throw new IllegalArgumentException("Registry with name " + name.toLowerCase() + " already exists!");

        this.name = name.toLowerCase();
        registry_names.add(name.toLowerCase());
    }

    @Override
    public String toString()
    {
        return "registry." + name;
    }

    public String getName()
    {
        return name;
    }

    public T register(int id, T item)
    {
        reg_map.put(id, item);
        reverse_map.put(item, id);

        return item;
    }

    public int getId(T item)
    {
        if (reverse_map.containsKey(item))
            return reverse_map.get(item);
        else
            throw new IndexOutOfBoundsException("Item " + item.toString() + " has not been registered!");
    }

    public T get(int id)
    {
        if (reg_map.containsKey(id))
            return reg_map.get(id);
        else
            throw new IndexOutOfBoundsException("Item " + String.valueOf(id) + " is not a registered item in this registry!");
    }
}
