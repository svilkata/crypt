package symmetric.des;

public class App {
    public static void main(String[] args) {
        DES des = new DES();

        String plainText = "Cryptography is important in cryptocurrencies!";

        String cryptoText = des.encrypt(plainText);
        System.out.println(cryptoText);

        System.out.println(des.decrypt(cryptoText));
    }
}
