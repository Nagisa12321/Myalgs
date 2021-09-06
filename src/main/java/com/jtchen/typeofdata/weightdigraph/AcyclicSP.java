package com.jtchen.typeofdata.weightdigraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/21 11:49
 */

// 限定于无环图
public class AcyclicSP extends AbstractSP {

	public AcyclicSP(EdgeWeightDigraph G, int s) {
		super(G, s);

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
				relax(e);

				int w = e.to();

				degree[w]--;
				if (degree[w] == 0) {
					queue.offer(w);
				}
			}
		}
	}
}
