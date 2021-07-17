package com.jtchen.typeofdata.weightgraph;

import edu.princeton.cs.algs4.In;

public class MSTTest {
	public static void main(String[] args) {
		In in = new In(args[0]);
		System.out.println("Successfully getting input.");
		EdgeWeightGraph G = new EdgeWeightGraph(in);
		long start, end;
		MST mst1, mst2;

		start = System.currentTimeMillis();
		mst1 = new MST(MST.Prim, G);
		end = System.currentTimeMillis();
		System.out.println("Prim's time: " + (end - start) + "ms. ");

		start = System.currentTimeMillis();
		mst2 = new MST(MST.LazyPrim, G);
		end = System.currentTimeMillis();
		System.out.println("Lazy Prim's time: " + (end - start) + "ms. ");

		MST mst3 = new MST(MST.Kruskal, G);

		System.out.println("Prim.weight: " + mst1.weight());
		System.out.println("Lazy Prim.weight: " + mst2.weight());

//		Iterator<Edge> i1 = mst1.edges().iterator();
//		Iterator<Edge> i2 = mst2.edges().iterator();
//		while (i1.hasNext()) {
//			assertEquals(i1.next(), i2.next());
//		}
	}
}