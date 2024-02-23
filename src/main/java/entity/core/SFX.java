package entity.core;

import entity.core.constants.Sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

import static entity.core.constants.Sounds.*;

public class SFX {
    Clip clip;

    public SFX() {
        KARMA_CAT_INTRO.soundURL = getClass().getClassLoader().getResource("sounds/karma_cat_intro.wav");
        LEVEL_UP.soundURL = getClass().getClassLoader().getResource("sounds/level_up.wav");
        POWER_UP.soundURL= getClass().getClassLoader().getResource("sounds/power_up.wav");
        MARIO_COIN.soundURL = getClass().getClassLoader().getResource("sounds/mario_coin.wav");
    }
    public void setFile(Sounds sounds) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(sounds.soundURL);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("" + sounds,e);
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
