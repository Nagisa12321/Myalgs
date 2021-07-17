package com.jtchen.typeofdata.weightgraph;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/16 11:48
 */
public class Edge implements Comparable<Edge> {

	// 权值
	private final double weight;

	private final int v;

	private final int w;

	public Edge(int v, int w, double weight) {
		this.weight = weight;
		this.v = v;
		this.w = w;
	}

	public int either() {
		return Math.min(v, w);
	}

	public int other(int v) {
		int otherNode;
		if (v == this.v) otherNode = this.w;
		else otherNode = this.v;
		return otherNode;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge that) {
		double tmp = this.weight - that.weight;
		if (tmp < 0) return -1;
		else if (tmp == 0) return 0;
		else return 1;
	}

	public String toString() {
		return "[" + v + ", " + w + ", " + weight + "]";
	}
}
