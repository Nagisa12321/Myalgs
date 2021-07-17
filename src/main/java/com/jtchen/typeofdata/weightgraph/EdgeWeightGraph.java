package com.jtchen.typeofdata.weightgraph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/16 11:55
 */
public class EdgeWeightGraph {
	private final int V;
	private int E;
	private final Set<Edge>[] edges;


	// 创建一个含有V个节点的带权图
	@SuppressWarnings("unchecked")
	public EdgeWeightGraph(int V) {
		this.V = V;
		this.E = 0;
		this.edges = new Set[V];
		for (int i = 0; i < V; i++)
			edges[i] = new HashSet<>();
	}

	public EdgeWeightGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new Edge(v, w, weight));
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(Edge edge) {
		int v = edge.either();
		int w = edge.other(v);

		edges[v].add(edge);
		edges[w].add(edge);
		E++;
	}

	// 和节点v关联的所有边
	public Iterable<Edge> adj(int v) {
 		return edges[v];
	}


	// 图的所有边
	public Iterable<Edge> edges() {
		List<Edge> edgeList = new ArrayList<>();
		for (int v = 0; v < V; v++) {
			for (Edge e : edges[v])
				if (e.other(v) > v)
					edgeList.add(e);
		}
		return edgeList;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("V: ").append(V).append('\n');
		builder.append("E: ").append(E).append('\n');

		for (int v = 0; v < V; v++) {
			builder.append(v).append(" -> ");
			for (Edge e : adj(v))
				builder.append(e).append(", ");
			builder.append('\n');
		}
		return builder.toString();
	}
}
