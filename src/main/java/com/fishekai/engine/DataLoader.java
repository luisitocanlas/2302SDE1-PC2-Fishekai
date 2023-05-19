package com.fishekai.engine;

import com.fishekai.objects.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

    private static List<Location> listLocations(String locationsPath) {
        Gson gson = new Gson();
        List<Location> locations = null;

        try (FileReader fileReader = new FileReader(locationsPath)) {
            Type locationType = new TypeToken<List<Location>>() {}.getType();
            locations = gson.fromJson(fileReader, locationType);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return locations;
    }

    private static HashMap<String, Location> mapLocations(List<Location> locations) {
        HashMap<String, Location> mappedLocations = new HashMap<>();

        for (Location location : locations) {
            mappedLocations.put(location.getName(), location);
        }
        return mappedLocations;
    }

    public static HashMap<String, Location> processLocations(String locationsPath) {
        return mapLocations(listLocations(locationsPath));
    }

    public static String formatName(String input) {
        return input.trim().toLowerCase().replace(" ", "_");
    }

    // for internal testing
    public static void main(String[] args) {
        String locationsPath = "json/locations.json";

        HashMap<String, Location> locations = DataLoader.processLocations(locationsPath);

        Location current_location = locations.get("Beach");
        System.out.println(current_location.getName());
        System.out.println(current_location.getDirections());
        System.out.println(current_location.getDirections().get("east"));

//        for (String key : locations.keySet()) {
//            System.out.printf("Key: %s\n", key);
//            System.out.printf("Directions: %s\n", locations.get(key).getDirections());
//            System.out.println("");
//        }
    }
}