package asymmetric.rsaWithAES;

public class App {

    //we usually do not use RSA for encryption and decryption
    // Reason: RSA is dealing with extremely large numbers (exponents)
    // We usually use AES - the so called sessionKey (privateKey for AES for both encryption and decryption)
    // RSA to encrypt this session AES privateKey

    public static void main(String[] args) {
        
    }
}
