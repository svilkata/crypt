package com.udemy.asymmetric.modularFundamentals.integerPrimeFactorization;

public class IntegerFactorization {

    public void factor(int num) {
        int limit = (int) Math.floor(Math.sqrt(num));

        for (int i = 2; i <= limit ; ++i) {
            if (num % i == 0) {
                System.out.println("Factors: [" + i + "," + num / i + "]");
            }
        }
    }
}
