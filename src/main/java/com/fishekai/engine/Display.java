package com.fishekai.engine;

import com.fishekai.objects.Character;
import com.fishekai.objects.Item;
import com.fishekai.objects.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Display {

    private static String banner;
    private static String helpMenu;


    public static void showTitle() {
        System.out.println(banner);
    }

    public static void showHelp() {
        System.out.println(helpMenu);
    }

    public static String readResource(String path) throws IOException {
        try (InputStream is = Display.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + path);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static void showStatus(Character character, Location location){

        if (!location.isHasBeenHere()) { // if false
            System.out.println("--------------------------><(((º>--------------------------");
            System.out.println("Health: " + character.getHp() + "     Hunger: " + character.getHunger() + "     Thirst: " + character.getThirst());
            System.out.println();
            System.out.println();
            System.out.println("You are in the at the: " + location.getName());
            System.out.println("Description: " + location.getDescriptions().get("before"));
            System.out.println();
            System.out.println("Inventory: " + character.getInventory());
            System.out.println();
            System.out.println("You see paths to " + location.getDirections());
            System.out.println("--------------------------><(((º>--------------------------");
            System.out.println();
        }
        else { // if true
            System.out.println("--------------------------><(((º>--------------------------");
            System.out.println("Health: " + character.getHp() + "     Hunger: " + character.getHunger() + "     Thirst: " + character.getThirst());
            System.out.println();
            System.out.println();
            System.out.println("You are in the at the: " + location.getName());
            System.out.println("Description: " + location.getDescriptions().get("after"));
            System.out.println();
            System.out.println("Inventory: " + character.getInventory());
            System.out.println();
            System.out.println("You see paths to " + location.getDirections());
            System.out.println("--------------------------><(((º>--------------------------");
            System.out.println();
        }
    }

    public static void showItem(Location location){
        if (location.getItems() != null) {
            List<String> itemAvailable = new ArrayList<>();
            for (Map.Entry<String, Item> entry : location.getItems().entrySet()) {
                itemAvailable.add(entry.getKey());
            }
            System.out.println(itemAvailable);
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
