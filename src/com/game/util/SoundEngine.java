package com.game.util;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

public class SoundEngine {
    public static Map<String, Sound> sounds = new HashMap<>();
    public static Map<String, Music> music = new HashMap<>();

    public void load() {
        try {
            sounds.put("zombieambient1", new Sound("res/sounds/sndZombie1.ogg"));
            sounds.put("zombieambient2", new Sound("res/sounds/sndZombie2.ogg"));
            sounds.put("zombieambient3", new Sound("res/sounds/sndZombie3.ogg"));
            sounds.put("zombieambient4", new Sound("res/sounds/sndZombie5.ogg"));
            sounds.put("zombieambient5", new Sound("res/sounds/sndZombie8.ogg"));
            sounds.put("zombiehit1", new Sound("res/sounds/sndZombie4.ogg"));
            sounds.put("zombiehit2", new Sound("res/sounds/sndZombie6.ogg"));
            sounds.put("zombiehit3", new Sound("res/sounds/sndZombie7.ogg"));
            sounds.put("zombiedeath1", new Sound("res/sounds/sndZombie9.ogg"));
            sounds.put("zombiedeath2", new Sound("res/sounds/sndZombie10.ogg"));
            sounds.put("zombiedeath3", new Sound("res/sounds/sndZombie11.ogg"));
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
