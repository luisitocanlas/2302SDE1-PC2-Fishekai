package com.fishekai.engine;

import com.fishekai.utilities.SplashApp;

public class Fishekai implements SplashApp {
    //fields
    private final Introduction intro = new Introduction();


    // methods
    public void start() {
        // show the intro
        intro.showIntro();
        // prompt to press any key to continue

    }

}