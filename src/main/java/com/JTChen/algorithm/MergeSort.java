package com.JTChen.algorithm;

import java.util.Arrays;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/16 11:09
 */
public class MergeSort {
	private static final int CUTOFF = 5;

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
		return arr[i] <= arr[j];
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, new int[arr.length], 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int[] aux, int lo, int hi) {
		if (lo >= hi) return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(arr, aux, lo, mid);
		mergeSort(arr, aux, mid + 1, hi);

		merge(arr, aux, lo, mid, hi);
	}

	//  l        m           h
	// [1, 5, 7, 9, 2, 4, 6, 8]
	public static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {

		assert isSorted(arr, lo, mid);
		assert isSorted(arr, mid + 1, hi);

		// if the len < CUTOFF, then insertion.
		if (hi - lo + 1 <= CUTOFF) {
			InsertSort.insertSort(arr, lo, hi);
			return;
		} else if (less(arr, mid, mid + 1)) return;

		// copy
		for (int i = lo; i <= hi; i++) {
			aux[i] = arr[i];
		}

		// merge
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) arr[k] = aux[j++];
			else if (j > hi) arr[k] = aux[i++];
			else if (less(aux, i, j)) arr[k] = aux[i++];
			else arr[k] = aux[j++];
		}

		assert isSorted(arr, lo, hi);
	}

	public static void main(String[] args) {
		int[] nums = {7, 5, 6, 4};
//		for (int i = 0; i < 1000; i++) {
//			nums[i] = (int) (Math.random() * 1000);
//		}
		mergeSort(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(isSorted(nums));
	}
}
