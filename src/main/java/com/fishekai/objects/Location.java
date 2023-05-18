package com.fishekai.objects;

import java.util.HashMap;
import java.util.List;

public class Location {

    // fields
    private final String name;
    private final HashMap<String, String> directions;
    private boolean hasBeenHere = false; // default value is false
    private final HashMap<String, String> descriptions;
    private List<String> fishes;
    private List<String> items;

    // constructors
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

    public HashMap<String, String> getDescriptions() {
        return descriptions;
    }

    public boolean isHasBeenHere() {
        return hasBeenHere;
    }

    public void setHasBeenHere(boolean hasBeenHere) {
        this.hasBeenHere = hasBeenHere;
    }

    public List<String> getFishes() {
        return fishes;
    }

    public void setFishes(List<String> fishes) {
        this.fishes = fishes;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    // for internal testing
    public static void main(String[] args) {
        HashMap<String, String> direction = new HashMap<>();
        direction.put("north", "North Beach");
        direction.put("east", "Jungle");

        HashMap<String, String> description = new HashMap<>();
        description.put("before", "Description when first entered.");
        description.put("after", "Description for subsequent entries.");

        Location location = new Location("Beach", direction, description);

        System.out.println(location.getName());

        System.out.println(location.getDirections());
        System.out.println(location.getDirections().get("north"));

        System.out.println(location.getDescriptions());
        System.out.println(location.getDescriptions().get("after"));

        System.out.println(location.getItems());
        System.out.println(location.getFishes());
    }
}

