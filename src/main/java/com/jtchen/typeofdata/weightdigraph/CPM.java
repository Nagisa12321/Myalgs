package com.jtchen.typeofdata.weightdigraph;

import edu.princeton.cs.algs4.In;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/22 16:41
 */
public class CPM {
	public static void main(String[] args) {
		In in = new In(args[0]);

		System.out.println("read the input successfully!");
		int tasks = in.readInt();
		in.readLine();
		EdgeWeightDigraph G = new EdgeWeightDigraph(tasks + 2);
		double[] time = new double[tasks];
		for (int i = 0; i < tasks; i++) {
			String line = in.readLine();
			String[] data = line.split("\\s+");
			double weight = Double.parseDouble(data[0]);
			time[i] = weight;
			for (int j = 2; j < data.length; j++) {
				G.addEdge(new DirectedEdge(i, Integer.parseInt(data[j]), weight));
			}
		}
		int[] inDegree =  new int[tasks];
		int[] outDegree =  new int[tasks];
		for (int i = 0; i < tasks; i++) {
			for (DirectedEdge edge : G.adj(i)) {
				inDegree[edge.to()]++;
				outDegree[edge.from()]++;
			}
		}
		int s = tasks, t = tasks + 1;
		for (int i = 0; i < tasks; i++) {
			if (inDegree[i] == 0)
				G.addEdge(new DirectedEdge(s, i, 0));
			if (outDegree[i] == 0)
				G.addEdge(new DirectedEdge(i, t, time[i]));
		}

		AcyclicLP acyclicLP = new AcyclicLP(G, s);
		System.out.println("start times: ");
		for (int i = 0; i < tasks; i++) {
			System.out.printf("%4d: %5.1f\n", i, acyclicLP.distTo(i));
		}
		System.out.println("finish time: " + acyclicLP.distTo(t));
	}
}
