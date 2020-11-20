package com.JTChen.PracticalOperation;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * @author jtchen
 * @version 1.0
 * @date 2020/11/20
 */
public class VisualAccmulator extends Accumulator {
    private double total;
    private int N;

    /**
     * @param trials
     * @param max    初始化
     */
    public VisualAccmulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    @Override
    public void addDataValue(double val) {
        super.addDataValue(val);
        ++N;
        total += val;
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(Color.PINK);
        StdDraw.point(N, total / N);
    }
}
