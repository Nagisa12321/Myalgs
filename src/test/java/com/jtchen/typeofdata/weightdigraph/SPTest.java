package com.jtchen.typeofdata.weightdigraph;

import edu.princeton.cs.algs4.In;

public class SPTest {
	public static void main(String[] args) {
		In in = new In(args[0]);
		System.out.println("read the input successfully!");
		EdgeWeightDigraph G = new EdgeWeightDigraph(in);
		int s = Integer.parseInt(args[1]);
		SP sp = new BellmanFordSP(G, s);

		for (int v = 0; v < G.V(); v++) {
			System.out.print(s + " to " + v + ' ');
			System.out.print("(" + sp.distTo(v) + "): ");
			if (sp.hasPathTo(v))
				sp.pathTo(v).forEach(SPTest::printPath);
			System.out.println();
		}
		ThreadLocal local = new ThreadLocal();
		local.get();
	}

	public static void printPath(DirectedEdge edge) {
		System.out.print(edge + ", ");
	}
}