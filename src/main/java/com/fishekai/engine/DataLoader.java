package com.fishekai.engine;

import com.fishekai.objects.Fish;
import com.fishekai.objects.Item;
import com.fishekai.objects.Location;
import com.fishekai.objects.Player;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    // references to be called
    public static String gameInfo;

    // reads the locations json file and stores data
    public static Map<String, Location> processLocations() {
        Gson gson = new Gson();

        // takes the data from json file and stores as a List<Location>
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(LOCATIONS_PATH))));
        TypeToken<List<Location>> token = new TypeToken<>() {
        };
        List<Location> listLocations = gson.fromJson(fileReader, token.getType());

        Map<String, Location> mappedLocations = listLocations.stream()
                .collect(Collectors.toMap(Location::getName, Function.identity()));

        // takes in the List<Location> and stores as a hashmap
        /*HashMap<String, Location> mappedLocations = new HashMap<>();
        for (Location location : listLocations) {
            mappedLocations.put(location.getName(), location);
        }*/
        return mappedLocations;
    }

    //
    public static Map<String, String> processGameInfo() {
        Gson gson = new Gson();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(GAME_INFO_PATH))));
        TypeToken<Map<String, String>> token = new TypeToken<>() {
        };
        return gson.fromJson(fileReader, token.getType());
    }


    // used for reading and storing text files
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
            gameInfo = readResource("/images/game_info.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads the item json file and stores data
    public static void processItems(Player player, Map<String, Location> locations) {
        Gson gson = new Gson();

        // takes the data from json file and stores the data
        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(ITEM_PATH))))) {

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
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads the fish json file and stores data
    public static void processFishes(Map<String, Location> locations) {
        Gson gson = new Gson();

        // takes the data from json file and stores the data
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(FISH_PATH))));
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
    }
}


//    // for internal testing
//    public static void main(String[] args) {
//        HashMap<String, Location> locations = processLocations();
//        Player player = new Player("Ethan Rutherford", "Known for expertise in ancient artifacts.");
//
//        processItems(locations, player);
//
//        System.out.println("");
//        System.out.printf("%s has %s\n", player.getName(), player.getInventory());
//        System.out.println("");
//        System.out.printf("%s has %s", locations.get("Cave").getName(), locations.get("Cave").getItems());
//        System.out.println("");
//        System.out.printf("%s has %s",locations.get("Jungle").getName(), locations.get("Jungle").getItems());
//        System.out.println("");
//        System.out.printf("%s has %s", locations.get("Waterfall").getName(), locations.get("Waterfall").getItems());
//        System.out.println("");
//        System.out.printf("%s has %s", locations.get("Mystical Grove").getName(), locations.get("Mystical Grove").getItems());
//    }
//}