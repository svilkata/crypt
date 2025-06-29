package com.udemy.hashing.sha256;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    // SHA256 uses 256 bits ( / 4 = 64 hexa characters) as output
    // This is the algorithm that BitCoin miners are generating in order to find the valid hash for the given block in the blockchain
    public String digest(String message) {
        MessageDigest messageDigest = null;
        byte[] messageDigestByteArray = null;
        String hexaHash = "";

        try {
            messageDigest = MessageDigest.getInstance("SHA256");

            // we need the byte array when dealing with cryptography
            messageDigestByteArray = messageDigest.digest(message.getBytes());

            // 1 hexa character = 4 bits
            // we convert the message hash into hexadecmial (16 characters)
            // problem is that it may omit leading zeros
            hexaHash = new BigInteger(1, messageDigestByteArray).toString(16);

            while (hexaHash.length() < 64) {
                hexaHash = "0" + hexaHash;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexaHash;
    }

}
