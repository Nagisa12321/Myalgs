package com.jtchen.typeofdata.weightdigraph;

import java.util.*;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/20 15:17
 */
public abstract class AbstractSP implements SP {
	protected final DirectedEdge[] edgeTo;
	protected final double[] distTo;

	// s means source
	public AbstractSP(EdgeWeightDigraph G, int s) {
		// edgeTo[v] - s->v 的最短路径的最后一条边
		edgeTo = new DirectedEdge[G.V()];
		// distTo[v] - s->v 的最短路径距离
		distTo = new double[G.V()];

		// 全部初始化为最大值, 除了s->s
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[s] = 0;

	}

	protected void relax(DirectedEdge edge) {
		int v = edge.from();
		int w = edge.to();
		if (distTo[w] > distTo[v] + edge.weight()) {
			distTo[w] = distTo[v] + edge.weight();
			edgeTo[w] = edge;
		}
	}

	@Override
	public double distTo(int v) {
		return distTo[v] == Double.POSITIVE_INFINITY ?
				Double.POSITIVE_INFINITY :
				(double) Math.round(distTo[v] * 1000) / 1000;
	}

	@Override
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	@Override
	public Iterable<DirectedEdge> pathTo(int v) {
		LinkedList<DirectedEdge> path = new LinkedList<>();

		while (edgeTo[v] != null) {
			path.addFirst(edgeTo[v]);
			v = edgeTo[v].from();
		}


		return path;
	}
}
