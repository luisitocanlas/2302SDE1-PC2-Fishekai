package com.fishekai.engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {
    Clip clip;
    float previousVolume = 0;
    float currentVolume = 0;
    FloatControl fc;
    boolean mute = false;

    // load clips in an array or list
    InputStream[] soundClips = {
            openAudioResource("/sounds/intro.wav"),
            openAudioResource("/sounds/look.wav"),
            openAudioResource("/sounds/drink.wav"),
            openAudioResource("/sounds/getItem.wav"),
            openAudioResource("/sounds/goodbye-friendly.wav"),
            openAudioResource("/sounds/unfold-a-map.wav"),
            openAudioResource("/sounds/mainMusicLoop.wav"),
            openAudioResource("/sounds/help.wav"),
            openAudioResource("/sounds/jump.wav"),
            openAudioResource("/sounds/talk_1.wav"),
            openAudioResource("/sounds/catch.wav"),
            openAudioResource("/sounds/drop.wav"),
            openAudioResource("/sounds/go.wav"),
            openAudioResource("/sounds/beforeEat.wav"),
            openAudioResource("/sounds/eat.wav"),
            openAudioResource("/sounds/build.wav"),
            openAudioResource("/sounds/fish_gets_away.wav"),
            openAudioResource("/sounds/pull.wav")
    };

    public void setFile(int i) {

        try (AudioInputStream ais = AudioSystem.getAudioInputStream(soundClips[i])) {
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (Exception e) {
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

    public void stopClip(int i) {
        if (clip != null && clip.isOpen()) {
            if (clip.getFramePosition() > 0 && clip.getFramePosition() < clip.getFrameLength()) {
                clip.stop();
                clip.setFramePosition(0);
            }
        }
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
        if (!mute) {
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
            for (int i = 0; i < soundClips.length; i++) {
                stopClip(i);
            }
        } else {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }

    private InputStream openAudioResource(String resourcePath) {

        InputStream input = getClass().getResourceAsStream(resourcePath);

        return new BufferedInputStream(input);
    }

}
