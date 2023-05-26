package com.fishekai.engine;

import com.fishekai.models.*;

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

    public static void showTitle() {
        System.out.println(banner);
    }

    public static void showHelp() {
        System.out.println(helpMenu);
    }

    public static void showStatus(Player player, Location location, Flask flask) {

        System.out.println("--------------------------------------------------------><(((º>--------------------------------------------------------");
        System.out.println("Player Status");
        System.out.println("Health: " + player.getHp() + "     Hunger: " + player.getHunger() + "     Thirst: " + player.getThirst());
        System.out.println();
        showFish(location);
        System.out.println();
        System.out.println("You are at the: " + location.getName());
        showDescription(location);
        System.out.println();
        showInventory(player);
        System.out.println();
        showItem(location);
        showNPC(location);
        showCharges(flask);
        System.out.println();
        System.out.println("You see paths to " + location.getDirections());
        System.out.println("--------------------------------------------------------><(((º>--------------------------------------------------------");
        System.out.println();
    }



    private static void showDescription(Location location) {
        if (!location.isHasBeenHere()) {
            Introduction.formatText(location.getDescriptions().get("before"), LINE_WIDTH);
        } else {
            Introduction.formatText(location.getDescriptions().get("after"), LINE_WIDTH);
        }
        System.out.println();
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
            System.out.println("You see " + itemAvailable + ". It might be useful.\n");
        }
    }

    public static void showFish(Location location) {
        if (location.getFishes() != null) {
            System.out.println("You see something swimming in the water...");
        }
    }
    public static void showCharges(Flask flask) {
        if (flask.getCharges()> 0){
            System.out.printf("Flask amount: %s/5", flask.getCharges());
        }
    }

    public static void showNPC(Location location) {
        if (location.getNpc() != null) {
            List<String> npcPresent = new ArrayList<>();
            for (Map.Entry<String, NPC> entry : location.getNpc().entrySet()) {
                npcPresent.add(entry.getKey());
            }
            System.out.printf("You see a %s, staring at you, at the %s\n", npcPresent, location.getName());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
