package com.fishekai.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserInputParserTest {
    UserInputParser parser = new UserInputParser();

    @BeforeEach
    void setUp() {
        parser.loadTextArguments();
    }

    @Test
    void scan_synonymousCommands() {
        String input = "run to the far west";
        String[] words = parser.scan(input);

        for (String word : words) {
            System.out.println(word);
        }
    }

    @Test
    void loadFromFile_loadsTextsFiles() {
        System.out.println(parser.getExcludedWords());
    }
}