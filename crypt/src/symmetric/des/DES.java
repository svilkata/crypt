package symmetric.des;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class DES {
    private SecretKey secretKey;
    private SecureRandom secureRandom;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private IvParameterSpec ivParams;

    public DES() {
        try {
            secretKey = KeyGenerator.getInstance("DES").generateKey();
//            System.out.println(this.secretKey.getEncoded());
//            System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded())); //String representation

            secureRandom = new SecureRandom();
            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] ivBytes = new byte[encryptCipher.getBlockSize()];
            secureRandom.nextBytes(ivBytes);
            ivParams = new IvParameterSpec(ivBytes);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String plainText) {
        byte[] bytes = plainText.getBytes();
        byte[] cipherText = null;

        try {
            cipherText = encryptCipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public String decrypt(String cipherText) {
        byte[] plainText = null;

        try {
            plainText = decryptCipher.doFinal(Base64.getDecoder().decode(cipherText.getBytes()));
            return new String(plainText, "UTF8");
        } catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


}
