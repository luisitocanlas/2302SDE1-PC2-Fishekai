package com.fishekai.game;

import com.fishekai.engine.Fishekai;

public class Main {

    public static void main(String[] args) {
        Fishekai game = new Fishekai(); // create instance of the game
//        game.welcome("images/fishekai_welcome.jpg"); // This only works when you build and run it like how we did with Jay's projects

        // show title here
        System.out.println("Replace me with the title.\n\n");

        // insert prompt here
        // if New Game, start the game
        game.start(); // starts the game

        // if Quit, ends the game

    }
}

