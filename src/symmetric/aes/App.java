package symmetric.aes;

public class App {
    public static void main(String[] args) {
        AES aes = new AES();
        String plainText = "My name is Svilen";

        String cipherText = aes.encrypt(plainText);
        System.out.println(cipherText);

        System.out.println(aes.decrypt(cipherText));

    }
}
