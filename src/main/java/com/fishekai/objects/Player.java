package com.fishekai.objects;

public class Player extends Character {

    // constructors
    public Player(String name) {
        super(name);
//        this.addToInventory("Amulet"); // initial item in player
    }

    public Player(String name, String description) {
        super(name, description);
//        this.addToInventory("Amulet"); // initial item in player
    }

    // methods

    // for internal testing
//    public static void main(String[] args) {
//        Player player = new Player("Ethan Rutherford", "Known for expertise in ancient artifacts.");
//
//        System.out.println(player.getName());
//        System.out.println(player.getDescription());
//
//        // changing HP levels
//        System.out.printf("HP: %s\n",player.getHp());
//        player.setHp(player.getHp() - 1);
//        System.out.printf("HP: %s\n",player.getHp());
//
//        // changing hunger levels
//        System.out.printf("Hunger: %s\n",player.getHunger());
//        player.setHunger(player.getHunger() + 2);
//        System.out.printf("Hunger: %s\n",player.getHunger());
//
//        // changing thirst levels
//        System.out.printf("Thirst: %s\n",player.getThirst());
//        player.setThirst(player.getThirst() + 4);
//        System.out.printf("Thirst: %s\n",player.getThirst());
//
//        // accessing inventory
//        System.out.printf("Inventory: %s\n",player.getInventory());
////        player.addToInventory("Binoculars");
//        System.out.printf("Inventory: %s\n",player.getInventory());
//        player.removeFromInventory("Binoculars");
//        System.out.printf("Inventory: %s\n",player.getInventory());
//    }
}
