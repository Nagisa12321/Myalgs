package com.jtchen.typeofdata.graph;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DgraphImplTest {
	public static void main(String[] args) {
		DgraphImpl g = new DgraphImpl(new In(args[0]));

		List<Integer> sources = new ArrayList<>();
		for (int i = 1; i < args.length; ++i) {
			sources.add(Integer.parseInt(args[i]));
		}

		for (int v = 0; v < g.V(); v++) {
			for (int s : sources) {
				if (g.marked(s, v)) {
					System.out.print(v + " ");
					break;
				}
			}
		}
		System.out.println();

		// has cycle?
		System.out.println("has cycle? " + g.hasCycle());
		if (g.hasCycle()) System.out.println(g.cycle());
	}
}