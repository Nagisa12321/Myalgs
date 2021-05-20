package com.JTChen.algorithm;

import java.util.Arrays;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/20 12:09
 */
public class ThreeWaysQuickSort {
	public static void qsort(int[] arr) {
		qsort(arr, 0, arr.length - 1);
		assert isSorted(arr);
	}

	private static boolean isSorted(int[] arr) {
		return isSorted(arr, 0, arr.length - 1);
	}

	private static boolean isSorted(int[] arr, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (arr[i] < arr[i - 1]) return false;
		return true;
	}

	private static void qsort(int[] arr, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo, gt = hi;
		int v = arr[lo];
		int i = lo + 1;
		while (i <= gt) {
			int cmp = arr[i] - v;
			if (cmp < 0) swap(arr, lt++, i++);
			else if (cmp > 0) swap(arr, i, gt--);
			else i++;
		}

		qsort(arr, lo, lt - 1);
		qsort(arr, gt + 1, hi);

		assert isSorted(arr, lo, hi);

	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	public static void main(String[] args) {
		int[] arr0 = new int[30];
		for (int i = 0; i < 30; i++) {
			arr0[i] = (int) (Math.random() * 10);
		}
		System.out.println(Arrays.toString(arr0));
		qsort(arr0);
		System.out.println(Arrays.toString(arr0));

		int[] arr1 = new int[100000000];
		int[] arr2 = new int[100000000];

		for (int i = 0; i < 100000000; i++) {
			int num = (int) (Math.random() * 3);
			arr1[i] = num;
			arr2[i] = num;
		}

		long t1 = System.currentTimeMillis();
		QuickSort.qsort(arr1);
		long t2 = System.currentTimeMillis();
		System.out.println("qsort: " + (t2 - t1) + "ms.");

		t1 = System.currentTimeMillis();
		qsort(arr2);
		t2 = System.currentTimeMillis();
		System.out.println("3 way qsort: " + (t2 - t1) + "ms.");

	}
}
