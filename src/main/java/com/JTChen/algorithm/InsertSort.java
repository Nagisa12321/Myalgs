package com.JTChen.algorithm;

import java.util.Arrays;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/16 11:54
 */
public class InsertSort {
	// arr [1, 2, 3, 4] --> we say isSort(arr, 0, 3)
	private static boolean isSorted(int[] arr, int begin, int end) {
		for (int i = begin + 1; i <= end; i++) {
			if (!less(arr, i - 1, i)) return false;
		}
		return true;
	}

	public static boolean isSorted(int[] arr) {
		return isSorted(arr, 0, arr.length - 1);
	}

	// return true if [i] less then [j]
	private static boolean less(int[] arr, int i, int j) {
		return arr[i] < arr[j];
	}

	public static void insertSort(int[] arr) {
		insertSort(arr, 0, arr.length - 1);
	}

	public static void insertSort(int[] arr, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			insert(arr, lo, i);
		}
	}

	// 		l        h
	// arr [1, 4, 6, 3]
	public static void insert(int[] arr, int lo, int hi) {
		int i = hi;
		int key = arr[hi];
		while (i > lo && arr[i - 1] >= key) {
			arr[i] = arr[i - 1];
			i--;
		}
		arr[i] = key;
	}


	public static void main(String[] args) {
		int[] arr = {1, 4, 2, 8, 5, 7};
		insertSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
