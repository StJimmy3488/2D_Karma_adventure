package entity.core;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SFX {
    Clip clip;
    URL[] soundURL = new URL[30];

    public SFX() {
        this.soundURL[0] = getClass().getClassLoader().getResource("sounds/karma-cat-intro.wav");
        this.soundURL[1] = getClass().getClassLoader().getResource("sounds/level-up.wav");
        this.soundURL[2] = getClass().getClassLoader().getResource("sounds/power-up.wav");
        this.soundURL[3] = getClass().getClassLoader().getResource("sounds/mario-coin.wav");
        this.soundURL[4] = getClass().getClassLoader().getResource("sounds/door-closed.wav");
    }
    public void setFile(int index) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
