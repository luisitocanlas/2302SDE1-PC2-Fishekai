package com.fishekai.engine;

import com.fishekai.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.fishekai.engine.Mapa.*;
import static com.fishekai.utilities.Console.blankLines;

class MapaTest {

    // fields
    Map<String, Location> locations;
    Location current_location;

    @BeforeEach
    void setUp() {
        locations = DataLoader.processLocations();
    }

    @Test
    void dynamicMap_showVisitedLocations() {
        showDynamicMap();
    }

    @Test
    void locationCheck_tracksVisitedLocations() {
        current_location = locations.get("Beach");
        locationCheck(current_location);
        System.out.printf("Current Location: %s\n", current_location.getName());
        System.out.printf("Visited Locations: %s\n", visitedLocations);
        blankLines(1);

        current_location = locations.get("Jungle");
        locationCheck(current_location);
        System.out.printf("Current Location: %s\n", current_location.getName());
        System.out.printf("Visited Locations: %s\n", visitedLocations);
        blankLines(1);

        current_location = locations.get("Beach");
        locationCheck(current_location);
        System.out.printf("Current Location: %s\n", current_location.getName());
        System.out.printf("Visited Locations: %s\n", visitedLocations);
    }

    @Test
    void showMap_showsCurrentLocation() {
        current_location = locations.get("Beach");
        showStaticMap(locations, current_location);
        blankLines(1);
        current_location = locations.get("Mystical Grove");
        showStaticMap(locations, current_location);
    }
}