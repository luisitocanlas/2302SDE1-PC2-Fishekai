package com.fishekai.objects;

public class Fish extends Item {
    String name;
    String type;
    String description;
    boolean isEdible;
    boolean causesDeath;
    int hungerValue;
    int catchDifficulty;

    public Fish(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public boolean isCausesDeath() {
        return causesDeath;
    }

    public int getHungerValue() {
        return hungerValue;
    }

    public int getCatchDifficulty() {
        return catchDifficulty;
    }
}
