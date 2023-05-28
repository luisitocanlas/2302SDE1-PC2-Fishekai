package com.fishekai.engine;

import com.fishekai.utilities.AudioManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VolumeControl {

    public VolumeControl(AudioManager audioManager) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(new GridLayout(1, 4));

        JButton volumeUpB = new JButton("Volume Up");
        volumeUpB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioManager.volumeUp();
            }
        });
        window.add(volumeUpB);

        JButton volumeDownB = new JButton("Volume Down");
        volumeDownB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioManager.volumeDown();
            }
        });
        window.add(volumeDownB);

        JButton muteB = new JButton("Mute");
        muteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                sound.volumeMute();
            }
        });
        window.add(muteB);

        JButton musicB = new JButton("Music on/off");
        musicB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (audioManager.isMusicPlaying()) {
                    audioManager.stopMusic();
                } else {
                    audioManager.playMusic(true);
                }
            }
        });
        window.add(musicB);

        window.pack();
        window.setVisible(true);
    }

}
