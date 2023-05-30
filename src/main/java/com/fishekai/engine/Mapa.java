package com.fishekai.engine;

import com.fishekai.models.Location;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.fishekai.engine.Display.readResource;

public class Mapa {

    private static String gameMap;
    private static String inBeach;
    private static String inCave;
    private static String inJungle;
    private static String inMountain;
    private static String inMysticalGrove;
    private static String inNorthBeach;
    private static String inVolcano;
    private static String inWaterfall;

    public static Set<String> visitedLocations = new HashSet<>();

    public static void showStaticMap(Map<String, Location> locations, Location current_location) {
        if (locations.get("Beach").equals(current_location)) {
            System.out.println(inBeach);
        } else if (locations.get("Cave").equals(current_location)) {
            System.out.println(inCave);
        } else if (locations.get("Jungle").equals(current_location)) {
            System.out.println(inJungle);
        } else if (locations.get("Mountain").equals(current_location)) {
            System.out.println(inMountain);
        } else if (locations.get("Mystical Grove").equals(current_location)) {
            System.out.println(inMysticalGrove);
        } else if (locations.get("North Beach").equals(current_location)) {
            System.out.println(inNorthBeach);
        } else if (locations.get("Volcano").equals(current_location)) {
            System.out.println(inVolcano);
        } else if (locations.get("Waterfall").equals(current_location)) {
            System.out.println(inWaterfall);
        } else {
            System.out.println(gameMap);
        }
    }

    public static void locationCheck(Location current_location) {
        if (!visitedLocations.contains(current_location.getName())) {
            visitedLocations.add(current_location.getName());
        }
    }

    static {
        try {
            gameMap = readResource("/maps/game_map.txt");
            inBeach = readResource("/maps/inBeachMap.txt");
            inCave = readResource("/maps/inCaveMap.txt");
            inJungle = readResource("/maps/inJungleMap.txt");
            inMountain = readResource("/maps/inMountainMap.txt");
            inMysticalGrove = readResource("/maps/inMysticalGroveMap.txt");
            inNorthBeach = readResource("/maps/inNorthBeachMap.txt");
            inVolcano = readResource("/maps/inVolcanoMap.txt");
            inWaterfall = readResource("/maps/inWaterfallMap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // testing ---------------------------------------------------------------------------------------------------------
    public static Set<String> visitedLoc = new HashSet<>();

    public static void visitLoc(String loc) {
        visitedLoc.add(loc);
    }

    private static String[][] asciiMap = {
            {"+", "-", "-", "+", "-", "-", "+"},
            {"|", " ", " ", " ", " ", " ", "|"},
            {"|", " ", " ", "X", " ", " ", "+"},
            {"|", " ", " ", " ", " ", " ", "|"},
            {"+", "-", "-", "-", "-", "-", "+"}
    };

    public static void showDynamicMap() {
        for (String[] row : asciiMap) {
            for (String cell : row) {
                if (cell.equals("X") || visitedLoc.contains(cell)) {
                    // Display a visited location or the player's current position
                    System.out.print("X");
                } else {
                    // Display an unvisited location or any other map element
                    System.out.print(cell);
                }
            }
            System.out.println(); // Move to the next row
        }
    }
    // testing ---------------------------------------------------------------------------------------------------------
}