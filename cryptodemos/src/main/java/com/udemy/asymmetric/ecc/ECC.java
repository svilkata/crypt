package com.udemy.asymmetric.ecc;

import java.math.BigDecimal;
import java.math.MathContext;

public class ECC {

    // y^2 = x^3 + ax + b
    // In Bitcoin a=0 and b=7 (y^2 = x^3 + 7)
    private BigDecimal a;
    private BigDecimal b;

    public ECC(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public Point pointAdditionXorPointDoubling(Point p, Point q) {
        BigDecimal x1 = p.getX();
        BigDecimal y1 = p.getY();
        BigDecimal x2 = q.getX();
        BigDecimal y2 = q.getY();

        BigDecimal m = BigDecimal.ZERO; //defining initial value for the slope of EC
        // sometimes we have to make Point Addition and
        // if P==Q, then we need to do Point Doubling
        if (x1.compareTo(x2) == 0 && y1.compareTo(y2) == 0) {
            // Point Doubling: m = (3 x^2 + a) / 2y
            m = ((BigDecimal.valueOf(3).multiply(x1.pow(2))).add(a))
//                    .divide(BigDecimal.TWO.multiply(y1), 20, RoundingMode.HALF_UP);
                    .divide(BigDecimal.TWO.multiply(y1), MathContext.DECIMAL64);
        } else {
            // Point Addition (P != Q)
            m = (y2.subtract(y1))
//                    .divide(x2.subtract(x1), 20, RoundingMode.HALF_UP);
                    .divide(x2.subtract(x1), MathContext.DECIMAL64);
        }

        // calculating the point R (x3, y3)
        // x3 = m^2 -x1 - x2
        // y3 = m * (x1 - x3) - y1
        BigDecimal x3 = ((m.pow(2)).subtract(x2)).subtract(x1);
        BigDecimal y3 = (m.multiply(x1.subtract(x3))).subtract(y1);

        return new Point(x3, y3);
    }

    // it has O(n) linear running time complexity
    public Point doubleAndAdd(int times, Point p) {
        Point temp = new Point(p.getX(), p.getY());

        String timesBinary = Integer.toBinaryString(times);

        // we are going to omit/skip the first bit
        for (int i = 1; i < timesBinary.length(); ++i) {
            // Point Doubling
            temp = pointAdditionXorPointDoubling(temp, temp);

            int actualBit = Integer.parseInt(String.valueOf(timesBinary.charAt(i)));
            if (actualBit == 1) {
                // Point Addition
                temp = pointAdditionXorPointDoubling(temp, p);
            }
        }

        return temp;
    }
}
