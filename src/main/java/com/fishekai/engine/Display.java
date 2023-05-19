package com.fishekai.engine;

import com.fishekai.objects.Character;
import com.fishekai.objects.Item;
import com.fishekai.objects.Location;

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
    public static void showItem(Item item, Location location){
        if (location.getItems().contains(item)){
            System.out.println("You see a " + item.getName());
        }

    }

}
