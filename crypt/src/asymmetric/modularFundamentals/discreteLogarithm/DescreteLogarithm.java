package asymmetric.modularFundamentals.discreteLogarithm;

import java.math.BigInteger;

public class DescreteLogarithm {

    // IT IS FAST
    // modular exponentiation - we just have to use the formula: (b^c) mod m
    public BigInteger modularExponentiation(BigInteger b, BigInteger c, BigInteger m) {
        return b.modPow(c, m);
    }


    // THIS IS SLOW OPERATION
    public BigInteger descreteLogarithm(BigInteger a, BigInteger b, BigInteger m) {
        // we try all the values if the exponent c one by one until we find it
        BigInteger c = new BigInteger("1");

        // if (b^c) mod m = a   it means we find the right exponent
        while (b.modPow(c, m).compareTo(a) != 0) {
            c = c.add(BigInteger.ONE);
        }

        return c;
    }
}
