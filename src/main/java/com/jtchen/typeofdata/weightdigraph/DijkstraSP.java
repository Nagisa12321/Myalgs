package com.jtchen.typeofdata.weightdigraph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/21 10:25
 */
public class DijkstraSP extends AbstractSP {
	public DijkstraSP(EdgeWeightDigraph G, int s) {
		super(G, s);

		PriorityQueue<Integer> pq =
				new PriorityQueue<>(Comparator.
						comparingDouble(value -> distTo[value]));

		pq.offer(s);

		// 每个点只放松一次
		boolean[] marked = new boolean[G.V()];
		while (!pq.isEmpty()) {
			// 要放松的点
			int v = pq.poll();

			if (marked[v]) continue;
			marked[v] = true;

			for (DirectedEdge edge : G.adj(v)) {
				int w = edge.to();
				if (distTo[w] > distTo[v] + edge.weight()) {
					distTo[w] = distTo[v] + edge.weight();
					edgeTo[w] = edge;
					pq.offer(w);
				}
			}
		}
	}
}
