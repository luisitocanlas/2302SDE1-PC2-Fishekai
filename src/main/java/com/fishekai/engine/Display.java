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
}
