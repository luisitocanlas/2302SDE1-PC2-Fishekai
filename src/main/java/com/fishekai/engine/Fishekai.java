package com.fishekai.engine;

import com.fishekai.objects.Location;
import com.fishekai.objects.Player;
import com.fishekai.utilities.Prompter;
import com.fishekai.utilities.SplashApp;

import java.util.HashMap;
import java.util.Scanner;

import static com.fishekai.utilities.Console.clear;
import static com.fishekai.utilities.Console.pause;

public class Fishekai implements SplashApp {
    // constants

    // fields
    private boolean isGameOver = false;

    // instances
    private final Introduction intro = new Introduction();
    private final Prompter prompter = new Prompter(new Scanner(System.in));

    Location beach = new Location("Beach");
    Location north_beach = new Location("North Beach");

    Player player = new Player("Ethan Rutherford", "Known for expertise in ancient artifacts.");


    // methods
    public void start() {
        // show title here
        Display.showTitle();

        // ask user for input and store it
        String input = prompter.prompt("Would you like to play a new game?\n><(((º> ",
                "Yes|yes|Y|y|No|no|N|n",
                "That is not a valid input\n");

        // if New Game
        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            begin();
        }

        // if Quit
        else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            prompter.prompt("Are you sure?\n><(((º> ",
                    "Yes|yes|Y|y",
                    "This is not a valid option!\n");
        }
    }

    private void begin() {
        // show the intro
        intro.showIntro();

        // clear the screen
        clear();

        // initialize data
        loadData();

        // starting point
        Location current_location = beach;

        // for testing
//        Display.showStatus(player, current_location);
//        current_location.setHasBeenHere(true);
//        Display.showStatus(player, current_location);

        while (!isGameOver) {
            // clear screen
            clear();

            // show display
            Display.showStatus(player, current_location);

            // ask user for input
            String input = prompter.prompt("What would you like to do?\n><(((º> ");

            // give the input to the parser and then save the output of the parser
            //String[] words = UserInputParser.scan(input);
            // process input
            String[] words = input.split(" ");
            if (words.length > 0) {
                String verb = words[0].toLowerCase();

                switch (verb) { // change this to if statements
                    case "go":
                        if (words.length > 1) {
                            current_location.setHasBeenHere(true);
                            String direction = words[1].toLowerCase();
                            current_location = current_location.getDirections().get(direction);
                            pause(1_500);
                        } else {
                            System.out.println("Please specify a direction");
                        }
                        break;

                    case "help":
                        clear();
                        Display.showHelp();
                        // insert prompt here to tell the player to press any key to continue
                        intro.askToContinue();
                        break;

                    case "quit":
                        isGameOver = true;
                        gameOver();
                        break;

                    default:
                        System.out.println("I don't understand. Type help for a list of commands.");
                        pause(1_500);
                        break;
                }
            }
            else {
                System.out.println("I don't understand. Type help for a list of commands.");
                pause(1_500);
            }

        }
    }

    private void gameOver() {
        clear();
        System.out.println("Thank you for playing!");
        pause(2_000);
    }

    // load the data
    private void loadData() {
        HashMap<String, Location> beach_dir = new HashMap<>();
        HashMap<String, String> beach_des = new HashMap<>();

        beach_dir.put("north", north_beach);
        beach_des.put("before", "As you awaken from a disorienting haze, your surroundings slowly come into focus. You find yourself standing upon a pristine beach, the rhythmic melody of lapping waves punctuating the air. The shimmering sand beneath your feet tells tales of countless tides, their whispers blending with the distant calls of seagulls. Surrounded by a picturesque landscape, you instinctively clutch the amulet that brought you here, its mysterious power revealing a path you never anticipated. The beach, now your newfound sanctuary, holds the promise of survival and the key to unlocking the island's enigmatic secrets. With resolve in your heart, you take your first step into the untamed wilderness, eager to confront the challenges that lie ahead.");
        beach_des.put("after", "Returning to the familiar embrace of the beach, you find solace in its timeless beauty. The sand greets your every step, each grain a memory etched into the tapestry of your journey. The crashing waves provide a constant companion, whispering tales of resilience and perseverance. Seashells, weathered by the passage of time, reveal the island's secrets in their delicate patterns. With every return to this haven, you find yourself more attuned to the ebb and flow of the island's rhythm, ever closer to unraveling its mysteries.");


        HashMap<String, Location> nBeach_dir = new HashMap<>();
        HashMap<String, String> nBeach_des = new HashMap<>();
        nBeach_dir.put("south", beach);
        nBeach_des.put("before", "You step onto the pristine sands of the North Beach, greeted by the rhythmic melody of crashing waves and the salty tang of the sea. The expansive shoreline stretches as far as the eye can see, adorned with delicate seashells and occasional pieces of driftwood. Crystal-clear waters gently lap at the shore, inviting you to dip your toes into their refreshing embrace. Seagulls glide overhead, their calls carried by the ocean breeze. The North Beach holds the promise of respite and the allure of untold treasures washed ashore. It's a sanctuary where land meets sea, offering a temporary escape from the challenges that lie ahead.");
        nBeach_des.put("after", "You return to the familiar embrace of the North Beach, where the ebb and flow of the tides echo the rhythm of your journey. The soft sands welcome your footsteps, imprinted with the memories of your previous explorations. Waves crash against the shore with a soothing cadence, as if whispering tales of distant lands. Seashells dot the shoreline like precious jewels, remnants of a hidden world beneath the waves. The North Beach has become a place of solace and reflection, where you can find solace amidst the vastness of the ocean and gather your thoughts before delving back into the challenges that await. Let the symphony of the sea guide you on your continued adventure.");

        beach.setDirections(beach_dir);
        beach.setDescriptions(beach_des);

        north_beach.setDirections(nBeach_dir);
        north_beach.setDescriptions(nBeach_des);
    }

}