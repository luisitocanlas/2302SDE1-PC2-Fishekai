package com.fishekai.engine;

import com.fishekai.utilities.Prompter;

import java.util.Scanner;

import static com.fishekai.utilities.Console.clear;

class Introduction {
    private static final int LINE_WIDTH = 120;

    private final Prompter prompter = new Prompter(new Scanner(System.in));

    public void showIntro() {
        // clear console
        clear();

        // call the game information from DataLoader
        System.out.println("********************************************************><(((º>********************************************************");
        System.out.println("The Story:");
        formatText(DataLoader.processGameInfo().get("story"), LINE_WIDTH);
        System.out.println();

        System.out.println("Objective:");
        formatText(DataLoader.processGameInfo().get("objective"), LINE_WIDTH);
        System.out.println();

        System.out.println("Who are you?");
        formatText(DataLoader.processGameInfo().get("player_info"), LINE_WIDTH);
        System.out.println();

        System.out.println("Winning Condition");
        formatText(DataLoader.processGameInfo().get("winning_condition"), LINE_WIDTH);
        System.out.println();

        System.out.println("********************************************************><(((º>********************************************************");

        // insert prompt here to tell the player to press any key to continue
        askToContinue();
        clear();
    }

    public void askToContinue() {
        prompter.prompt("Enter [Y]es to Continue:\n><(((º> ",
                "Y|y",
                "This is not a valid option!\n");

    }

    public static void formatText(String text, int width) {
        StringBuilder formattedText = new StringBuilder();
        int currentLineLength = 0;

        String[] words = text.split("\\s+");

        for (String word : words) {
            int wordLength = word.length();

            // Check if adding the word to the current line exceeds the line width
            if (currentLineLength + wordLength + 1 > width) {
                formattedText.append(System.lineSeparator()); // Start a new line
                currentLineLength = 0; // Reset the current line length
            }

            formattedText.append(word).append(" "); // Add the word to the formatted text
            currentLineLength += wordLength + 1; // Update the current line length
        }

        System.out.println(formattedText.toString());
    }

}