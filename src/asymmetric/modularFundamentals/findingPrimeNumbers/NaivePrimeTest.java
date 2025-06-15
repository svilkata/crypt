package asymmetric.modularFundamentals.findingPrimeNumbers;

public class NaivePrimeTest {
    public boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        if (number == 2) {
            return true;
        }

        // consider even numbers
        if (number % 2 == 0) {
            return false;
        }

        // 1. we have already checked numbers 1 and 2
        // 2. finding primes up to N we just have to check numbers up to sqrt(N)
        // 3. increment by 2 because we have already considered even numbers
        for (int i = 3; i <= Math.floor(Math.sqrt(number)); i+=2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
