package com.udemy.asymmetric.modularAdvanced.gcdEuclidean;


public class App {
    public static void main(String[] args) {

        GCDEuclidean gcd = new GCDEuclidean();

        System.out.println(gcd.gcd_recursion(24, 12));
        System.out.println(gcd.gcd_recursion(24, 9));

        //relative primes = coprime   are 21 and 11
        System.out.println(gcd.gcd_recursion(21, 11));


        System.out.println(gcd.gcd_iteration(24, 12));
        System.out.println(gcd.gcd_iteration(24, 9));

        //relative primes = coprime   are 21 and 11
        System.out.println(gcd.gcd_iteration(21, 11));
    }
}
