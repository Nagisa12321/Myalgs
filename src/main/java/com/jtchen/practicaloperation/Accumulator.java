package com.jtchen.practicaloperation;

/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/11/19
 */
public class Accumulator {
    private double m;
    private double s;
    private int N;

    public Accumulator() {
        this.m = 0;
        this.N = 0;
    }

    public void addDataValue(double x) {
        N++;
        s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
        m = m + (x - m) / N;
    }

    public double mean() {
        return m;
    }

    public double var() {
        return s / (N - 1);
    }

    public double stddev() {
        return Math.sqrt(this.var());
    }

    public String toString() {
        return "Mean (" + N + " values): " + mean();
    }

    public static void main(String[] args) {
        Accumulator accumulator = new Accumulator();
        accumulator.addDataValue(5);
        accumulator.addDataValue(6);
        accumulator.addDataValue(7);
        System.out.println(accumulator.toString());
        System.out.println(accumulator.mean());
        System.out.println(accumulator.stddev());
    }
}
