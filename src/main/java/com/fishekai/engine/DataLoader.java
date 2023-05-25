package com.fishekai.engine;

import com.fishekai.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataLoader {
    // file paths
    private static final String LOCATIONS_PATH = "/json/locations.json";
    private static final String GAME_INFO_PATH = "/json/Game_Information.json";
    private static final String ITEM_PATH = "/json/Item.json";
    private static final String FISH_PATH = "/json/Fish.json";
    private static final String NPC_PATH = "/json/npc.json";

    // reads the locations json file and stores data
    public static Map<String, Location> processLocations() {
        Gson gson = new Gson();

        // takes the data from json file and stores as a List<Location>
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(LOCATIONS_PATH))));
        TypeToken<List<Location>> token = new TypeToken<>() {
        };
        List<Location> listLocations = gson.fromJson(fileReader, token.getType());

        return listLocations.stream()
                .collect(Collectors.toMap(Location::getName, Function.identity()));
    }

    public static Map<String, String> processGameInfo() {
        Gson gson = new Gson();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(GAME_INFO_PATH))));
        TypeToken<Map<String, String>> token = new TypeToken<>() {
        };
        return gson.fromJson(fileReader, token.getType());
    }

    // reads the item json file and stores data
    public static void processItems(Player player, Map<String, Location> locations) {
        Gson gson = new Gson();

        // takes the data from json file and stores the data
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(ITEM_PATH))))) {

            TypeToken<List<Item>> token = new TypeToken<>() {
            };
            List<Item> listItems = gson.fromJson(fileReader, token.getType());

            for (Item item : listItems) {
                // checks where the item should be located
                if (item.getLocation().equalsIgnoreCase("player")) {
                    // place the item in the player inventory
                    player.getInventory().put(item.getName(), item);
                } else if (locations.containsKey(item.getLocation())) {
                    // variable for accessing where the item will be located
                    String locationName = locations.get(item.getLocation()).getName();

                    // place the item in the specified location
                    if (locations.get(locationName).getItems() == null) {
                        Map<String, Item> itemMap = new HashMap<>();
                        itemMap.put(item.getName(), item);
                        locations.get(locationName).setItems(itemMap);
                    } else {
                        locations.get(locationName).getItems().put(item.getName(), item);
                    }
                }
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    // reads the fish json file and stores data
    public static void processFishes(Map<String, Location> locations) {
        Gson gson = new Gson();

        // takes the data from json file and stores the data
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(FISH_PATH))))) {
            TypeToken<List<Fish>> token = new TypeToken<>() {
            };
            List<Fish> listFishes = gson.fromJson(fileReader, token.getType());

            for (Fish fish : listFishes) {
                // variable for accessing where the item will be located
                String location = "North Beach";

                // place the item in the specified location
                if (locations.get(location).getFishes() == null) {
                    Map<String, Fish> fishMap = new HashMap<>();
                    fishMap.put(fish.getName(), fish);
                    locations.get(location).setFishes(fishMap);
                } else {
                    locations.get(location).getFishes().put(fish.getName(), fish);
                }
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void processNpc(Map<String, Location> locations) {
        Gson gson = new Gson();

        // takes the data from json file and stores the data
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(NPC_PATH))))) {
            TypeToken<List<NPC>> token = new TypeToken<>() {
            };
            List<NPC> listNpcs = gson.fromJson(fileReader, token.getType());

            for (NPC npc : listNpcs) {
                // variable for accessing where the npc will be located
                String locationName = locations.get(npc.getLocation()).getName();

                // place the npc in the specified location
                if (locations.get(locationName).getNpc() == null) {
                    Map<String, NPC> npcMap = new HashMap<>();
                    npcMap.put(npc.getType(), npc);
                    locations.get(locationName).setNpc(npcMap);
                } else {
                    locations.get(locationName).getNpc().put(npc.getType(), npc);
                }
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    //    // for internal testing
    public static void main(String[] args) {
        Map<String, Location> locations = processLocations();
        processNpc(locations);
        locations.get("Mystical Grove").getNpc().get("ghost").getRandomQuotes();
    }
}