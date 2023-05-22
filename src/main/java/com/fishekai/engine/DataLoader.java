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
    // file paths
    private static final String LOCATIONS_PATH = "/json/locations.json";
    private static final String GAME_INFO_PATH = "/json/Game_Information.json";

    // references to be called
    public static String gameInfo;

    // reads the locations json file and stores data
    public static HashMap<String, Location> processLocations() {
        Gson gson = new Gson();

        // takes the data from json file and stores as a List<Location>
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(LOCATIONS_PATH))));
        TypeToken<List<Location>> token = new TypeToken<>(){};
        List<Location> listLocations = gson.fromJson(fileReader, token.getType());

        // takes in the List<Location> and stores as a hashmap
        HashMap<String, Location> mappedLocations = new HashMap<>();
        for (Location location : listLocations) {
            mappedLocations.put(location.getName(), location);
        }
        return mappedLocations;
    }

    public static HashMap<String, String> processGameInfo() {
        Gson gson = new Gson();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataLoader.class.getResourceAsStream(GAME_INFO_PATH))));
        TypeToken<HashMap<String, String>> token = new TypeToken<>(){};
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

    static{
        try{
            gameInfo = readResource("/images/game_info.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // for internal testing
//    public static void main(String[] args) {
//        HashMap<String, String> game_Info = processGameInfo();
//
//    }
}