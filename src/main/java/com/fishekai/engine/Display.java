package com.fishekai.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Display {

    public static void showTitle() {
        List<String> banner = new ArrayList<>();
        try {
            banner = Files.readAllLines(Path.of("images/banner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (var line : banner) {
            System.out.println(line);
        }
    }
    public static void showHelp() {
        List<String> helpMenu = new ArrayList<>();
        try {
            helpMenu = Files.readAllLines(Path.of("images/helpMenu.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (var line : helpMenu) {
            System.out.println(line);
        }
    }
}
