package com.jtchen.typeofdata.weightdigraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/24 22:25
 */
public class BellmanFordSP extends AbstractSP {
	public BellmanFordSP(EdgeWeightDigraph G, int s) {
		// 全部初始化为最大值, 除了s->s
		super(G, s);

		Queue<Integer> queue = new LinkedList<>();
		boolean[] marked = new boolean[G.V()];

		queue.offer(s);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (marked[cur])
				continue;
			marked[cur] = true;
			System.out.println("== now deal with [" + cur + "] ==");
			for (DirectedEdge edge : G.adj(cur)) {
				relax(edge);
				System.out.println("relax edge: " + edge);
				queue.offer(edge.to());
			}
		}
	}
}
