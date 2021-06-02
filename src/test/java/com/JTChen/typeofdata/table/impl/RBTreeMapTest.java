package com.JTChen.typeofdata.table.impl;

import org.junit.Before;
import org.junit.Test;

public class RBTreeMapTest {

	private RBTreeMap<Integer, String> map;

	@Before
	public void init() {
		map = new RBTreeMap<>();
	}

	@Test
	public void testPut() {
		for (int i = 0; i < 10000; i++) {
			int num = (int) (Math.random() * 10000);
			map.put(num, "test input");
		}
		map.show();
	}
}