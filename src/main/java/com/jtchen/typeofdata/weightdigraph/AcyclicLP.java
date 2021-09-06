package com.jtchen.typeofdata.weightdigraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/22 15:27
 */
public class AcyclicLP extends AbstractSP {

	public AcyclicLP(EdgeWeightDigraph G, int s) {
		super(G, s);
		Arrays.fill(distTo, Double.NEGATIVE_INFINITY);
		distTo[s] = 0;

		// 拓补排序的队列
		Queue<Integer> queue = new LinkedList<>();
		// 入度表
		int[] degree = new int[G.V()];

		// 写入度表
		for (int v = 0; v < G.V(); v++)
			G.adj(v).forEach(edge -> degree[edge.to()]++);

		// 把入度表中度数为0的添加到queue之中
		for (int v = 0; v < G.V(); v++)
			if (degree[v] == 0)
				queue.add(v);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (DirectedEdge e : G.adj(v)) {
				int w = e.to();
				if (distTo[w] < distTo[v] + e.weight()) {
					distTo[w] = distTo[v] + e.weight();
					edgeTo[w] = e;
				}

				degree[w]--;
				if (degree[w] == 0) {
					queue.offer(w);
				}
			}
		}
	}
}
