package com.fishekai.objects;

import java.util.List;
import java.util.Random;

public class NPC extends Character {

    // constructors
    public NPC(String name) {
        super(name);
    }

    public NPC(String name, String description) {
        super(name, description);
    }

    // methods
    public String quotes() {
        List<String> quotes = List.of("I require a shrubbery! Oh, wrong game. Sorry. I am the one who landed on this god-forsaken island. Did you know this island looks like a fish? I wonder what that means… Anyway, isn’t it weird that you can see me? Good luck with getting off this island.", "Hmmm… you’re talking to me again. What help could I possibly be? I’m dead!","You know, you are a persistent thorn in my side. I’m just trying to spend the remainder of my non-existence in this lovely grove. Minding my own business!","Still here? Go fishing already!");
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }

    // for internal testing
    public static void main(String[] args) {
        NPC ghost = new NPC("Hanley Druthers", "Your standard ghost haunting the place of his death and  just enjoying the Mystic Grove for its beauty.");

        System.out.println(ghost.getName());
        System.out.println(ghost.getDescription());

        System.out.println(ghost.getHp());
        ghost.setHp(ghost.getHp() - 3);
        System.out.println(ghost.getHp()); // you can kill a ghost!!!

        System.out.println(ghost.quotes());
    }
}
