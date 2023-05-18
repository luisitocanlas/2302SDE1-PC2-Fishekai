package com.fishekai.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputParser {

    public static void scan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a verb and a noun:");
        String input = scanner.nextLine();

        List<String> verbs = new ArrayList<>();
        List<String> nouns = new ArrayList<>();

        String[] words = input.split("\\s+");
        for (String word : words) {
            if (isVerb(word)) {
                verbs.add(word);
            } else if (isNoun(word)) {
                nouns.add(word);
            }
        }

        System.out.println("Verbs: " + verbs);
        System.out.println("Nouns: " + nouns);
    }

    private static boolean isVerb(String word) {
        // Implement your verb identification logic here
        // This is a simple example using a predefined list of verbs

        List<String> verbList = List.of("go", "get", "drink", "build", "eat", "cast", "pull", "help", "talk");
        return verbList.contains(word.toLowerCase());
    }

    private static boolean isNoun(String word) {
        // Implement your noun identification logic here
        // This is a simple example using a predefined list of nouns

        List<String> nounList = List.of("North", "East", "South", "West", "water", "fish", "line", "pole");
        return nounList.contains(word.toLowerCase());
    }
}

