package com.JTChen.typeofdata;

import org.junit.Test;

public class MyPriorityQueueTest {
	@Test
	public void testCompare1() {
		MyPriorityQueue<String> queue = new MyPriorityQueue<>();
		System.out.println(queue.compare("abc", "defghi"));
	}

	@Test
	public void testOffer() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();


		int[] tree = {2, 5, 3, 1, 10, 4};
		for (int i = 0; i < 6; i++)
			queue.offer(tree[i]);

		System.out.println(queue);
	}

}