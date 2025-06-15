package symmetric.aes;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    private SecretKey secretKey;
    private SecureRandom secureRandom;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private IvParameterSpec ivParams;

    public AES() {
        secureRandom = new SecureRandom();
        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
            encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] ivBytes = new byte[encryptCipher.getBlockSize()];
            secureRandom.nextBytes(ivBytes);
            ivParams = new IvParameterSpec(ivBytes);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
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
