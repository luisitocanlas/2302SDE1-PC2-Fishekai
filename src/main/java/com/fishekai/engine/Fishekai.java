package com.fishekai.engine;

import com.fishekai.objects.Item;
import com.fishekai.objects.Location;
import com.fishekai.objects.Player;
import com.fishekai.utilities.Prompter;
import com.fishekai.utilities.SplashApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.fishekai.utilities.Console.clear;
import static com.fishekai.utilities.Console.pause;

public class Fishekai implements SplashApp {
    // constants
    private static final long PAUSE_VALUE = 1_500;

    // fields
    private boolean isGameOver = false;
    private Map<String, Location> locations; // will contain the locations loaded from JSON file
    Player player = new Player("Ethan Rutherford", "Known for expertise in ancient artifacts.");

    Sound sound = new Sound();

    // instances
    private final Introduction intro = new Introduction();
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final UserInputParser parser = new UserInputParser();


    // methods
    public void start() {
        // show title here
        Display.showTitle();

        // ask user for input and store it
        String input = prompter.prompt("Would you like to play a new game? [Y]es or [N]o.\n><(((º> ",
                "Yes|yes|Y|y|No|no|N|n",
                "That is not a valid input\n");

        // if New Game, go to begin()
        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            begin();
        }

        // if Quit, terminates the game
        else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            prompter.prompt("Are you sure?\n><(((º> ",
                    "Yes|yes|Y|y",
                    "This is not a valid option!\n");
        }
    }

    private void begin() {
        // show the intro
        intro.showIntro();

        // initialize data, crashes the jar build
        loadData();

        // set starting point
        Location current_location = locations.get("Beach");

        // starts the game
        while (!isGameOver) {
            // clear screen
            clear();

            // show display
            Display.showStatus(player, current_location);

            // ask user for input
            String input = prompter.prompt("What would you like to do?\n><(((º> ").trim().strip();

            // give the input to the parser and then save the output of the parser
            String[] words = parser.scan(input);

            // process input
//            String[] words = input.split("\\s+");
            if (words.length > 0) {
                String verb = words[0].toLowerCase();

                switch (verb) { // change this to if statements
                    case "go":
                        String direction = words[1].toLowerCase();
                        if (parser.getDirectionsList().contains(direction) && current_location.getDirections().containsKey(direction)) {
                            current_location.setHasBeenHere(true);
                            current_location = locations.get(current_location.getDirections().get(direction));
                        } else {
                            System.out.println("Please specify a valid direction.");
                        }
                        pause(PAUSE_VALUE);
                        break;

                    case "look": // need more testing
                        String itemToLook = words[1].toLowerCase();
                        if (parser.getItemList().contains(itemToLook) || parser.getFoodList().contains(itemToLook)) {
                            if (player.getInventory().containsKey(itemToLook)) {
                                System.out.println("The " + player.getInventory().get(itemToLook).getName() + " looks like " + player.getInventory().get(itemToLook).getDescription());
                            } else if (current_location.getItems().containsKey(itemToLook)) {
                                System.out.println("The " + current_location.getItems().get(itemToLook).getName() + " looks like " + current_location.getItems().get(itemToLook).getDescription());
                            } else {
                                System.out.println("There is no " + itemToLook + " here.");
                            }
                        } else {
                            // Handle the case when the user didn't specify an item to look at
                            System.out.println("Please specify an item to look at.");
                        }
                        pause(PAUSE_VALUE);
                        break;

                    case "drop":
                        String itemToDrop = words[1].toLowerCase();
                        if (parser.getItemList().contains(itemToDrop) || parser.getFoodList().contains(itemToDrop)) {
                            if (player.getInventory().containsKey(itemToDrop)) {
                                if (current_location.getItems() == null) {
                                    Map<String, Item> inventoryMap = new HashMap<>();
                                    inventoryMap.put(itemToDrop, player.getInventory().get(itemToDrop));
                                    player.getInventory().remove(itemToDrop);
                                    current_location.setItems(inventoryMap);
                                    System.out.println("You dropped the " + itemToDrop + ".");
                                } else {
                                    current_location.getItems().put(itemToDrop, player.getInventory().get(itemToDrop));
                                    player.getInventory().remove(itemToDrop);
                                    System.out.println("You dropped the " + itemToDrop + ".");
                                }
                            } else {
                                System.out.println("You don't have a " + itemToDrop + "in your inventory.");
                            }
                        } else {
                            System.out.println("Please specify an item to drop.");
                        }
                        pause(PAUSE_VALUE);
                        break;

                    case "get":
                        String itemToGet = words[1].toLowerCase();
                        if (parser.getItemList().contains(itemToGet) || parser.getFoodList().contains(itemToGet)) {
                            if (!player.getInventory().containsKey(itemToGet)) {
                                player.getInventory().put(itemToGet, current_location.getItems().get(itemToGet));
                                playSE(3);
                                current_location.getItems().remove(itemToGet);
                                System.out.println("You got the " + itemToGet + ".");
                            } else if (player.getInventory().containsKey(itemToGet)) {
                                System.out.println("You have the " + itemToGet + ".");
                            } else {
                                System.out.println("There is no " + itemToGet + " here.");
                            }
                        }
                        pause(PAUSE_VALUE);
                        break;

                    case "talk":
                        String npcCharacter = words[1].toLowerCase();
                        if (parser.getNpcList().contains(npcCharacter)) {
                            if (current_location.getNpc().containsKey(npcCharacter)) {
                                current_location.getNpc().get(npcCharacter).getRandomQuotes();
                            } else {
                                System.out.println(current_location.getNpc().containsKey(npcCharacter));
                                System.out.println("There is no " + npcCharacter + "here.");
                            }
                        }
                        pause(PAUSE_VALUE);
                        break;

                    case "map":
                        clear();
//                        playSE(5);
                        Display.showMap(locations, current_location);
                        intro.askToContinue();
                        break;

                    case "help":
                        clear();
                        Display.showHelp();
                        intro.askToContinue();
                        break;

                    case "quit":
                        isGameOver = true;
                        playSE(4);
                        gameOver();
                        break;

                    default:
                        System.out.println("I don't understand. Type help for a list of commands.");
                        pause(PAUSE_VALUE);
                        break;
                }
            } else {
                System.out.println("I don't understand. Type help for a list of commands.");
                pause(PAUSE_VALUE);
            }
        }
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    private void gameOver() {
        clear();
        System.out.println("Thank you for playing!");
        pause(PAUSE_VALUE);
    }

    // load the data
    private void loadData() {
        locations = DataLoader.processLocations(); // load the locations
        DataLoader.processItems(player, locations); // load items and place in locations
        DataLoader.processFishes(locations); // load fishes and place in locations
        DataLoader.processNpc(locations); // load NPCs and place in locations
    }

}