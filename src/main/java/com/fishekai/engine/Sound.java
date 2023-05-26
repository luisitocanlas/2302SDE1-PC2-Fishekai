package com.fishekai.engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    float previousVolume = 0;
    float currentVolume = 0;
    FloatControl fc;
    boolean mute = false;


    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/intro.wav");
        soundURL[1] = getClass().getResource("/sounds/look.wav");
        soundURL[2] = getClass().getResource("/sounds/drink.wav");
        soundURL[3] = getClass().getResource("/sounds/getItem.wav");
        soundURL[4] = getClass().getResource("/sounds/goodbye-friendly.wav");
        soundURL[5] = getClass().getResource("/sounds/unfold-a-map.wav");
        soundURL[6] = getClass().getResource("/sounds/mainMusicLoop.wav");
        soundURL[7] = getClass().getResource("/sounds/help.wav");
        soundURL[8] = getClass().getResource("/sounds/jump.wav");
        soundURL[9] = getClass().getResource("/sounds/talk_1.wav");
        soundURL[10] = getClass().getResource("/sounds/catch.wav");
        soundURL[11] = getClass().getResource("/sounds/drop.wav");
        soundURL[12] = getClass().getResource("/sounds/go.wav");
        soundURL[13] = getClass().getResource("/sounds/beforeEat.wav");
        soundURL[14] = getClass().getResource("/sounds/eat.wav");
        soundURL[15] = getClass().getResource("/sounds/build.wav");
        soundURL[16] = getClass().getResource("/sounds/fish_gets_away.wav");
        soundURL[17] = getClass().getResource("/sounds/pull.wav");

    }

    public void setFile(int i) {

        try (AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i])) {
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
    public void volumeUp() {
        currentVolume += 1.0f;
        if (currentVolume > 6.0f) {
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeDown() {
        currentVolume -= 1.0f;
        if (currentVolume < -80.f) {
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeMute() {
        if(!mute) {
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
            for (int i = 0; i < soundURL.length; i++) {
                stop();
            }
        } else {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
            }
        }
    }
