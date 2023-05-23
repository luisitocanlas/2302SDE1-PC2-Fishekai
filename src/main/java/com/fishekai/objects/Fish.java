package com.fishekai.objects;

public class Fish extends Item {

    private final boolean causesDeath;
    private final int hungerValue;
    private int catchDifficulty;

    public Fish(String name, String type, String description, boolean causesDeath, int hungerValue) {
        super(name, type, description);
        this.causesDeath = causesDeath;
        this.hungerValue = hungerValue;
    }

    public Fish(String name, String type, String description, boolean causesDeath, int hungerValue, int catchDifficulty) {
        super(name, type, description);
        this.causesDeath = causesDeath;
        this.hungerValue = hungerValue;
        this.catchDifficulty = catchDifficulty;
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
