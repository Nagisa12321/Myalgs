package com.jtchen.typeofdata.graph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/5 22:24
 */
public class DgraphImpl implements Dgraph {
	private final int V;
	private int E;
	private final ArrayList<Integer>[] lists;
	private boolean hasCycle;
	private List<Integer> cycle;

	@SuppressWarnings("unchecked")
	public DgraphImpl(int V) {
		this.V = V;
		this.E = 0;
		lists = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			lists[i] = new ArrayList<>();
		}
		this.hasCycle = false;
		this.cycle = new ArrayList<>();
	}

	public DgraphImpl(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		return E;
	}

	@Override
	public void addEdge(int v, int w) {
		lists[v].add(w);
		++E;
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return lists[v];
	}

	@Override
	public DgraphImpl reverse() {
		DgraphImpl res = new DgraphImpl(V);
		for (int i = 0; i < V; i++)
			for (int line : lists[i])
				res.addEdge(line, i);
		return res;
	}

	// v是可达的吗
	public boolean marked(int s, int v) {
		boolean[] mark = new boolean[V];
		dfs(mark, s, v);
		return mark[v];
	}

	private void dfs(boolean[] marked, int s, int v) {
		if (marked[s])
			return;
		marked[s] = true;

		List<Integer> neighborhood = lists[s];
		for (int nei : neighborhood) {
			dfs(marked, nei, v);
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < V; ++i) {
			builder.append(i)
					.append(": ")
					.append(lists[i])
					.append('\n');
		}
		return builder.toString();
	}


	public boolean hasCycle() {
		if (hasCycle) return true;

		boolean[] marked = new boolean[V];
		boolean[] onStack = new boolean[V];
		for (int v = 0; v < V; ++v) {
			if (hasCycle)
				return true;
			if (!marked[v])
				dfs(marked, onStack, v);
		}

		return false;
	}

	private void dfs(boolean[] marked, boolean[] onstack, int v) {
		if (onstack[v]) {
			hasCycle = true;
			return;
		}
		onstack[v] = true;
		marked[v] = true;
		for (int nei : adj(v)) {
			cycle.add(nei);
			dfs(marked, onstack, nei);
			if (hasCycle) return;
			cycle.remove(cycle.size() - 1);
		}

		onstack[v] = false;
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}
}
