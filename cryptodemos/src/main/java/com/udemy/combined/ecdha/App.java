package com.udemy.combined.ecdha;

import com.udemy.asymmetric.ecc.ECC;
import com.udemy.asymmetric.ecc.Point;

import java.math.BigDecimal;
import java.util.Random;

// Elliptic Curve Diffie-Hellman Algorithm
public class App {
    public static void main(String[] args) {
        // Let's use these values: a = 3, b = 7
        ECC ecc = new ECC(BigDecimal.valueOf(3), BigDecimal.valueOf(7)); // PUBLIC data
        Point pointP = new Point(BigDecimal.valueOf(-2), BigDecimal.ONE); // PUBLIC data

        Random random = new Random();
        // Elliptic Curve Diffie-Hellman Algorithm
        int a = random.nextInt(10000);  // generate random number for Alice - PRIVATE
        int b = random.nextInt(10000);  // generate random number for Bob - PRIVATE

        // Generate the PUBLIC part(key) of the final shared key - with the Double and Add Algorithm
        // kAlice = a * R(xR, yR)
        // kBob = b * R(xR, yR)
        Point kAlicePublicKey = ecc.doubleAndAdd(a, pointP);
        Point kBobPublicKey = ecc.doubleAndAdd(b, pointP);

        // Generating the private part (key) for both encrypting and decrypting
        Point aliceSecretKey = ecc.doubleAndAdd(a, kBobPublicKey);
        Point bobSecretKey = ecc.doubleAndAdd(b, kAlicePublicKey);

        System.out.println("Alice Secret Key: " + aliceSecretKey);
        System.out.println("Bob Secret Key: " + bobSecretKey);

    }
}
