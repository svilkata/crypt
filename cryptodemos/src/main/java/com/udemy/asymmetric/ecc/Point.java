package com.udemy.asymmetric.ecc;

import java.math.BigDecimal;

// A Point on the Elliptic Curve
public class Point {
    private BigDecimal x;
    private BigDecimal y;

    Point () {

    }

    public Point (BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
