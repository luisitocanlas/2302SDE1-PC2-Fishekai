package com.fishekai.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VolumeControl {

    public VolumeControl(Sound sound) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(new GridLayout(1, 3));

        JButton volumeUpB = new JButton("Volume Up");
        volumeUpB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeUp();
            }
        });
        window.add(volumeUpB);

        JButton volumeDownB = new JButton("Volume Down");
        volumeDownB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeDown();
            }
        });
        window.add(volumeDownB);

        JButton muteB = new JButton("Mute");
        muteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeMute();
            }
        });
        window.add(muteB);

        window.pack();
        window.setVisible(true);
    }

}
