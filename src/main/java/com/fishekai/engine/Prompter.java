package com.fishekai.engine;

import com.sun.tools.javac.util.Log;
import com.apps.util.Prompter;
import com.apps.util.Console;
import java.util.Scanner;
import com.fishekai.game;

public class Prompter {
    Prompter prompter = new Prompter();
    User user = new User();

        public void newGame() {
        // Ask the user if they want to play
            while (true) {
                String answer = prompter.prompt("Would you like to play a game?");
            // Test if answer is a valid input
                if (answer != null && answer.trim().equalsIgnoreCase("yes")) {
                    game.play();
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
