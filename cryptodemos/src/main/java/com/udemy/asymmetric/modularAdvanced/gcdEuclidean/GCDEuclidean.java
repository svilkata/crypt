package com.udemy.asymmetric.modularAdvanced.gcdEuclidean;


// Greatest commong divisor - Euclidean algorithm
public class GCDEuclidean {

    // we assume a<b in every iteration
    public int gcd_recursion(int a, int b) {

        // base-case when we terminate the algorithm
        if (a % b == 0) {
            return b;
        }

        // recursive method call
        return gcd_recursion(b, a % b);
    }

    // we assume a<b in every iteration
    public int gcd_iteration(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
