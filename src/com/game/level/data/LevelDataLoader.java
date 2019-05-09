package com.game.level.data;

import com.game.block.BlockType;
import com.game.level.Level;
import com.game.level.LevelSection;
import com.game.registry.Registry;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class LevelDataLoader
{
    private static final int META_END = -1;

    public static Level loadSection(Level level, File file, int sectionX)
    {
        Path loc = getSection(file, sectionX);
        File fileloc = loc.toFile();
        LevelSection section = level.getSection(sectionX);
        try {
            if (fileloc.createNewFile())
                level.generate(sectionX);
            else
            {
                DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileloc), 1024));

                    for (int x = 0; x < 16; ++x)
                        for (int y = section.getHeight() - 1; y >= 0; --y)
                        {
                            int i = in.readInt();
                            BlockType b = Registry.BLOCK.get(i);
                            section.setBlock(x, y, b);
                        }

                 in.close();
            }
        } catch (EOFException e) {
            System.err.println("Error loading section " + String.valueOf(sectionX) + ": Reached end of file earlier than expected!");
            e.printStackTrace();
            throw new SectionLoadError("Error loading section " + String.valueOf(sectionX) + ": Reached end of file earlier than expected!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SectionLoadError("Error loading section " + String.valueOf(sectionX) + ": Unhandled IOException!");
        }

        return level;
    }

    public static Level loadLevel(File file)
    {
        if (file.mkdirs())
        {
            System.out.println("Made world directory: " + file.toString());
        }

        Random random = new Random();
        Level level;

        Path loc = Paths.get(file.getPath() + "/meta.dat");
        File fileloc = loc.toFile();

        try {
            if (fileloc.createNewFile())
            {
                System.out.println("Creating initial level data.");

                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileloc), 1024));

                long seed = random.nextLong();
                level = new Level(seed);

                out.writeLong(seed);
                out.flush();
                out.close();
            }
            else
            {
                DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileloc), 1024));

                long seed = in.readLong();

                level = new Level(seed);

                in.close();
            }
        } catch (EOFException e) {
            System.err.println("Error loading level " + loc.toString() + ": Reached end of file earlier than expected!");
            e.printStackTrace();
            throw new LevelLoadError("Error loading level " + loc.toString() + ": Reached end of file earlier than expected!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new LevelLoadError("Error loading level " + loc.toString() + ": Unhandled IOException!");
        }

        return level;
    }

    public static void writeSection(Level level, File file, int sectionX)
    {
        Path loc = getSection(file, sectionX);
        File fileloc = loc.toFile();
        LevelSection section = level.getSection(sectionX);

        if (section.getBlock(0, 0) != null)
        {
            try
            {
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileloc), 1024));

                for (int x = 0; x < 16; ++x)
                {
                    for (int y = section.getHeight() - 1; y >= 0; --y)
                    {
                        int i = Registry.BLOCK.getId(section.getBlock(x, y));
                        System.out.println(i);
                        out.writeInt(i);
                    }
                }

                out.writeInt(META_END);

                out.flush();
                out.close();
            } catch (EOFException e)
            {
                e.printStackTrace();
                System.err.println("Error loading section " + String.valueOf(sectionX) + ": Reached end of file earlier than expected!");
                throw new SectionLoadError("Error loading section " + String.valueOf(sectionX) + ": Reached end of file earlier than expected!");
            } catch (IOException e)
            {
                e.printStackTrace();
                throw new SectionLoadError("Error loading section " + String.valueOf(sectionX) + ": Unhandled IOException!");
            }
        }
    }

    private static Path getSection(File parent, int sectionX)
    {
        String newPath = parent.getPath() + "/sect_" + String.valueOf(sectionX) + ".dat";
        return Paths.get(newPath);
    }

    public static class SectionLoadError extends LevelLoadError
    {
        public SectionLoadError(String message)
        {
            super(message);
        }
    }

    public static class LevelLoadError extends RuntimeException
    {
        public LevelLoadError(String message)
        {
            super(message);
        }
    }
}
