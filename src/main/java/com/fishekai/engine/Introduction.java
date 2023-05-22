package com.fishekai.engine;

import com.fishekai.utilities.Prompter;

import java.util.Scanner;

import static com.fishekai.utilities.Console.clear;

class Introduction {
    private final Prompter prompter = new Prompter(new Scanner(System.in));

    public void showIntro() {
        // clear console
        clear();

        // call the game information from DataLoader
        System.out.println("********************************************************><(((º>********************************************************");
        System.out.println("The Story:");
        System.out.printf("%s\n\n", DataLoader.processGameInfo().get("story"));

        System.out.println("Objective:");
        System.out.printf("%s\n\n", DataLoader.processGameInfo().get("objective"));

        System.out.println("Who are you?");
        System.out.printf("%s\n\n", DataLoader.processGameInfo().get("player_info"));

        System.out.println("Winning Condition");
        System.out.printf("%s\n", DataLoader.processGameInfo().get("winning_condition"));

        System.out.println("********************************************************><(((º>********************************************************");

        // insert prompt here to tell the player to press any key to continue
        askToContinue();
    }

    public void askToContinue() {
        prompter.prompt("Enter [Y]es to Continue:\n><(((º> ",
                "Y|y",
                "This is not a valid option!\n");
    }

    // for internal testing
//    public static void main(String[] args) {
//        Introduction intro = new Introduction();
//        intro.showIntro();
//    }
}