package com.fishekai.game;

import com.fishekai.engine.Fishekai;

public class Main {

    public static void main(String[] args) {
        Fishekai game = new Fishekai(); // create instance of the game
//        game.welcome("images/fishekai_welcome.jpg"); // This only works when you build and run it like how we did with Jay's projects
        game.start(); // starts the game
    }
}

