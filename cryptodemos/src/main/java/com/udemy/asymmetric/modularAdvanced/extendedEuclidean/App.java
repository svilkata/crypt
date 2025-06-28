package com.udemy.asymmetric.modularAdvanced.extendedEuclidean;


import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        ExtendedEuclideanAlgorithm gcd = new ExtendedEuclideanAlgorithm();

        System.out.println(Arrays.toString(gcd.egcd(24, 12)));
        System.out.println(Arrays.toString(gcd.egcd(24, 9)));

        //relative primes = coprime   are 21 and 11
        System.out.println(Arrays.toString(gcd.egcd(21, 11)));
    }
}
