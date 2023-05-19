package com.fishekai.objects;

import java.util.HashMap;
import java.util.List;

public class Location {

    // fields
    private final String name;
    private HashMap<String, String> directions;
    private boolean hasBeenHere = false; // default value is false
    private HashMap<String, String> descriptions;
    private List<Item> fishes;
    private List<Item> items;

    // constructors
    public Location(String name) {
        this.name = name;
    }

    public Location(String name, HashMap<String, String> directions, HashMap<String, String> descriptions) {
        this.name = name;
        this.directions = directions;
        this.descriptions = descriptions;
    }

    // accessors
    public String getName() {
        return name;
    }

    public HashMap<String, String> getDirections() {
        return directions;
    }

    public void setDirections(HashMap<String, String> directions) {
        this.directions = directions;
    }

    public HashMap<String, String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(HashMap<String, String> descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isHasBeenHere() {
        return hasBeenHere;
    }

    public void setHasBeenHere(boolean hasBeenHere) {
        this.hasBeenHere = hasBeenHere;
    }

    public List<Item> getFishes() {
        return fishes;
    }

    public void setFishes(List<Item> fishes) {
        this.fishes = fishes;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // for internal testing
//    public static void main(String[] args) {
//        Location north_beach = new Location("North Beach");
//
//        HashMap<String, Location> direction = new HashMap<>();
//        direction.put("north", north_beach);
//
//        HashMap<String, String> description = new HashMap<>();
//        description.put("before", "Description when first entered.");
//        description.put("after", "Description for subsequent entries.");
//
//        Location beach = new Location("Beach", direction, description);
//
//        Location current_location = beach;
//
//        System.out.println(current_location.getName());
//
//        System.out.println(current_location.getDirections().keySet());
//    }
}

