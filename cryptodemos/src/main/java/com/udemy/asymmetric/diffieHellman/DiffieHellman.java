package com.udemy.asymmetric.diffieHellman;

import java.math.BigInteger;
import java.util.Random;

public class DiffieHellman {

    // SecureRandom class instead
    private Random random;

    public DiffieHellman() {
        this.random = new Random();
    }

    // we want to make sure that both sender Alice and receiver Bob
    // will use the same private key (AES)
    public void generatePrivateKeys(BigInteger n, BigInteger g) {
        // THESE VARIABLES ARE PRIVATE
        //random secret number x for ALice where x<n-1
        int xAlice = random.nextInt(0, n.intValue() - 2) + 1;
        BigInteger x = new BigInteger(Integer.toString(xAlice));

        //random secret number y for Bob where y<n-1
        int yBob = random.nextInt(0, n.intValue() - 2) + 1;
        BigInteger y = new BigInteger(Integer.toString(yBob));

        // THESE VARIABLES ARE PUBLIC
        // calculate g^x mod n which is Alice's k1=kAlice
        BigInteger kAlice = g.modPow(x, n);

        // calculate g^y mod n which is Bob's k2=kBob
        BigInteger kBob = g.modPow(y, n);

        // THESE ARE AGAIN PRIVATE
        // they can calculate the same private key to use in further cryptographic algorithms
        // Alice private key
        BigInteger key1 = kBob.modPow(x, n);

        // Bob private key
        BigInteger key2 = kAlice.modPow(y, n);

        // if an attacker wants to get x and y (the private variables), then this is
        // the discrete logarithm problem - exponentially slow process
        System.out.println("Alice's private key: " + key1.intValue());
        System.out.println("Bob's private key: " + key2.intValue());
    }
}
