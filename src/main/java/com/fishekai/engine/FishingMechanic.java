package com.fishekai.engine;

import com.fishekai.models.Fish;
import com.fishekai.models.Location;
import com.fishekai.models.Player;
import com.fishekai.utilities.AudioManager;
import com.fishekai.utilities.Prompter;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static com.fishekai.utilities.Console.blankLines;
import static com.fishekai.utilities.Console.pause;

public class FishingMechanic {
    private static final int PAUSE_VALUE = 1_500;

    private final Player player;
    private final Location current_location;

    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final AudioManager audioManager = new AudioManager();
//    VolumeControl volumeControl = new VolumeControl(audioManager);

    public FishingMechanic(Player player, Location current_location) {
        this.player = player;
        this.current_location = current_location;
    }

    public void startFishing() {
        // randomly select a fish
        Map<String, Fish> fishMap = current_location.getFishes();
        Random random = new Random();
        String randomFishName = fishMap.keySet().stream()
                .skip(random.nextInt(fishMap.size()))
                .findFirst()
                .orElse(null);
        Fish fish = current_location.getFishes().get(randomFishName);

        System.out.println("You cast your line into the water at " + current_location.getName() + " and wait patiently...");
        pause(PAUSE_VALUE);
        blankLines(1);
        System.out.println("A " + fish.getName() + " has bitten your bait! It's time to reel it in.");

        boolean fishBattle = true;
        boolean lineTight = false;
        int pullCount = 0;

        // start the BATTLE!!!
        while (fishBattle) {
            String move = prompter.prompt("[Pull] or [Release] the line?\n><(((º> ").trim().strip();

            if (lineTight) {
                if (move.equalsIgnoreCase("pull")) {
                    System.out.println("The line is tight! You pull anyway and lose some progress.");
                    pullCount -= 3;
                    audioManager.randomPull();
                } else if (move.equalsIgnoreCase("release")) {
                    System.out.println("You release the line, giving the fish some slack.");
                    lineTight = false;
                    audioManager.randomReel();
                } else {
                    System.out.println("Invalid move. Choose either [Pull] or [Release].");
                }
            } else {
                if (move.equalsIgnoreCase("pull")) {
                    int success = random.nextInt(5); // Random number: 0, 1, 2, 3, or 4
                    audioManager.randomPull();
                    if (success < 3) {
                        System.out.println("You pull the line and feel a strong resistance. You're making progress!");
                        pullCount++;

                        if (pullCount == 3) {
                            System.out.println("After a few more strong pulls, you successfully catch the " + fish.getName() + "!");
                            player.getInventory().put(fish.getName(), fish);
                            fishBattle = false;
                        }
                    } else {
                        System.out.println("You pull the line, but the fish slips away. Keep trying!");
                        pullCount--;

                        if (pullCount == -3) {
                            System.out.println("The fish escapes. Better luck next time!");
                            fishBattle = false;
                        }
                    }

                    // Randomly determine if the line becomes tight
                    lineTight = random.nextBoolean();
                    if (lineTight) {
                        System.out.println("The line goes very tight with a lot of resistance. Be careful!");
                    }
                } else if (move.equalsIgnoreCase("release")) {
                    audioManager.randomReel();
                    System.out.println("You release the line, giving the fish some slack.");
                    lineTight = true;
                } else {
                    System.out.println("Invalid move. Choose either [Pull] or [Release].");
                }
            }
        }

    }
}