package com.JTChen.typeofdata.table.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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

	@Test
	public void testDelete() {
		int[] nums = new int[1000];
		for (int i = 0; i < 1000; i++) {
			nums[i] = (int) (Math.random() * 1000);
			map.put(nums[i], "#");
		}
		System.out.println(Arrays.toString(nums));
		map.show();

		int[] nums_del = new int[1000];
		for (int i = 0; i < 1000; i++) {
			nums_del[i] = (int) (Math.random() * 1000);
		}
		System.out.println(Arrays.toString(nums_del));
		for (int i = 0; i < 1000; i++) {
			map.delete(nums_del[i]);
		}
		map.show();
	}

	@Test
	public void testDeleteRandom() {
		int[] nums = {9, 1, 4, 0, 3, 5, 1, 2, 6, 7};
		for (int i = 0; i < 10; i++) {
			map.put(nums[i], "#");
		}
		map.show();

		int[] nums_del = {4, 2, 8, 4, 9, 2, 3, 1, 7, 7};
		for (int i = 0; i < 10; i++) {
			map.delete(nums_del[i]);
		}
		map.show();
	}
}