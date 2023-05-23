package com.fishekai.engine;

import com.fishekai.objects.Fish;
import com.fishekai.objects.Item;
import com.fishekai.objects.Location;
import com.fishekai.objects.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Display {

    private static String banner;
    private static String helpMenu;
    private static String gameMap;


    public static void showTitle() {
        System.out.println(banner);
    }

    public static void showHelp() {
        System.out.println(helpMenu);
    }

    public static void showMap() {
        System.out.println(gameMap);
    }

    public static String readResource(String path) throws IOException {
        try (InputStream is = Display.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + path);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static void showStatus(Player player, Location location){

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
        System.out.println("You see paths to " + location.getDirections());
        System.out.println("--------------------------><(((º>--------------------------");
        System.out.println();
    }

    private static void showDescription(Location location) {
        if (!location.isHasBeenHere()) {
            System.out.printf("Description: %s", location.getDescriptions().get("before"));
        } else {
            System.out.printf("Description: %s", location.getDescriptions().get("after"));
        }
    }

    private static void showInventory(Player player) {
        List<String> inventoryList = new ArrayList<>();
        for (Map.Entry<String, Item> entry : player.getInventory().entrySet()) {
            inventoryList.add(entry.getKey());
        }
        System.out.printf("Inventory: %s\n", inventoryList);
    }

    public static void showItem(Location location){
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
            List<String> fishAvailable = new ArrayList<>();
            for (Map.Entry<String, Fish> entry : location.getFishes().entrySet()) {
                fishAvailable.add(entry.getKey());
            }
            System.out.printf("You see something swimming in the water...\n%s\n", fishAvailable);
        }
    }

    static {
        try {
            banner = readResource("/images/banner.txt");
            helpMenu = readResource("/images/helpMenu.txt");
            gameMap = readResource("/images/game_map.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
