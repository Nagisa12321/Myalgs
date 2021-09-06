package com.jtchen.typeofdata.weightgraph;


import edu.princeton.cs.algs4.Point2D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/18 15:40
 */
public class GraphUtil {
	public static Point2D[] point2DS;

	public static Point2D[] createPos(EdgeWeightGraph g) {
		Point2D[] point2DS = new Point2D[g.V()];
		for (int v = 0; v < g.V(); v++) {
			double rmdX = Math.random();
			double rmdY = Math.random();
			point2DS[v] = new Point2D(rmdX, rmdY);
		}
		return point2DS;
	}
}
