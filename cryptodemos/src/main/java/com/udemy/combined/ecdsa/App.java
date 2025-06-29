package com.udemy.combined.ecdsa;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.Security;
import java.util.Base64;

// Elliptic Curve Digital Signature Algorithm
public class App {
    public static void main(String[] args) {

        // define the provider - BC
        Security.addProvider(new BouncyCastleProvider());

        KeyPair keyPairAlice = EcdsaCryptoHelper.generateKeyPair();
        KeyPair keyPairSomebodyStopMe = EcdsaCryptoHelper.generateKeyPair();

        System.out.println("Alice EC Public Key: " + keyPairAlice.getPublic());
        System.out.println("Alice EC Public Key as String: " + Base64.getEncoder().encodeToString(keyPairAlice.getPublic().getEncoded()));
        System.out.println("----------");
        System.out.println("Alice EC Private Key as String: " + Base64.getEncoder().encodeToString(keyPairAlice.getPrivate().getEncoded()));
        System.out.println("----------");

        String message = "This is a test message";
        byte[] signatureByteArray = EcdsaCryptoHelper.signMessage(keyPairAlice.getPrivate(), message);
        System.out.println("Alice generated signature as String: " + Base64.getEncoder().encodeToString(signatureByteArray));

        System.out.println("Somebody verified the Alice's signature with result: " + EcdsaCryptoHelper.verifySignature(keyPairAlice.getPublic(), signatureByteArray, message));
        System.out.println("Somebody verified the Alice's signature with result: " + EcdsaCryptoHelper.verifySignature(keyPairSomebodyStopMe.getPublic(), signatureByteArray, message));
    }
}
