package com.udemy.asymmetric.modularAdvanced.modularInverse;


public class App {
    public static void main(String[] args) {

        ModularInverse modularInverse = new ModularInverse();
        System.out.println(modularInverse.calculateBruteForce(12, 31));  // (12 * 13) % 31 = 1

    }
}
