package asymmetric.modularAdvanced.modularInverse;

public class ModularInverse {

    public int calculateBruteForce(int a, int modulos) {
        // this is the brute-force approach, so we check all possible values in the range [0, m-1]
        // running time seems to be O(m) linear, but actually it is exponential in the number of input bits
        for (int inverse = 0; inverse < modulos; inverse++) {
            if ((a * inverse) % modulos == 1) {
                return inverse;
            }
        }

        // when there is no modular inverse (a is not a coprime to modulos)
        return -1;
    }
}
