package com.fishekai.engine;

import com.fishekai.models.Fish;
import com.fishekai.models.Location;
import com.fishekai.models.Player;
import com.fishekai.utilities.AudioManager;
import com.fishekai.utilities.Prompter;

import java.util.*;

import static com.fishekai.utilities.Console.*;

public class FishingMechanic {
    private static final int PAUSE_VALUE = 1_500;

    private List<String> fishDistance = new ArrayList<>();

    private final Prompter prompter = new Prompter(new Scanner(System.in));

    public void startFishing(Player player, Location current_location, AudioManager audioManager, VolumeControl volumeControl) {
        // clear the screen
        clear();

        // reset display
        resetFishDistance();

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
        boolean islineTight = false;
        int pullCount = 0;

        // start the BATTLE!!!
        while (fishBattle) {
            blankLines(2);
            System.out.println(fishDistance);
            System.out.printf("Is the line tight? %s\n", islineTight);
            System.out.printf("Fish distance: %s\n", pullCount);

            String move = prompter.prompt("[Pull] or [Release] the line?\n><(((º> ").trim().strip();

            if (islineTight) {
                if (pullCount <= -3) {
                    System.out.println("The fish escapes. Better luck next time!");
                    fishBattle = false;
                }
                else if (move.equalsIgnoreCase("pull")) {
                    System.out.println("The line is tight! You pull anyway and lose some progress.");
                    pullCount -= 3;
                    fishDistance.remove(fishDistance.size()-1);
                    fishDistance.remove(fishDistance.size()-1);
                    fishDistance.remove(fishDistance.size()-1);
                    fishDistance.add(1," ");
                    fishDistance.add(1," ");
                    fishDistance.add(1," ");
                    audioManager.randomPull();
                } else if (move.equalsIgnoreCase("release")) {
                    System.out.println("You release the line, giving the fish some slack.");
                    islineTight = false;
                    audioManager.randomReel();
                } else {
                    System.out.println("Invalid move. Choose either [Pull] or [Release].");
                }
            } else {
                if (move.equalsIgnoreCase("pull")) {
                    int success = random.nextInt(5); // Random number will handle success rate of pulling a fish
                    audioManager.randomPull();
                    if (success >= 1) {
                        System.out.println("You pull the line and feel a strong resistance. You're making progress!");
                        pullCount++;
                        fishDistance.remove(1);
                        fishDistance.add(" ");

                        if (pullCount >= 3) {
                            System.out.println("After a few more strong pulls, you successfully catch the " + fish.getName() + "!");
                            player.getInventory().put(fish.getName(), fish);
                            fishBattle = false;
                        }
                    } else {
                        System.out.println("You pull the line, but the fish slips away. Keep trying!");
                        pullCount--;
                        fishDistance.remove(fishDistance.size()-1);
                        fishDistance.add(1," ");

                        if (pullCount <= -3) {
                            System.out.println("The fish escapes. Better luck next time!");
                            fishBattle = false;
                        }
                    }

                    // Randomly determine if the line becomes tight
                    islineTight = random.nextBoolean();

                } else if (move.equalsIgnoreCase("release")) {
                    audioManager.randomReel();
                    System.out.println("You release the line, giving the fish some slack.");
                    islineTight = true;
                } else {
                    System.out.println("Invalid move. Choose either [Pull] or [Release].");
                }
            }
            pause(PAUSE_VALUE);
        }

    }

    private void resetFishDistance(){
        fishDistance.clear();

        fishDistance.add("o/");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add("><(((º>");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add(" ");
    }
}