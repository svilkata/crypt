package com.udemy.asymmetric.modularFundamentals.findingPrimeNumbers;

import java.math.BigInteger;
import java.util.Random;

public class FermatPrimeTest {
    private Random random;

    public FermatPrimeTest() {
        this.random = new Random();
    }

    public boolean isPrime(BigInteger number, int k) {
        if (number.intValue() <= 1) {
            return false;
        }

        if (number.intValue() == 2) {
            return true;
        }

        // Fermat's prime test is probabilistic: the more k iterations we make
        // the higher the probability that the given number is not composite (so it is a prime)
        for (int i = 0; i < k; ++i) {
            // generate a random number [2, N-1]
            BigInteger a = BigInteger.valueOf(random.nextInt(number.intValue() - 2) + 2);

            // if a^(n-1) % n != 1 then n is NOT a prime number
            if (!a.modPow(number.subtract(BigInteger.ONE), number).equals(BigInteger.ONE)) {
                return false;
            }
        }

        // after k iterations, we can come to the conclusion that number is a prime
        return true;
    }
}
