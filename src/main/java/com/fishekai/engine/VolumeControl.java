package com.fishekai.engine;

import com.fishekai.utilities.AudioManager;

import javax.swing.*;
import java.awt.*;


public class VolumeControl {
    private JFrame window;

    public VolumeControl(AudioManager audioManager) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(new FlowLayout());

        JButton volumeUpB = new JButton("Volume Up");
        volumeUpB.addActionListener(e -> audioManager.volumeUp());
        window.add(volumeUpB);

        JButton volumeDownB = new JButton("Volume Down");
        volumeDownB.addActionListener(e -> audioManager.volumeDown());
        window.add(volumeDownB);

        JButton effectB = new JButton("Sound Effects On/Off");
        effectB.addActionListener(e -> {
            if (audioManager.areSoundEffectsEnabled()) {
                audioManager.setSoundEffectsEnabled(false);
            } else {
                audioManager.setSoundEffectsEnabled(true);
            }
        });
        window.add(effectB);

        JButton musicB = new JButton("Music On/Off");
        musicB.addActionListener(e -> {
            if (audioManager.isMusicPlaying()) {
                audioManager.stopMusic();
            } else {
                audioManager.playMusic(true);
            }
        });
        window.add(musicB);

        window.pack();
        window.setVisible(true);
    }

    public void showWindow() {
        window.setVisible(true);
    }

}
