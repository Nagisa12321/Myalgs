package com.jtchen.practicaloperation;

import edu.princeton.cs.algs4.StdRandom;

public class AccumulatorTest {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.random());
        System.out.println(a);
    }
}