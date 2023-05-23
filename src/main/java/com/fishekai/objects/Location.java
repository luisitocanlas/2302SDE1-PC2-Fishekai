package com.fishekai.objects;

import java.util.HashMap;
import java.util.Map;

public class Location {

    // fields
    private final String name;
    private Map<String, String> directions;
    private boolean hasBeenHere = false; // default value is false
    private Map<String, String> descriptions;
    private Map<String, Fish> fishes = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();
    private Map<String, NPC> npc = new HashMap<>();

    // constructors
    public Location(String name, Map<String, String> directions, Map<String, String> descriptions) {
        this.name = name;
        this.directions = directions;
        this.descriptions = descriptions;
    }

    // accessors
    public String getName() {
        return name;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    public boolean isHasBeenHere() {
        return hasBeenHere;
    }

    public void setHasBeenHere(boolean hasBeenHere) {
        this.hasBeenHere = hasBeenHere;
    }

    public Map<String, Fish> getFishes() {
        return fishes;
    }

    public void setFishes(Map<String, Fish> fishes) {
        this.fishes = fishes;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public Map<String, NPC> getNpc() {
        return npc;
    }

    public void setNpc(Map<String, NPC> npc) {
        this.npc = npc;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", directions=" + directions +
                ", description='" + descriptions + '\'' +
                ", items=" + items +
                '}';
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

