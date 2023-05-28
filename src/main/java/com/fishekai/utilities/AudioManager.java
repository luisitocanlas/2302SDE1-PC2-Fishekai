package com.fishekai.utilities;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    public static final float MIN_VOLUME = 0.0f;
    public static final float MAX_VOLUME = 1.0f;

    private Clip musicClip;
    private Map<String, Clip> soundEffects;
    private float musicVolume;
    private float soundEffectsVolume;
    private boolean soundEffectsEnabled;

    public AudioManager() {
        soundEffects = new HashMap<>();
        musicVolume = 1.0f; // Default volume is maximum (1.0)
        soundEffectsVolume = 1.0f; // Default volume is maximum (1.0)
        soundEffectsEnabled = true; // Enable sound effects by default
    }

    private InputStream openAudioResource(String resourcePath) {
        InputStream input = getClass().getResourceAsStream(resourcePath);
        return new BufferedInputStream(input);
    }

    public void loadSoundEffect(String soundEffectName, String filePath) {
        try (InputStream input = openAudioResource(filePath)) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(input);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            soundEffects.put(soundEffectName, clip);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void loadMusic(String filePath) {
        try (InputStream input = openAudioResource(filePath)) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(input);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(boolean loop) {
        if (musicClip != null) {
            musicClip.setFramePosition(0);
            musicClip.loop(loop ? Clip.LOOP_CONTINUOUSLY : 0);
            musicClip.start();
        }
    }

    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.setFramePosition(0);
        }
    }

    public boolean isMusicPlaying() {
        return musicClip != null && musicClip.isRunning();
    }

    public void setSoundEffectsEnabled(boolean enabled) {
        soundEffectsEnabled = enabled;
    }

    public boolean areSoundEffectsEnabled() {
        return soundEffectsEnabled;
    }

    public void playSoundEffect(String soundEffectName) {
        Clip clip = soundEffects.get(soundEffectName);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void setMusicVolume(float volume) {
        if (volume >= MIN_VOLUME && volume <= MAX_VOLUME && musicClip != null) {
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            musicVolume = volume;
            System.out.println("Music volume set to: " + musicVolume);
        }
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void volumeUp() {
        if (musicVolume == MAX_VOLUME) {
            System.out.println("Already at maximum volume.");
        } else {
            float newVolume = musicVolume + 0.1f;
            if (newVolume > MAX_VOLUME) {
                newVolume = MAX_VOLUME;
            }
            setMusicVolume(newVolume);
        }
    }

    public void volumeDown() {
        if (musicVolume == MIN_VOLUME) {
            System.out.println("Already at minimum volume.");
        } else {
            float newVolume = musicVolume - 0.1f;
            if (newVolume < MIN_VOLUME) {
                newVolume = MIN_VOLUME;
            }
            setMusicVolume(newVolume);
        }
    }

    public void setSoundEffectsVolume(float volume) {
        if (volume >= 0.0f && volume <= 1.0f) {
            for (Clip clip : soundEffects.values()) {
                if (clip != null) {
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                    gainControl.setValue(dB);
                }
            }
            soundEffectsVolume = volume;
        }
    }

    public float getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    // call in Fishekai.class to load and store in audioManager instance
    public void loadAudioFiles() {
        loadMusic("/sounds/mainMusicLoop.wav");
        loadSoundEffect("intro", "/sounds/intro.wav");
        loadSoundEffect("look", "/sounds/look.wav");
        loadSoundEffect("drink", "/sounds/drink.wav");
        loadSoundEffect("getItem", "/sounds/getItem.wav");
        loadSoundEffect("goodbye", "/sounds/goodbye-friendly.wav");
        loadSoundEffect("map", "/sounds/unfold-a-map.wav");
        loadSoundEffect("help", "/sounds/help.wav");
        loadSoundEffect("jump", "/sounds/jump.wav");
        loadSoundEffect("talk", "/sounds/talk_1.wav");
        loadSoundEffect("catch", "/sounds/catch.wav");
        loadSoundEffect("drop", "/sounds/drop.wav");
        loadSoundEffect("go", "/sounds/go.wav");
        loadSoundEffect("beforeEat", "/sounds/beforeEat.wav");
        loadSoundEffect("eat", "/sounds/eat.wav");
        loadSoundEffect("build", "/sounds/build.wav");
        loadSoundEffect("fish_gets_away", "/sounds/fish_gets_away.wav");
        loadSoundEffect("pull", "/sounds/pull.wav");
    }
}