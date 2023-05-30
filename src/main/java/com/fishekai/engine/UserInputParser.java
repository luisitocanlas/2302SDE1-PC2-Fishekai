package com.fishekai.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInputParser {
    private static final String EXCLUDED_WORDS_PATH = "/texts/excluded_words.txt";
    private static final String GO_SYNONYM_PATH = "/texts/go_synonym.txt";
    private static final String LOOK_SYNONYM_PATH = "/texts/look_synonym.txt";
    private static final String DROP_SYNONYM_PATH = "/texts/drop_synonym.txt";
    private static final String GET_SYNONYM_PATH = "/texts/get_synonym.txt";

    private final Set<String> excludedWords = new HashSet<>();
    private final Set<String> goSynonym = new HashSet<>();
    private final Set<String> lookSynonym = new HashSet<>();
    private final Set<String> dropSynonym = new HashSet<>();
    private final Set<String> getSynonym = new HashSet<>();
    private final Set<String> directionsList = new HashSet<>(List.of("north", "east", "south", "west"));
    private final Set<String> itemList = new HashSet<>(List.of("amulet", "parachute", "hook", "stick", "flask", "rod"));
    private final Set<String> foodList = new HashSet<>(List.of("water", "banana", "apple", "sunfish", "fangfish", "tuna", "parrotfish", "lanternfish"));
    private final Set<String> npcList = new HashSet<>(List.of("ghost", "ball"));

    public Set<String> getExcludedWords() {
        return excludedWords;
    }

    public Set<String> getDirectionsList() {
        return directionsList;
    }

    public Set<String> getItemList() {
        return itemList;
    }

    public Set<String> getFoodList() {
        return foodList;
    }

    public Set<String> getNpcList() {
        return npcList;
    }

    public String[] scan(String input) {

        String[] words = input.split("\\s+");

        String[] output = Arrays.stream(words)
                .map(word -> {
                    if (goSynonym.contains(word)) {
                        return "go";
                    } else if (lookSynonym.contains(word)) {
                        return "look";
                    } else if (dropSynonym.contains(word)) {
                        return "drop";
                    } else if (getSynonym.contains(word)) {
                        return "get";
                    } else {
                        return word;
                    }
                })
                .filter(word -> !excludedWords.contains(word))
                .toArray(String[]::new);

        return output;
    }

    private static void loadFromFile(Set<String> outputSet, String filePath) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(filePath))))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                outputSet.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTextArguments() {
        loadFromFile(excludedWords, EXCLUDED_WORDS_PATH);
        loadFromFile(goSynonym, GO_SYNONYM_PATH);
        loadFromFile(lookSynonym, LOOK_SYNONYM_PATH);
        loadFromFile(dropSynonym, DROP_SYNONYM_PATH);
        loadFromFile(getSynonym, GET_SYNONYM_PATH);
    }
}

