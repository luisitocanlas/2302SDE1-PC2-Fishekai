package com.fishekai.engine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserInputParser {

    private final Set<String> excludedWords = new HashSet<>(List.of("to", "at", "your", "the"));
    private final List<String> directionsList = List.of("north", "east", "south", "west");
    private final List<String> itemList = List.of("parachute", "hook", "stick");
    private final List<String> foodList = List.of("water", "banana", "apple");
    private final List<String> fishList = List.of("Mystical Sunfish", "Venomous Fangfish", "Silverscale Tuna", "Rainbow Parrotfish", "Luminous Lanternfish");

    public List<String> getDirectionsList() {
        return directionsList;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public List<String> getFoodList() {
        return foodList;
    }

    public List<String> getFishList() {
        return fishList;
    }

    public String[] scan(String input) {

        String[] words = input.split("\\s+");

        String[] output = Arrays.stream(words)
                .filter(word -> !excludedWords.contains(word))
                .toArray(String[]::new);

        return output;
    }

    private boolean isVerb(String word) {
        // Implement verb identification logic here
        // This is a simple example using a predefined list of verbs

        List<String> verbList = List.of("go", "get", "drink", "build", "eat", "cast", "pull", "help", "talk", "quit");
        return verbList.contains(word.toLowerCase());
    }

    private boolean isNoun(String word) {
        // Implement your noun identification logic here
        // This is a simple example using a predefined list of nouns

        List<String> nounList = List.of("north", "east", "south", "west", "water", "fish", "line", "pole");
        return nounList.contains(word.toLowerCase());
    }

    //for testing
    /*public static void main(String[] args) {
        UserInputParser parser = new UserInputParser();
        String[] words = parser.scan("go to your mommas house");
        for (String word : words) {
            System.out.println(word);
        }
    }*/
}

