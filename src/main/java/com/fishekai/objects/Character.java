package com.fishekai.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Character {

    // fields
    private final String name;
    private String description;
    private int hp = 10;    // range 0 ~ 10
    private int hunger = 0; // range 0 ~ 10
    private int thirst = 0; // range 0 ~ 10
    private final HashMap<String, Object> inventory = new HashMap<>();

    // constructors
    public Character(String name) {
        this.name = name;
    }

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // methods


    public void removeFromInventory(Object item) {
        inventory.remove(item);
    }

    // accessors
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public HashMap<String, Object> getInventory() {
        return inventory;
    }
}
