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

import static com.fishekai.engine.Introduction.formatText;
import static com.fishekai.engine.Mapa.locationCheck;
import static com.fishekai.engine.Mapa.showStaticMap;
import static com.fishekai.utilities.Console.*;

public class Fishekai implements SplashApp {
    // constants
    private static final long PAUSE_VALUE = 1_500;
    private static final int LINE_WIDTH = 120;

    // fields
    private boolean isGameOver = false;
    public static int moveCounter;
    private Map<String, Location> locations; // will contain the locations loaded from JSON file
    Player player = new Player("Ethan Rutherford", "Known for expertise in ancient artifacts.");
    Sound sound = new Sound();
    VolumeControl volumeControl = new VolumeControl(sound);
    Flask flask = new Flask("Hanley's flask");
    private final int drinkCharge = -2; // the value when you drink

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
        playSE(0);

        // initialize data, crashes the jar build
        loadData();

        // initialize move counter and set to 0
        moveCounter = 0;

        // set starting point
        Location current_location = locations.get("Beach");
        playMusic(6);

        // starts the game
        while (!isGameOver) {
            // clear screen
            clear();

            // health check
            if (player.getHp() == 0) {
                areYouStillAlive();
                break;
            }

            // check for visited locations, used for showing on the map
            locationCheck(current_location);

            // show display
            Display.showStatus(player, current_location, flask);

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
                        playSE(12);
                        pause(PAUSE_VALUE);
                        break;

                    case "look": // need more testing
                        lookAtItem(current_location, words[1]);
                        playSE(1);
                        pause(PAUSE_VALUE);
                        break;

                    case "drop":
                        dropItem(current_location, words[1]);
                        playSE(11);
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
                        playSE(7);
                        Display.showHelp();
                        intro.askToContinue();
                        break;

                    case "music":
                        if (words[1].equals("off")) {
                            stopMusic();

                        } else if (words[1].equals("on")) {
                            playMusic(6);

                        } else {
                            System.out.println("That is not a valid command. Try music on or music off.");
                        }
                        break;

                    case "jump":
                        jumpIntoTheVoid(current_location);
                        pause(PAUSE_VALUE);
                        break;

                    case "quit":
                        playSE(4);
                        gameOver();
                        break;

                    case "sound":
                        volumeControl(sound);
                        break;

                    case "drink":
                        if (words[1].equals("flask")) {
                            if (player.getInventory().containsKey("flask") && flask.getCharges() > 0) {
                                System.out.printf("drinkCharge be this value: %s\n", drinkCharge);
                                player.setThirst(drinkCharge);
                                flask.setCharges(flask.getCharges() - 1);
                                playSE(2);
                                System.out.println("You take a drink from the flask.");
                            } else {
                                System.out.println("You don't have any items to drink.");
                            }
                        }
                        pause(PAUSE_VALUE);
                        break;

                    case "eat":
                        String itemToEat = words[1].toLowerCase();
                        playSE(13);
                        pause(6_000);
                        if (parser.getFoodList().contains(itemToEat) && (player.getInventory().containsKey(itemToEat))) {
                            int nourishment = player.getInventory().get(itemToEat).getModifier();
                            player.setHunger(player.getHunger() - nourishment);
                            playSE(14);
                            pause(1_000);
                            player.getInventory().remove(itemToEat);
                        } else {
                            System.out.printf("You can't eat that %s", itemToEat);
                        }
                        break;

                    case "build":
                        if (player.getInventory().containsKey("parachute")
                        && player.getInventory().containsKey("stick")
                        && player.getInventory().containsKey("hook")) {
                            System.out.println("I have all the items for fishing pole");
                            Item rod = new Item("Fishing Pole", "tool", "You hold in your hands an artifact that you have created. Let's hope it catches a fish.");
                            player.getInventory().put("rod", rod);
                            player.getInventory().remove("parachute");
                            player.getInventory().remove("stick");
                            player.getInventory().remove("hook");
                        }
                        pause(PAUSE_VALUE);
                        break;

                    default:
                        invalidInput();
                        break;
                }
            } else {
                invalidInput();
            }
        }
    }

    private void areYouStillAlive() {
        if (player.getHp() == 0 && player.getThirst() == 10) {
            formatText(DataLoader.processGameCondition().get("Thirst_Toll"), LINE_WIDTH);
            blankLines(1);
            intro.askToContinue();
            gameOver();
        } else if (player.getHp() == 0 && player.getHunger() == 10) {
            formatText(DataLoader.processGameCondition().get("Starvation_Embrace"), LINE_WIDTH);
            blankLines(1);
            intro.askToContinue();
            gameOver();
        }
    }

    private void jumpIntoTheVoid(Location current_location) {
        if (current_location.getName().equals("Volcano")) {
            System.out.println("Despite you better judgement, you jumped into the Volcano's crater...");
            blankLines(1);
            playSE(8);
            formatText(DataLoader.processGameCondition().get("Volcanic_Plunge"), LINE_WIDTH);
            intro.askToContinue();
            gameOver();
        } else {
            System.out.println("Oof, my knees aren't what they used to be.");
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
                playSE(9);
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
            } else if (itemToGet.equals("water")) {
                if (player.getInventory().containsKey("flask")) {
                    flask.setCharges(5);
                    System.out.printf("You filled up the flask");
                } else {
                    player.setThirst(drinkCharge);
                    System.out.println("You drink a long pull of water. It would be nice to be able to carry some with you.");
                }
            } else {
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
            // check move counter and apply damage
            player.moveDamage(moveCounter);
            moveCounter += 1;
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

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }


    private void gameOver() {
        isGameOver = true;
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