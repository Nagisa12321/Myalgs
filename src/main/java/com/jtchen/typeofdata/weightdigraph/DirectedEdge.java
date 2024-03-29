package com.jtchen.typeofdata.weightdigraph;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/20 11:50
 */
public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;

	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}

	public double weight() {
		return weight;
	}

	public String toString() {
		return "[" + v + " -> " + w + ", " + weight + ']';
	}
}
