package com.jtchen.typeofdata.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/6 21:28
 */
public class StronglyConnect {
	private final boolean[] marked;
	private final int[] id;
	private int count;

	public StronglyConnect(DgraphImpl g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];

		for (int s : reversePost(g.reverse())) {
			if (!marked[s]) {
				dfs(g, s);
				count++;
			}
		}

	}

	private Iterable<Integer> reversePost(DgraphImpl g) {
		boolean[] marked = new boolean[g.V()];
		Stack<Integer> res = new Stack<>();
		for (int i = 0; i < g.V(); i++)
			reversePostHelper(res, marked, g, i);
		List<Integer> list = new ArrayList<>();
		while (!res.isEmpty())
			list.add(res.pop());
		return list;
	}

	private void reversePostHelper(Stack<Integer> res, boolean[] marked, DgraphImpl g, int v) {
		if (marked[v])
			return;
		marked[v] = true;
		for (int next : g.adj(v)) {
			reversePostHelper(res, marked, g, next);
		}
		res.push(v);
	}

	private void dfs(DgraphImpl g, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : g.adj(v)) {
			if (!marked[w])
				dfs(g, w);
		}
	}

	private boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}
}
