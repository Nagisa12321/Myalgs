package com.jtchen.typeofdata.weightdigraph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/20 12:01
 */
public class EdgeWeightDigraph {

	// 邻接表
	private final List<DirectedEdge>[] edges;

	// V, E
	private final int V;
	private int E;

	@SuppressWarnings("unchecked")
	public EdgeWeightDigraph(int V) {
		this.V = V;
		this.E = 0;
		edges = new List[V];
		for (int v = 0; v < V; v++)
			edges[v] = new ArrayList<>();
	}

	public EdgeWeightDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int e = 0; e < E; e++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new DirectedEdge(v, w, weight));
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(DirectedEdge edge) {
		edges[edge.from()].add(edge);
		E++;
	}

	public Iterable<DirectedEdge> adj(int v) {
		return edges[v];
	}

	public Iterable<DirectedEdge> edges() {
		List<DirectedEdge> list = new ArrayList<>();
		for (List<DirectedEdge> edge : edges)
			list.addAll(edge);
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("v: ").append(V).append('\n');
		builder.append("e: ").append(E).append('\n');
		for (int v = 0; v < V; v++) {
			builder.append(v).append(": ");
			edges[v].forEach(edge -> builder.append(edge).append(", "));
			builder.append('\n');
		}
		return builder.toString();
	}
}
