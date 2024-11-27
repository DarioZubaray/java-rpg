package io.github.dariozubaray.sound;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource(SoundLabel.COIN.getPath());
        soundURL[1] = getClass().getResource(SoundLabel.POWER_UP.getPath());
        soundURL[2] = getClass().getResource(SoundLabel.UNLOCK.getPath());
        soundURL[3] = getClass().getResource(SoundLabel.FANFARE.getPath());

        soundURL[4] = getClass().getResource(SoundLabel.HIT_MONSTER.getPath());
        soundURL[5] = getClass().getResource(SoundLabel.RECEIVE_DAMAGE.getPath());
        soundURL[6] = getClass().getResource(SoundLabel.SWING_WEAPON.getPath());

        soundURL[7] = getClass().getResource(SoundLabel.LEVEL_UP.getPath());
        soundURL[8] = getClass().getResource(SoundLabel.CURSOR.getPath());
        soundURL[9] = getClass().getResource(SoundLabel.BURNING.getPath());
        soundURL[10] = getClass().getResource(SoundLabel.CUT_TREE.getPath());
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {
            e.printStackTrace();
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
