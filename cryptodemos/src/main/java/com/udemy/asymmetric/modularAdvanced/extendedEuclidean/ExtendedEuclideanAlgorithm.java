package com.udemy.asymmetric.modularAdvanced.extendedEuclidean;

public class ExtendedEuclideanAlgorithm {

    // in this implementation a<b
    public int[] egcd(int a, int b) {

        // base-case
        // gcd(0,b)=b and a*x+b*y=b - so x=0 and y=1
        if(a == 0) {
            return new int[] { b, 0, 1 };
        }

        // so we use the Euclidean algorithm for gcd()
        // b % a is always the smaller number than a - and 'a' is the smaller integer
        // always in this implementation
        int[] values = egcd(b % a, a);

        // and we update the parameters for x, y accordingly
        int gcd = values[0];
        int x1 = values[1];
        int y1 = values[2];

        int x = y1 - (b / a) * x1;
        int y = x1;

        return new int[] {gcd, x, y};
    }
}
