package com.fishekai.utilities;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AudioManager {
    private static final float MUSIC_MIN_VOLUME = 0.0f;
    private static final float MUSIC_MAX_VOLUME = 1.0f;
    private static final float EFFECT_MIN_VOLUME = 0.0f;
    private static final float EFFECT_MAX_VOLUME = 1.0f;
    private Random random = new Random();

    private Clip musicClip;
    private final Map<String, Clip> soundEffects;
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
        if (soundEffectsEnabled) {
            setSoundEffectsVolume(EFFECT_MAX_VOLUME);
        } else {
            setSoundEffectsVolume(EFFECT_MIN_VOLUME);
        }
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
        if (volume >= MUSIC_MIN_VOLUME && volume <= MUSIC_MAX_VOLUME && musicClip != null) {
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

    public float getMusicMinVolume() {
        return MUSIC_MIN_VOLUME;
    }

    public float getMusicMaxVolume() {
        return MUSIC_MAX_VOLUME;
    }

    public void volumeUp() {
        if (musicVolume == MUSIC_MAX_VOLUME) {
            System.out.println("Already at maximum volume.");
        } else {
            float newVolume = musicVolume + 0.1f;
            if (newVolume > MUSIC_MAX_VOLUME) {
                newVolume = MUSIC_MAX_VOLUME;
            }
            setMusicVolume(newVolume);
        }
    }

    public void volumeDown() {
        if (musicVolume == MUSIC_MIN_VOLUME) {
            System.out.println("Already at minimum volume.");
        } else {
            float newVolume = musicVolume - 0.1f;
            if (newVolume < MUSIC_MIN_VOLUME) {
                newVolume = MUSIC_MIN_VOLUME;
            }
            setMusicVolume(newVolume);
        }
    }

    public void setSoundEffectsVolume(float volume) {
        if (volume >= EFFECT_MIN_VOLUME && volume <= EFFECT_MAX_VOLUME) {
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
    public void randomEat(){
        int randomIndex = random.nextInt(4) + 1;
        String filename = "eat" + randomIndex;
        playSoundEffect(filename);
    }
    public void randomGo() {
        int randomGo = random.nextInt(4) + 1;
        String goSounds = "go" + randomGo;
        playSoundEffect(goSounds);
    }
    public void randomDrink() {
        int randomDrink = random.nextInt(3) + 1;
        String drinkSounds = "drink" + randomDrink;
        playSoundEffect(drinkSounds);
    }
    public void randomTalk() {
        int randomTalk = random.nextInt(3) + 1;
        String talkSounds = "talk" + randomTalk;
        playSoundEffect(talkSounds);
    }
    public void randomGet() {
        int randomGet = random.nextInt(4) + 1;
        String getSounds = "getItem" + randomGet;
        playSoundEffect(getSounds);
    }
    public void randomPull() {
        int randomPull = random.nextInt(3) + 1;
        String pullSounds = "pull" + randomPull;
        playSoundEffect(pullSounds);
    }
    public void randomReel() {
        int randomReel = random.nextInt(3) + 1;
        String reelSounds = "reel" + randomReel;
        playSoundEffect(reelSounds);
    }
    // call in Fishekai.class to load and store in audioManager instance
    public void loadAudioFiles() {
        loadMusic("/sounds/mainMusicLoop.wav");
        loadSoundEffect("build", "/sounds/build.wav");
        loadSoundEffect("catch", "/sounds/catch.wav");
        loadSoundEffect("ding", "/sounds/ding.wav");
        loadSoundEffect("drink1", "/sounds/drink1.wav");
        loadSoundEffect("drink2", "/sounds/drink2.wav");
        loadSoundEffect("drink3", "/sounds/drink3.wav");
        loadSoundEffect("drop", "/sounds/drop.wav");
        loadSoundEffect("eat1", "/sounds/eat1.wav");
        loadSoundEffect("eat2", "/sounds/eat2.wav");
        loadSoundEffect("eat3", "/sounds/eat3.wav");
        loadSoundEffect("eat4", "/sounds/eat4.wav");
        loadSoundEffect("fish_gets_away", "/sounds/fish_gets_away.wav");
        loadSoundEffect("getItem1", "/sounds/getItem1.wav");
        loadSoundEffect("getItem2", "/sounds/getItem2.wav");
        loadSoundEffect("getItem3", "/sounds/getItem3.wav");
        loadSoundEffect("getItem4", "/sounds/getItem4.wav");
        loadSoundEffect("go1", "/sounds/go1.wav");
        loadSoundEffect("go2", "/sounds/go2.wav");
        loadSoundEffect("go3", "/sounds/go3.wav");
        loadSoundEffect("go4", "/sounds/go4.wav");
        loadSoundEffect("goodbye", "/sounds/goodbye-friendly.wav");
        loadSoundEffect("help", "/sounds/help.wav");
        loadSoundEffect("intro", "/sounds/intro.wav");
        loadSoundEffect("jump", "/sounds/jump.wav");
        loadSoundEffect("jungle", "/sounds/jungle_growl.wav");
        loadSoundEffect("look", "/sounds/look.wav");
        loadSoundEffect("map_open", "/sounds/map-open.wav");
        loadSoundEffect("mountain", "/sounds/mountain-river.wav");
        loadSoundEffect("pull1", "/sounds/pull1.wav");
        loadSoundEffect("pull2","/sounds/pull2.wav");
        loadSoundEffect("pull3", "/sounds/pull3.wav");
        loadSoundEffect("reel1", "/sounds/reel1.wav");
        loadSoundEffect("reel2", "/sounds/reel2.wav");
        loadSoundEffect("reel3", "/sounds/reel3.wav");
        loadSoundEffect("talk1", "/sounds/talk1.wav");
        loadSoundEffect("talk2", "/sounds/talk2.wav");
        loadSoundEffect("talk3", "/sounds/talk3.wav");
        loadSoundEffect("unfold_map", "/sounds/unfold-a-map.wav");
        loadSoundEffect("waves", "/sounds/waves-of-hawaii.wav");
    }
}