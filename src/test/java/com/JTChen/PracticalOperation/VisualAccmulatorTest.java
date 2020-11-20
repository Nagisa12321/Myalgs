package com.JTChen.PracticalOperation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author jtchen
 * @version 1.0
 * @date 2020/11/20
 */
public class VisualAccmulatorTest {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        VisualAccmulator a = new VisualAccmulator(T, 1.0);
        for (int i = 0; i < T; ++i)
            a.addDataValue(StdRandom.random());
        StdOut.println(a);
    }
}