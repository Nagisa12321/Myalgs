package com.JTChen.algorithm;

import java.util.Arrays;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/21 10:52
 */
public class HeapSort {

	public static void heapsort(int[] arr) {
		buildheap(arr, arr.length);
		int i = arr.length - 1;
		while (i >= 0) {
			swap(arr, i, 0);
			heapify(arr, 0, i--);
		}
	}

	private static void buildheap(int[] arr, int n) {
		for (int i = (n - 2) / 2; i >= 0; i--) {
			heapify(arr, i, n);
		}
	}

	private static void heapify(int[] arr, int i, int n) {
		if (i >= n) return;

		int c1 = i * 2 + 1;
		int c2 = i * 2 + 2;
		int max = i;

		if (c1 < n && less(arr, max, c1)) max = c1;
		if (c2 < n && less(arr, max, c2)) max = c2;

		if (max != i) {
			swap(arr, i, max);
			heapify(arr, max, n);
		}
	}

	private static boolean isSorted(int[] arr) {
		return isSorted(arr, 0, arr.length - 1);
	}

	private static boolean isSorted(int[] arr, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (arr[i] < arr[i - 1]) return false;
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static boolean less(int[] arr, int i, int j) {
		return arr[i] < arr[j];
	}

	public static void main(String[] args) {
		int[] nums = new int[1000];
		for (int i = 0; i < 1000; i++) {
			nums[i] = (int) (Math.random() * 400);
		}
		System.out.println(Arrays.toString(nums));
		heapsort(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(isSorted(nums));
	}


}
