package com.jtchen.typeofdata.weightgraph;

import edu.princeton.cs.algs4.In;

import static org.junit.Assert.*;

public class EdgeWeightGraphTest {
	public static void main(String[] args) {
		EdgeWeightGraph edgeWeightGraph = new EdgeWeightGraph(new In("src/main/resources/tinyEWG.txt"));
		System.out.println(edgeWeightGraph);
	}
}