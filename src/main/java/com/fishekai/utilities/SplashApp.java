package com.fishekai.utilities;

import java.awt.*;
import java.net.URL;

public interface SplashApp {
    long DEFAULT_PAUSE = 3000L;

    void start();

    default void welcome(String... var1) throws IllegalArgumentException {
        this.welcome(3000L, var1);
    }

    default void welcome(long var1, String... var3) throws IllegalArgumentException {
        SplashScreen var4 = SplashScreen.getSplashScreen();

        try {
            Thread.sleep(var1);
            String[] var5 = var3;
            int var6 = var3.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String var8 = var5[var7];
                URL var9 = new URL("file:./" + var8);
                var4.setImageURL(var9);
                Thread.sleep(var1);
            }
        } catch (Exception var13) {
            throw new IllegalArgumentException("Error initializing application", var13);
        } finally {
            var4.close();
        }

    }
}