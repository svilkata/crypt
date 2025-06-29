package com.udemy.hashing.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    // MD5 uses 128 bits (= 32 hexa characters) as output
    public String digest(String message) {

        MessageDigest messageDigest = null;
        byte[] messageDigestByteArray = null;
        String hexaHash = "";

        try {
            // we are using the MD5 hashing algorithm
            messageDigest = MessageDigest.getInstance("MD5");

            // we need the byte array when dealing with cryptography
            messageDigestByteArray = messageDigest.digest(message.getBytes());

            // 1 hexa character = 4 bits
            // we convert the message hash into hexadecmial (16 characters)
            // problem is that it may omit leading zeros
            hexaHash = new BigInteger(1, messageDigestByteArray).toString(16);

            while (hexaHash.length() < 32) {
                hexaHash = "0" + hexaHash;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexaHash;
    }
}
