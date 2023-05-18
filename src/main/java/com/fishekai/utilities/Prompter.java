package com.fishekai.utilities;

import java.util.Scanner;

public class Prompter {
    private final Scanner scanner;

    public Prompter(Scanner var1) {
        this.scanner = var1;
    }

    public String info(String var1) {
        System.out.println(var1);
        return var1;
    }

    public String prompt(String var1) {
        System.out.print(var1);
        return this.scanner.nextLine();
    }

    public String prompt(String var1, String var2, String var3) {
        String var4 = null;
        boolean var5 = false;

        while(!var5) {
            System.out.print(var1);
            var4 = this.scanner.nextLine();
            var5 = var4.matches(var2);
            if (var5) {
                break;
            }

            System.out.print(var3);
        }

        return var4;
    }
}