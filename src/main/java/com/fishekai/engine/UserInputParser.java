package com.fishekai.engine;

import java.util.List;

public class UserInputParser {

    public static String[] scan(String input) {

        String[] output = new String[2];

        String[] words = input.split(" ");
        for (String word : words) {
            if (isVerb(word)) {
                output[0] = word;
            } else if (isNoun(word)) {
                output[1] = word;
            }
        }
        return output;
    }

    private static boolean isVerb(String word) {
        // Implement verb identification logic here
        // This is a simple example using a predefined list of verbs

        List<String> verbList = List.of("go", "get", "drink", "build", "eat", "cast", "pull", "help", "talk", "quit");
        return verbList.contains(word.toLowerCase());
    }

    private static boolean isNoun(String word) {
        // Implement your noun identification logic here
        // This is a simple example using a predefined list of nouns

        List<String> nounList = List.of("north", "east", "south", "west", "water", "fish", "line", "pole");
        return nounList.contains(word.toLowerCase());
    }

    /*public static void main(String[] args) {
        String[] words = UserInputParser.scan("go to the east");
        for (String word : words) {
            System.out.println(word);
        }
    }*/
}

