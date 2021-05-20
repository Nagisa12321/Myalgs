package com.JTChen.algorithm;

import java.util.Arrays;

/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/10/28
 */
public class QuickSort {
	private static final int CUTOFF = 15;

	public static void qsort(int[] arr) {
		qsort(arr, 0, arr.length - 1);
	}

	private static void qsort(int[] arr, int lo, int hi) {
		if (lo >= hi) return;
		if (hi - lo + 1 <= CUTOFF) {
			InsertSort.insertSort(arr, lo, hi);
			return;
		}
		int m = medianOf3(arr, lo, hi, lo + (hi - lo) / 2);
		swap(arr, lo, m);

		int mid = partition(arr, lo, hi);

		qsort(arr, lo, mid - 1);
		qsort(arr, mid + 1, hi);
	}

	private static int medianOf3(int[] arr, int lo, int hi, int mid) {
		int result;
		if (arr[lo] < arr[hi]) {
			if (arr[mid] > arr[hi]) result = hi;
			else if (arr[mid] > arr[lo]) result = mid;
			else result = lo;
		} else {
			if (arr[mid] > arr[lo]) result = lo;
			else if (arr[mid] > arr[hi]) result = mid;
			else result = hi;
		}
		return result;
	}

	// 对arr 进行处理, 返回了一个下标idx
	// [lo, idx) 的值均小于[idx]
	// (idx, hi] 的值均大于[idx]
	private static int partition(int[] arr, int lo, int hi) {
		int i = lo, j = hi + 1;
		while (true) {
			// 先移动左指针直到不符合条件
			while (less(arr, ++i, lo)) {
				if (i == hi) break;
			}
			// 移动右指针直到不符合条件
			while (less(arr, lo, --j)) {
				if (j == lo) break;
			}
			// 如果此时已经指针交叉, 那就返回吧
			if (i >= j) break;
			// 否则对左指针和右指针指向的两个不符合条件的数组项进行交换
			// 使得它们符合条件
			swap(arr, i, j);
		}
		// j的指针就是对的位置
		swap(arr, lo, j);
		return j;
	}

	// the top K -> quick selection
	public static int select(int[] arr, int k) {
		assert k <= arr.length;

		int lo = 0, hi = arr.length - 1;
		while (lo < hi) {
			int j = partition(arr, lo, hi);
			if (j < k) lo = j + 1;
			else if (j > k) hi = j - 1;
			else return arr[j];
		}

		// the worst case -> arr just been sorted.
		return arr[k];
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
		int[] arr = new int[20];
		for (int i = 0; i < 20; i++) {
			arr[i] = (int) (Math.random() * 20);
		}
		System.out.println(select(arr, 3));
		qsort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
