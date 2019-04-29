package com.game.util;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundEngine {
    public static Map<String, Sound> sounds = new HashMap<>();
    public static Map<String, Music> music = new HashMap<>();

    public void load() {
        try {
            sounds.put("test", new Sound("res/sounds/test.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Sound getSound(String key) {
        return sounds.get(key);
    }

    public static Music getMusic(String key) {
        return music.get(key);
    }
}
