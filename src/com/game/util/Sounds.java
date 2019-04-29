package com.game.util;

import org.newdawn.slick.Sound;

import java.io.File;

public class Sounds {
    public static final File test_wav = new File("src/res/sounds/test.wav");

    public static final Sound test_ogg = SoundEngine.getSound("test");
}
