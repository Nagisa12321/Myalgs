package com.jtchen.typeofdata.weightdigraph;

import edu.princeton.cs.algs4.In;

import static org.junit.Assert.*;

public class EdgeWeightDigraphTest {
	public static void main(String[] args) {
		EdgeWeightDigraph G;
		G = new EdgeWeightDigraph(new In(args[0]));

		System.out.println(G);
	}
}