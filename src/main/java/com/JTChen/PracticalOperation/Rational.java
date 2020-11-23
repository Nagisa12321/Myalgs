package com.JTChen.PracticalOperation;

import java.util.Objects;

/************************************************
 * @description 用有理数实现一个比u可变数据类型，支持加减乘除操作
 * @author jtchen
 * @date 2020/11/23 17:09
 * @version 1.0
 ************************************************/
public class Rational {
    private final int numerator, denominator;

    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational plus(Rational b) {
        int denominator = b.getDenominator() * this.denominator;
        int numerator = b.getNumerator() * this.denominator + this.numerator * b.getDenominator();
        int gcd = gcd(denominator, numerator);
        return new Rational(numerator / gcd, denominator / gcd);
    }

    public Rational minus(Rational b) {
        int denominator = b.getDenominator() * this.denominator;
        int numerator = this.numerator * b.getDenominator() - b.getNumerator() * this.denominator;
        int gcd = gcd(denominator, numerator);
        return new Rational(numerator / gcd, denominator / gcd);
    }

    public Rational times(Rational b) {
        int numerator = b.getNumerator() * this.numerator;
        int denominator = b.getDenominator() * this.denominator;
        int gcd = gcd(denominator, numerator);
        return new Rational(numerator / gcd, denominator / gcd);
    }

    public Rational divides(Rational b) {
        int numerator = b.getNumerator() * this.denominator;
        int denominator = b.getDenominator() * this.numerator;
        int gcd = gcd(denominator, numerator);
        return new Rational(numerator / gcd, denominator / gcd);
    }

    //辗转相除法
    private int gcd(int a, int b) {
        if (a % b == 0) return b;
        else return gcd(b, a % b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rational)) return false;
        Rational rational = (Rational) o;
        return numerator == rational.numerator &&
                denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return "Rational{ " + numerator +
                " / " + denominator +
                " }";
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public static void main(String[] args) {
        Rational a = new Rational(1, 2);
        Rational b = new Rational(3, 4);
        Rational c = b.plus(a);
        Rational d = b.minus(a);
        Rational e = b.times(a);
        Rational f = b.divides(a);
        System.out.println(c + " " + d + " " + e + " " + f);
    }

}
