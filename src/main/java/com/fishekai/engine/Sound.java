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
        soundURL[0] = getClass().getResource("/sounds/waves-of-hawaii.wav");
        soundURL[1] = getClass().getResource("/sounds/jungle_growl.wav");
        soundURL[2] = getClass().getResource("/sounds/mountain-river.wav");
        soundURL[3] = getClass().getResource("/sounds/getItem.wav");
        soundURL[4] = getClass().getResource("/sounds/goodbye-friendly.wav");
        soundURL[5] = getClass().getResource("/sounds/unfold-a-map.wav");
        soundURL[6] = getClass().getResource("/sounds/mainMusicLoop.wav");
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
        System.out.println("current Volume:" + currentVolume);
        if (currentVolume > 6.0f) {
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeDown() {
        currentVolume -= 1.0f;
        System.out.println("current Volume:" + currentVolume);
        if (currentVolume < -80.f) {
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeMute() {
        if(mute == false) {
            previousVolume = currentVolume;
            System.out.println("current Volume:" + currentVolume);
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        }
        else if (mute == true) {
            currentVolume = previousVolume;
            System.out.println("current Volume:" + currentVolume);
            fc.setValue(currentVolume);
            mute = false;
        }
    }
}
