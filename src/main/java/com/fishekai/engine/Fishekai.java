package com.fishekai.engine;

import com.fishekai.models.Flask;
import com.fishekai.models.Item;
import com.fishekai.models.Location;
import com.fishekai.models.Player;
import com.fishekai.utilities.Prompter;
import com.fishekai.utilities.SplashApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.fishekai.engine.Mapa.locationCheck;
import static com.fishekai.engine.Mapa.showStaticMap;
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
    VolumeControl volumeControl = new VolumeControl(sound);
    Flask flask = new Flask("Hanley's flask");
    private int drinkCharge = player.setThirst(player.getThirst() - 2);

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
        playMusic(6);

        // starts the game
        while (!isGameOver) {
            // clear screen
            clear();

            // check for visited locations, used for showing on the map
            locationCheck(current_location);

            // show display
            Display.showStatus(player, current_location);

            // ask user for input
            String input = prompter.prompt("What would you like to do?\n><(((º> ").trim().strip();

            // give the input to the parser and then save the output of the parser
            String[] words = parser.scan(input);

            // process input
            if (words.length > 0) {
                String verb = words[0].toLowerCase();

                switch (verb) {
                    case "go":
                        current_location = changeLocation(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "look": // need more testing
                        lookAtItem(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "drop":
                        dropItem(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "get":
                        getItem(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "talk":
                        talkWithNpc(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "map":
                        clear();
                        playSE(5);
                        showStaticMap(locations, current_location);
                        intro.askToContinue();
                        break;

                    case "help":
                        clear();
                        Display.showHelp();
                        intro.askToContinue();
                        break;
                    case "music":
                        if (words[1].equals("off")) {
                            stopMusic(6);
                        } else if (words[1].equals("on")) {
                            playMusic(6);
                        } else {
                            System.out.println("That is not a valid command. Try music on or music off.");
                        }
                        break;

                    case "quit":
                        isGameOver = true;
                        playSE(4);
                        gameOver();
                        break;

                    case "sound":
                        volumeControl(sound);
                        break;

                    case "drink":
                        if (words[1].equals("flask")) {
                            if (player.getInventory().containsKey("flask")) {
                                player.setThirst(drinkCharge);
                                flask.setCharges(flask.getCharges() - 1);
                                System.out.println("You take a drink from the flask, and wish it was rum.");
                            } else {
                                System.out.println("You don't have any items to drink.");
                            }
                        }break;


                    default:
                        invalidInput();
                        break;
                }
            } else {
                invalidInput();
            }
        }
    }

    private void volumeControl(Sound sound) {
        new VolumeControl(sound);
    }

    private void invalidInput() {
        System.out.println("I don't understand. Type help for a list of commands.");
        pause(PAUSE_VALUE);
    }

    private void talkWithNpc(Location current_location, String word) {
        String npcCharacter = word.toLowerCase();
        if (parser.getNpcList().contains(npcCharacter)) {
            if (current_location.getNpc().containsKey(npcCharacter)) {
                current_location.getNpc().get(npcCharacter).getRandomQuotes();
            } else {
                System.out.println(current_location.getNpc().containsKey(npcCharacter));
                System.out.println("There is no " + npcCharacter + "here.");
            }
        }
    }

    private void getItem(Location current_location, String word) {
        String itemToGet = word.toLowerCase();
        if (parser.getItemList().contains(itemToGet) || parser.getFoodList().contains(itemToGet)) {
            if (!player.getInventory().containsKey(itemToGet) && !itemToGet.equals("water")) {
                player.getInventory().put(itemToGet, current_location.getItems().get(itemToGet));
                playSE(3);
                current_location.getItems().remove(itemToGet);
                System.out.println("You got the " + itemToGet + ".");
            } else if (player.getInventory().containsKey(itemToGet)) {
                System.out.println("You have the " + itemToGet + ".");
            } else if (itemToGet.equals("water")){
                    if (player.getInventory().containsKey("flask")){
                        int charges = 5;
                        flask.setCharges(charges);
                        System.out.printf("You filled up the flask");
                } else {
                        player.setThirst(drinkCharge);
                        System.out.println("You drink a long pull of water. It would be nice to be able to carry some with you.");
                    }
            }
            else {
                System.out.println("There is no " + itemToGet + " here.");
            }
        }
    }

    private void dropItem(Location current_location, String word) {
        String itemToDrop = word.toLowerCase();
        if (parser.getItemList().contains(itemToDrop) || parser.getFoodList().contains(itemToDrop)) {
            if (player.getInventory().containsKey(itemToDrop)) {
                if (current_location.getItems() == null) {
                    Map<String, Item> inventoryMap = new HashMap<>();
                    inventoryMap.put(itemToDrop, player.getInventory().get(itemToDrop));
                    player.getInventory().remove(itemToDrop);
                    current_location.setItems(inventoryMap);
                } else {
                    current_location.getItems().put(itemToDrop, player.getInventory().get(itemToDrop));
                    player.getInventory().remove(itemToDrop);
                }
                System.out.println("You dropped the " + itemToDrop + ".");
            } else {
                System.out.println("You don't have a " + itemToDrop + "in your inventory.");
            }
        } else {
            System.out.println("Please specify an item to drop.");
        }
    }

    private void lookAtItem(Location current_location, String word) {
        String itemToLook = word.toLowerCase();
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
    }

    private Location changeLocation(Location current_location, String word) {
        String direction = word.toLowerCase();
        if (parser.getDirectionsList().contains(direction) && current_location.getDirections().containsKey(direction)) {
            current_location.setHasBeenHere(true);
            current_location = locations.get(current_location.getDirections().get(direction));
        } else {
            System.out.println("Please specify a valid direction.");
        }
        return current_location;
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(int i) {
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
        parser.loadTextArguments(); // loads text arguments in UserInputParser
    }

}