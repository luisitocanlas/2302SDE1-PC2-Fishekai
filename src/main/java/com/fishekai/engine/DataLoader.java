package com.fishekai.engine;

import com.fishekai.objects.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

    private static List<Location> listLocations(String locationsPath) {
        Gson gson = new Gson();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(DataLoader.class.getResourceAsStream("/json/locations.json")));
        TypeToken<List<Location>> token = new TypeToken<>(){};
        List<Location> locations = gson.fromJson(fileReader, token.getType());

        return locations;
    }

    private static HashMap<String, Location> mapLocations(List<Location> locations) {
        HashMap<String, Location> mappedLocations = new HashMap<>();

        for (Location location : locations) {
            mappedLocations.put(location.getName(), location);
        }
        return mappedLocations;
    }

    public static HashMap<String, Location> processLocations() {
        return mapLocations(listLocations());
    }

    // for internal testing
//    public static void main(String[] args) {
//        String locationsPath = "json/locations.json";
//
//        HashMap<String, Location> locations = DataLoader.processLocations(locationsPath);
//
//        Location current_location = locations.get("Beach");
//        System.out.println(current_location.getName());
//        System.out.println(current_location.getDirections());
//        System.out.println(current_location.getDirections().get("east"));
//
////        for (String key : locations.keySet()) {
////            System.out.printf("Key: %s\n", key);
////            System.out.printf("Directions: %s\n", locations.get(key).getDirections());
////            System.out.println("");
////        }
//    }
}