package com.jtchen.typeofdata.weightdigraph;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/20 14:55
 */
public interface SP {
	double distTo(int v);

	boolean hasPathTo(int v);

	Iterable<DirectedEdge> pathTo(int v);
}
