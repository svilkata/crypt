package asymmetric.modularFundamentals.discreteLogarithm;


import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        DescreteLogarithm test = new DescreteLogarithm();
        System.out.println("Modular exponentiation:");
        BigInteger b = new BigInteger("5");
        BigInteger c = new BigInteger("948603");
        BigInteger m = new BigInteger("9048610007");
        System.out.println("Value a is:" + test.modularExponentiation(b, c, m));

        System.out.println();
        System.out.println("Descrete Logarithm:");
        BigInteger a = new BigInteger("3668993056");
        System.out.println("Value c is:" + test.descreteLogarithm(a, b, m));


    }
}
