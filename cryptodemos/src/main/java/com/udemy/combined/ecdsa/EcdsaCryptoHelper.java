package com.udemy.combined.ecdsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class EcdsaCryptoHelper {

    // private key is a large prime 256 bits number ("prime256v1")
    // public key is a Point(x,y) on the Elliptic Curve, and x and y are also 256 bits long numbers
    public static KeyPair generateKeyPair() {
        try {
            // BC stands for Bouncy Castle provider
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");

            // random numbers - 160 bit
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG"); // PRNG stands for Pseudo Random Generator

            // 256-bit prime field Weierstrass curve, also known as "secp256r1" or "P-256"
            ECGenParameterSpec params = new ECGenParameterSpec("prime256v1");

            keyPairGenerator.initialize(params);
            return keyPairGenerator.generateKeyPair();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // We use the private key for signature generation (signing the message)
    public static byte[] signMessage(PrivateKey privateKey, String message) {
        Signature signatureAlgorithm;
        byte[] output = new byte[0];

        try {
            signatureAlgorithm = Signature.getInstance("ECDSA", "BC");
            signatureAlgorithm.initSign(privateKey);
            signatureAlgorithm.update(message.getBytes());
            return signatureAlgorithm.sign();  // return the generated signature
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // public key - for verifying
    public static boolean verifySignature(PublicKey publicKey, byte[] signature, String message) {
        try {
            Signature signatureAlgorithm = Signature.getInstance("ECDSA", "BC");
            signatureAlgorithm.initVerify(publicKey);
            signatureAlgorithm.update(message.getBytes());
            return signatureAlgorithm.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
