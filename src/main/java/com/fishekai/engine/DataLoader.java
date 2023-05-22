package com.fishekai.engine;

import com.fishekai.objects.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DataLoader {
    private static final String LOCATIONS_PATH = "/json/locations.json";

    public static String introduction;

    private static List<Location> listLocations() {
        Gson gson = new Gson();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(LOCATIONS_PATH))));
        TypeToken<List<Location>> token = new TypeToken<>(){};

        return gson.fromJson(fileReader, token.getType());
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

    public static String readResource(String path) throws IOException {
        try (InputStream is = Display.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + path);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    static{
        try{
            introduction = readResource("/images/introduction.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
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