package com.fishekai.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.fishekai.utilities.Console.pause;
import static org.junit.jupiter.api.Assertions.*;

class AudioManagerTest {
    AudioManager audioManager = new AudioManager();

    @BeforeEach
    void setUp() {
        audioManager.loadAudioFiles();
    }

//    @Test
//    void playMusic() {
//        audioManager.playMusic(false);
//        pause(50_000);
//        audioManager.stopMusic();
//    }
//
//    @Test
//    void playSoundEffect() {
//        audioManager.playSoundEffect("pull");
//        pause(10_000);
//    }
}