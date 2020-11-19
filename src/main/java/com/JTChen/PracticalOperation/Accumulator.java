package com.JTChen.PracticalOperation;

/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/11/19
 */
public class Accumulator {
    private double val;
    private int num;

    public Accumulator() {
        this.val = 0;
        this.num = 0;
    }

    public void addDataValue(double val) {
        this.val += val;
        this.num++;
    }

    public double mean() {
        return this.val / this.num;
    }

    public String toString() {
        return "Mean (" + num + " values): " + mean();
    }
}
