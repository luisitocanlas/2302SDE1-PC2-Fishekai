package com.fishekai.utilities;

import java.io.IOException;

public class Console {
    private static final String os = System.getProperty("os.name").toLowerCase();

    private Console() {
    }

    public static void blankLines(int var0) {
        for(int var1 = 0; var1 < var0; ++var1) {
            System.out.println();
        }

    }

    public static void clear() {
        ProcessBuilder var0 = os.contains("windows") ? new ProcessBuilder(new String[]{"cmd", "/c", "cls"}) : new ProcessBuilder(new String[]{"clear"});

        try {
            var0.inheritIO().start().waitFor();
        } catch (InterruptedException var2) {
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public static void pause(long var0) {
        try {
            Thread.sleep(var0);
        } catch (InterruptedException var3) {
        }

    }
}