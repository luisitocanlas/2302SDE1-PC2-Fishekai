package com.fishekai.engine;

import com.fishekai.objects.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Display {

    private static final int LINE_WIDTH = 120;

    private static String banner;
    private static String helpMenu;
    private static String gameMap;
    private static String inBeach;
    private static String inCave;
    private static String inJungle;
    private static String inMountain;
    private static String inMysticalGrove;
    private static String inNorthBeach;
    private static String inVolcano;
    private static String inWaterfall;

    public static void showTitle() {
        System.out.println(banner);
    }

    public static void showHelp() {
        System.out.println(helpMenu);
    }

    public static void showMap(Map<String, Location> locations, Location current_location) {
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

    public static void showStatus(Player player, Location location) {

        System.out.println("--------------------------><(((º>--------------------------");
        System.out.println("Player Status");
        System.out.println("Health: " + player.getHp() + "     Hunger: " + player.getHunger() + "     Thirst: " + player.getThirst());
        System.out.println();
        showFish(location);
        System.out.println();
        System.out.println("You are in the at the: " + location.getName());
        showDescription(location);
        System.out.println();
        showInventory(player);
        System.out.println();
        showItem(location);
        System.out.println();
        showNPC(location);
        System.out.println();
        System.out.println("You see paths to " + location.getDirections());
        System.out.println("--------------------------><(((º>--------------------------");
        System.out.println();
    }

    private static void showDescription(Location location) {
        if (!location.isHasBeenHere()) {
            Introduction.formatText(location.getDescriptions().get("before"), LINE_WIDTH);
            System.out.println();
        } else {
            Introduction.formatText(location.getDescriptions().get("after"), LINE_WIDTH);
            System.out.println();
        }
    }

    private static void showInventory(Player player) {
        List<String> inventoryList = new ArrayList<>();
        for (Map.Entry<String, Item> entry : player.getInventory().entrySet()) {
            inventoryList.add(entry.getKey());
        }
        System.out.printf("Inventory: %s\n", inventoryList);
    }

    public static void showItem(Location location) {
        if (location.getItems() != null) {
            List<String> itemAvailable = new ArrayList<>();
            for (Map.Entry<String, Item> entry : location.getItems().entrySet()) {
                itemAvailable.add(entry.getKey());
            }
            System.out.println("You see:" + itemAvailable);
        }
    }

    public static void showFish(Location location) {
        if (location.getFishes() != null) {
//            List<String> fishAvailable = new ArrayList<>();
//            for (Map.Entry<String, Fish> entry : location.getFishes().entrySet()) {
//                fishAvailable.add(entry.getKey());
//            }
//            System.out.printf("You see something swimming in the water...\n%s\n", fishAvailable);
            System.out.println("You see something swimming in the water...");
        }
    }

    public static void showNPC(Location location) {
        if (location.getNpc() != null) {
            List<String> npcPresent = new ArrayList<>();
            for (Map.Entry<String, NPC> entry : location.getNpc().entrySet()) {
                npcPresent.add(entry.getKey());
            }
            System.out.printf("You see a %s at the %s\n", npcPresent, location.getName());
        }
    }

    public static String readResource(String path) throws IOException {
        try (InputStream is = Display.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + path);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    static {
        try {
            banner = readResource("/images/banner.txt");
            helpMenu = readResource("/images/helpMenu.txt");
            gameMap = readResource("/images/game_map.txt");
            inBeach = readResource("/images/inBeachMap.txt");
            inCave = readResource("/images/inCaveMap.txt");
            inJungle = readResource("/images/inJungleMap.txt");
            inMountain = readResource("/images/inMountainMap.txt");
            inMysticalGrove = readResource("/images/inMysticalGroveMap.txt");
            inNorthBeach = readResource("/images/inNorthBeachMap.txt");
            inVolcano = readResource("/images/inVolcanoMap.txt");
            inWaterfall = readResource("/images/inWaterfallMap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
