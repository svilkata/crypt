package com.udemy.asymmetric.ecc;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        // Let's use Bitcoin values: a = 0, b = 7
        ECC ecc = new ECC(BigDecimal.ZERO, BigDecimal.valueOf(7));

        Point pointP = new Point(BigDecimal.ONE, BigDecimal.ONE);
        Point pointQ = new Point(BigDecimal.TWO, BigDecimal.TEN);
        System.out.println("Point Doubling result for R: " + ecc.pointAdditionXorPointDoubling(pointP, pointP));
        System.out.println("Point Addition result for R: " + ecc.pointAdditionXorPointDoubling(pointP, pointQ));

        System.out.println("----------------");
        System.out.println("Double and Add Algorithm result for R: " + ecc.doubleAndAdd(100, pointP));
    }

}
