package com.jtchen.typeofdata.weightgraph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.LinearProbingHashST;

import java.util.*;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/17 11:06
 */
public class MST {
	public final static String Kruskal = "Kruskal";
	public final static String Prim = "Prim";
	public final static String LazyPrim = "LazyPrim";
	private final static String DEFAULT_SOLUTION = LazyPrim;
	private final Solution solution;

	public MST(EdgeWeightGraph G) {
		this(DEFAULT_SOLUTION, G);
	}

	public MST(String solution, EdgeWeightGraph G) {
		if (solution.equals("Prim"))
			this.solution = new Prim(G);
		else if (solution.equals("LazyPrim")) {
			this.solution = new LazyPrim(G);
		} else this.solution = new Kruskal(G);
	}

	// 最小生成树的所有边
	public Iterable<Edge> edges() {
		return solution.edges();
	}


	//  最小生成树的权重
	public double weight() {
		return solution.weight();
	}

	private interface Solution {
		Iterable<Edge> edges();

		double weight();
	}

	private static class LazyPrim implements Solution {

		private final List<Edge> mst;
		private final double weight;

		public LazyPrim(EdgeWeightGraph G) {
			// 树的点
			boolean[] marked = new boolean[G.V()];
			// 横切边
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			// 最小生成树的边
			mst = new ArrayList<>();

			// 初始化pq, marked
			marked[0] = true;
			for (Edge e : G.adj(0))
				pq.offer(e);

			// 迭代计算, 直到所有点进入最小生成树
			// 在此假设G为连通图
			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				int v = cur.either();
				int w = cur.other(v);

				// 两端都在最小生成树中, 因此是失效边
				if (marked[v] && marked[w])
					continue;

				mst.add(cur);
				// 新的节点
				int newNode = marked[v] ? w : v;
				marked[newNode] = true;
				for (Edge e : G.adj(newNode))
					pq.offer(e);
			}

			// weight
			double tmp = .0;
			for (Edge e : mst)
				tmp += e.getWeight();
			weight = (double) Math.round(tmp * 10000000) / 10000000;

			// sort list
//			mst.sort((e1, e2) -> {
//				int v1 = e1.either();
//				int w1 = e1.other(v1);
//				int v2 = e2.either();
//				int w2 = e2.other(v2);
//
//				if (v1 < v2) return -1;
//				else if (v1 > v2) return 1;
//				else return w1 - w2;
//			});
		}

		@Override
		public Iterable<Edge> edges() {
			return mst;
		}

		@Override
		public double weight() {
			return weight;
		}
	}

	private static class Prim implements Solution {

		private final List<Edge> mst;
		private double weight;

		public Prim(EdgeWeightGraph G) {
			// edgeTo[v] - 将v点和树相连的最短边
			Edge[] edgeTo = new Edge[G.V()];
			// 标记树中的节点
			boolean[] marked = new boolean[G.V()];
			// 横切边
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			// 初始化pq, marked
			marked[0] = true;
			for (Edge e : G.adj(0)) {
				pq.offer(e);
				edgeTo[e.other(0)] = e;
				if (e.getWeight() < getWeight(0, edgeTo))
					edgeTo[0] = e;
			}

			while (!pq.isEmpty()) {
				Edge cur = pq.poll();

				int v = cur.either();
				int w = cur.other(v);

				// 新的节点
				int newNode = marked[v] ? w : v;
				marked[newNode] = true;

				// 更新edgeTo, pq
				for (Edge e : G.adj(newNode)) {
					int tmpW = e.other(newNode);

					// 如果另外的点是标记过的, 跳过
					if (marked[tmpW])
						continue;
					// 走到这里代表e是新的边, tmpW是新的节点
					// 如果权值比edgeTo中的权值还小, 那么, 更新edgeTo
					// 也可以加入pq中
					if (getWeight(tmpW, edgeTo) > e.getWeight()) {
						edgeTo[tmpW] = e;
						pq.offer(e);
					}
				}
			}

			mst = new ArrayList<>();
			for (int v = 1; v < G.V(); v++) {
				mst.add(edgeTo[v]);
			}

			// weight
			double tmp = .0;
			for (Edge e : mst)
				tmp += e.getWeight();
			weight = (double) Math.round(tmp * 100000) / 100000;

			// sort list
//			mst.sort((e1, e2) -> {
//				int v1 = e1.either();
//				int w1 = e1.other(v1);
//				int v2 = e2.either();
//				int w2 = e2.other(v2);
//
//				if (v1 < v2) return -1;
//				else if (v1 > v2) return 1;
//				else return w1 - w2;
//			});
 		}

 		private double getWeight(int i, Edge[] edges) {
			return edges[i] == null ? Double.POSITIVE_INFINITY : edges[i].getWeight();
		}

		@Override
		public Iterable<Edge> edges() {
			return mst;
		}

		@Override
		public double weight() {
			return weight;
		}
	}

	private static class Kruskal implements Solution {

		public Kruskal(EdgeWeightGraph G) {
		}

		@Override
		public Iterable<Edge> edges() {
			return null;
		}

		@Override
		public double weight() {
			return 0;
		}
	}
}
