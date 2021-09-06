package com.jtchen.typeofdata.weightgraph;

import edu.princeton.cs.algs4.In;

public class MSTTest {

	public static void main(String[] args) {
		In in = new In(args[0]);
		System.out.println("Successfully getting input.");
		EdgeWeightGraph G = new EdgeWeightGraph(in);

		long start, end;
		long primLazyTime, primTime, kruskalTime;

		start = System.currentTimeMillis();
		MST primLazy = new MST(MST.LazyPrim, G);
		end = System.currentTimeMillis();
		primLazyTime = end - start;

		start = System.currentTimeMillis();
		MST prim = new MST(MST.Prim, G);
		end = System.currentTimeMillis();
		primTime = end - start;

		start = System.currentTimeMillis();
		MST kruskal = new MST(MST.Kruskal, G);
		end = System.currentTimeMillis();
		kruskalTime = end - start;

		System.out.println("prim lazy:\ntime: " + primLazyTime + "ms\nweight: " + primLazy.weight());
		// primLazy.edges().forEach(System.out::println);
		System.out.println("\nprim:\ntime: " + primTime + "ms\nweight: " + prim.weight());
		// prim.edges().forEach(System.out::println);
		System.out.println("\nkruskal:\ntime: " + kruskalTime + "ms\nweight: " + kruskal.weight());
		// kruskal.edges().forEach(System.out::println);

	}
}