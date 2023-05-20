package com.fishekai.objects;

public class Item {
    String name;
    String type;
    String description;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
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

}
