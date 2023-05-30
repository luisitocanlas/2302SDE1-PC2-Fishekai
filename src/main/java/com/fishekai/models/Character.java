package com.fishekai.models;

import java.util.HashMap;

public class Character {
    // constants
    private static final int MAX_HP = 10;
    private static final int MIN_HP = 0;
    private static final int MAX_THIRST = 10;
    private static final int MIN_THIRST = 0;
    private static final int MAX_HUNGER = 10;
    private static final int MIN_HUNGER = 0;

    // fields
    private final String name;
    private String description;
    private int hp = 10;    // range 0 ~ 10
    private int hunger = 5; // range 0 ~ 10
    private int thirst = 5; // range 0 ~ 10
    private final HashMap<String, Item> inventory = new HashMap<>();

    // constructors
    public Character(String name) {
        this.name = name;
    }

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
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
        if (hp >= MIN_HP && hp <= MAX_HP) {
            this.hp = hp;
        }
        else if (hp < MIN_HP) {
            this.hp = MIN_HP;
        }
        else if (hp > MAX_HP){
            this.hp = MAX_HP;
        }
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        if (hunger >= MIN_HUNGER && hunger <= MAX_HUNGER) {
            this.hunger = hunger;
        }
        else if (hunger < MIN_HUNGER) {
            this.hunger = MIN_HUNGER;
        }
        else if (hunger > MAX_HUNGER) {
            this.hunger = MAX_HUNGER;
        }
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        if (thirst >= MIN_THIRST && thirst <= MAX_THIRST) {
            this.thirst = thirst;
        }
        else if (thirst < MIN_THIRST) {
            this.thirst = MIN_THIRST;
        }
        else if (thirst > MAX_THIRST) {
            this.thirst = MAX_THIRST;
        }
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

}
