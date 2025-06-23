package asymmetric.rsa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    // this is the 'e' parameter part of the publicKey - the recipient's public key to be encrypted with
    private BigInteger publicKey;

    // this is the 'd' parameter part of the privateKey - the recipient's private key for decryption
    private BigInteger privateKey;

    // this is n=p*q, i.e. n is the moduloN
    private BigInteger n;

    private SecureRandom random; //random generator

    public RSA() {
        this.random = new SecureRandom();
    }

    // 1024 bit or 2048 bits
    public void generateKeys(int keyDigits) {
        // p is a large prime number
        BigInteger p = BigInteger.probablePrime(keyDigits, this.random);

        // p is a large prime number
        BigInteger q = BigInteger.probablePrime(keyDigits, this.random);

        // n = p*q
        // this is the trap-door function - integer prime factorization problem
        this.n = p.multiply(q);

        // Euler's totient phi function: phi(n) = (p-1)(q-1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // we have to use GCD to find 'e' and gcd(phi(n), e) = 1
        BigInteger e = generatePublicFactor(phi);

        // calculate d which is the modular inverse of 'e' (extended Euclidean algorithm)
        // in java it comes out of the box
        BigInteger d = e.modInverse(phi);

        // this is how we can encrypt messages
        this.publicKey = e;

        // this is how we can decrypt messages
        this.privateKey = d;

    }

    private BigInteger generatePublicFactor(BigInteger phi) {
        BigInteger e = new BigInteger(phi.bitLength(), this.random);

        while (!e.gcd(phi).equals(BigInteger.ONE)) {
            e = new BigInteger(phi.bitLength(), random);
        }

        return e;
    }

    public BigInteger encryptMessage(String message) {
        return encrypt(this.publicKey, this.n, message);
    }

    public String decryptMessage(BigInteger encryptedMessage) {
        return decrypt(this.privateKey, this.n, encryptedMessage);
    }

    // the ciphertext is a huge integer
    private BigInteger encrypt(BigInteger ePartOfPublicKey, BigInteger nModulos, String message) {
        byte[] messageBytes = message.getBytes();
        BigInteger messageInt  = new BigInteger(messageBytes);

        // we have to use modular exponentiation
        // so the ciphertext = messageInt  ^ e mod n
        return messageInt.modPow(ePartOfPublicKey, nModulos);
    }

    private String decrypt(BigInteger dPartOfPrivateKey, BigInteger nModulos, BigInteger cipherText) {
        // we use modular exponentiation for decryption as well
        // cipher ^ d mod n = plain text
        BigInteger messageInt = cipherText.modPow(dPartOfPrivateKey, nModulos);

        // we want to end up with a string
        return new String(messageInt.toByteArray());
    }
}
