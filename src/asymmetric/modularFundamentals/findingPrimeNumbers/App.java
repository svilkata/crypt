package asymmetric.modularFundamentals.findingPrimeNumbers;


import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        System.out.println("Naive finding:");
        NaivePrimeTest test = new NaivePrimeTest();
        System.out.println(test.isPrime(9));
        System.out.println(test.isPrime(11));
        System.out.println(test.isPrime(2147483647));
        System.out.println(test.isPrime(672804137));

        System.out.println();
        System.out.println("Fermat's theorem finding:");
        FermatPrimeTest fermat = new FermatPrimeTest();
        System.out.println(fermat.isPrime(BigInteger.valueOf(2147483647), 10));
        System.out.println(fermat.isPrime(BigInteger.valueOf(12), 10));
        System.out.println(fermat.isPrime(BigInteger.valueOf(101), 10));
        System.out.println(fermat.isPrime(BigInteger.valueOf(1000000), 10));

    }
}
