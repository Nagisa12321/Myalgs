package com.jtchen.typeofdata;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStringsTest {
    public static void main(String[] args) {
        ResizingArrayStack<String> s;
        s = new ResizingArrayStack<>(2);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}