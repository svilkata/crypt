package asymmetric.diffieHellman;


import java.math.BigInteger;

public class App {
    public static void main(String[] args) {

        // it should be a huge prime number
        BigInteger n = new BigInteger(Integer.toString(37));

        // g is the primitive root of n
        BigInteger g = new BigInteger(Integer.toString(13));

        DiffieHellman dh = new DiffieHellman();

        // At each run we have different same numbers for both Alice and Bob
        dh.generatePrivateKeys(n, g);
    }
}
