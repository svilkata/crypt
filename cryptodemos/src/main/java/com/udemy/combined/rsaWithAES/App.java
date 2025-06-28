package com.udemy.combined.rsaWithAES;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.util.HexFormat;

public class App {

    //we usually do not use RSA for encryption and decryption
    // Reason: RSA is dealing with extremely large numbers (exponents)
    // We usually use AES - the so called sessionKey (privateKey for AES for both encryption and decryption)
    // RSA to encrypt this session AES privateKey

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Security.addProvider(new BouncyCastleProvider());

        String message = "This is just a simple message";

        // generate 2048 bits long RSA keys
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyGen.initialize(2048);

        KeyPair keyPair = rsaKeyGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // decrypt the session key (private key for AES)
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // encrypt the session key (private key for AES)

        System.out.println("privateKey - the Modulus n: " + privateKey.getModulus());
        System.out.println("privateKey - the private Exponent d: " + privateKey.getPrivateExponent());
        System.out.println("---------------");
        System.out.println("publicKey: " + publicKey);

        // -----------------------------------

        // CLIENT SIDE == SENDER
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        aesKeyGen.init(256);
        SecretKey sessionKey = aesKeyGen.generateKey();

        System.out.println("-------------------");
        System.out.println("AES session key in HEX format: " + HexFormat.of().formatHex(sessionKey.getEncoded()));

        // AES initialization vector (IV)
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        // AES encryption
        AES aesEncrypt = new AES(sessionKey, iv);
        String cipherText = aesEncrypt.encrypt(message);
        System.out.println("Cipher Text: " + cipherText );

        // use the RSA public key for encrypting the AES session key
        Cipher encryptCipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding");
        // OAEP - Optimal asymmetric encryption padding
        // MGF1 - Mask Generation function
        OAEPParameterSpec specEncryptSessionKey = new OAEPParameterSpec("SHA-256", "MGF1",
                MGF1ParameterSpec.SHA3_256, PSource.PSpecified.DEFAULT);
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey, specEncryptSessionKey);

        // finish the AES session key encryption with the RSA public key
        byte[] encryptedSessionKey = encryptCipher.doFinal(sessionKey.getEncoded());
        System.out.println("AES Session Key encrypted with the RSA public key. In HEX format: " + HexFormat.of().formatHex(encryptedSessionKey));

        // we send from client/sender to the server/recipient:
        // cipherText, encrypted AES session key, RSA public key, iv

        // -------------------
        // SERVER SIDE = Recipient's side
        Cipher decryptCipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding");
        OAEPParameterSpec specDecryptSessionKey = new OAEPParameterSpec("SHA-256", "MGF1",
                MGF1ParameterSpec.SHA3_256, PSource.PSpecified.DEFAULT);
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey, specDecryptSessionKey);
        byte[] decryptedSessionKeyInBytes = decryptCipher.doFinal(encryptedSessionKey);
        System.out.println("AES Session Key decrypted with RSA private key. In HEX format: " + HexFormat.of().formatHex(decryptedSessionKeyInBytes));

        // AES with this session key to decrypt the cipher text
        SecretKey decryptedSessionKey = new SecretKeySpec(decryptedSessionKeyInBytes, 0, decryptedSessionKeyInBytes.length, "AES");
        AES decryptAES = new AES(decryptedSessionKey, iv);
        System.out.println("Decrypted message: " +  decryptAES.decrypt(cipherText));
    }
}
