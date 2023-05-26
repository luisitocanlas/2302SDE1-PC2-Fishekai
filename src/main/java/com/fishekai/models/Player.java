package com.fishekai.models;

import static com.fishekai.utilities.Console.pause;

public class Player extends Character {

    // constructors
    public Player(String name) {
        super(name);
    }

    public Player(String name, String description) {
        super(name, description);
    }

    // methods
    public void moveDamage(int moveCounter) {
        if (moveCounter % 2 == 0 && moveCounter > 0) { // be more thirsty every 2 moves
            this.setThirst(this.getThirst() + 1);
            System.out.println("You're getting thirstier...");
        }
        if (moveCounter % 3 == 0 && moveCounter > 0) { // be more hungry every 3 move
            this.setHunger(this.getHunger() + 1);
            System.out.println("You're getting hungrier...");
        }

        // taking damage while certain conditions are met
        if (this.getHunger() == 10) {
            this.setHp(this.getHp() - 5);
            System.out.println("Your stomach grumbles loudly, and you feel weak from hunger. Find food soon before it becomes unbearable.");
        }
        else if (this.getThirst() == 10) {
            this.setHp(this.getHp() - 4);
            System.out.println("Your throat is parched, and every swallow feels like sandpaper. Your body desperately craves water. Find a water source quickly.");
        }
        else if (this.getHunger() == 10 && this.getThirst() == 10) {
            this.setHp(-10);
            System.out.println("You are completely famished and your mouth feels dry as a desert. Your body is weak, and survival becomes a race against time. Find food and water immediately.");
        }
    }

}
