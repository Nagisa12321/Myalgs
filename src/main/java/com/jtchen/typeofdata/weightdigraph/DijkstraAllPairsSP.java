package com.jtchen.typeofdata.weightdigraph;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/21 11:20
 */
public class DijkstraAllPairsSP {
	private final DijkstraSP[] all;

	public DijkstraAllPairsSP(EdgeWeightDigraph G) {
		all = new DijkstraSP[G.V()];
		for (int v = 0; v < G.V(); v++) {
			all[v] = new DijkstraSP(G, v);
		}
	}

	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);
	}

	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
}
