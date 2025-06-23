package com.udemy.rsaWithAES;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    private SecretKey secretKey;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private IvParameterSpec ivParams;

    // we have to use same initialization vector both for encryption and for decryption !!!
    public AES(SecretKey secretKey, byte[] initializationVector) {
        try {
            this.secretKey = secretKey;
            this.encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.ivParams = new IvParameterSpec(initializationVector);
            this.encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            this.decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String plainText) {
        byte[] plainTextBytes = plainText.getBytes();
        byte[] encryptedBytes = null;

        try {
            encryptedBytes = encryptCipher.doFinal(plainTextBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String cipherText) {
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText.getBytes());
        byte[] plainTextBytes = null;

        try {
            plainTextBytes = decryptCipher.doFinal(encryptedBytes);
            return new String(plainTextBytes, "UTF8");
        } catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
