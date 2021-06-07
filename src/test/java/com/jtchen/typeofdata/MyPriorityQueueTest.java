package com.jtchen.typeofdata;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MyPriorityQueueTest {

	@Test
	public void testOffer() {
		PriorityQueue<Integer> queue1 = new  PriorityQueue<>();
		MyPriorityQueue<Integer> queue2 = new  MyPriorityQueue<>();

		for (int i = 0; i < 50; i++) {
			int num = (int) (Math.random() * 50);
			queue1.offer(num);
			queue2.offer(num);
		}

		System.out.println(queue1);
		System.out.println(queue2);

		System.out.println(queue1.size());
		System.out.println(queue2.size());

		int[] nums1 = new int[25];
		int[] nums2 = new int[25];

		for (int i = 0; i < 25; i++) {
			nums1[i] = queue1.poll();
			nums2[i] = queue2.poll();
		}

		System.out.println(Arrays.toString(nums1));
		System.out.println(Arrays.toString(nums2));

		System.out.println(queue1.size());
		System.out.println(queue2.size());
	}

}