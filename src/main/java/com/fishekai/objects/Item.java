package com.fishekai.objects;

public class Item {
    private final String name;
    private String type;
    private String description;
    private String location;
    private int modifier;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Item(String name, String type, String description, String location, int modifier) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.location = location;
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getModifier() {
        return modifier;
    }
}
