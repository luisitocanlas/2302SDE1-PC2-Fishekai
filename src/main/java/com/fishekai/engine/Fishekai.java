package com.fishekai.engine;

import com.fishekai.utilities.Prompter;
import com.fishekai.utilities.SplashApp;

import java.util.Scanner;

public class Fishekai implements SplashApp {
    //fields
    private final Introduction intro = new Introduction();
    private final Prompter prompter = new Prompter(new Scanner(System.in));

    // methods
    public void start() {
        // show title here
        Display.showTitle();

        // ask user for input and store it
        String input = prompter.prompt("Would you like to play a new game?\n", "Yes|yes|y|No|no|n", "That is not a valid input\n");

        // if New Game
        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            begin();
        }

        // if Quit
        else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            // exits game
        }

//        else {
//
//        }
    }

    private void begin() {
        // show the intro
        intro.showIntro();
        // press any key to continue


//        while (true) {
//          if(move[0] == "help"){
//            Display.showHelp();

//        }
        //testing to see if showHelp works.
        Display.showHelp();
    }
}