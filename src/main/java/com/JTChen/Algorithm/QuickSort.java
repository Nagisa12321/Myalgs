package com.JTChen.Algorithm;

/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/10/28
 */
public class QuickSort {

    public static void Sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int left, int right) {
        if (left < right) {
			//获取下标
			int index = getIndex(arr, left, right);
			//递归操作
			quicksort(arr, left, index - 1);
			quicksort(arr, index + 1, right);
		}
    }

    //获取中间值下标
    private static int getIndex(int[] arr, int left, int right) {
        int tmp = arr[left];
        while (left < right) {
            while (tmp <= arr[right] && left < right)
                right--;
            arr[left] = arr[right];
            while (tmp >= arr[left] && left < right)
                left++;
            arr[right] = arr[left];
        }
        arr[left] = tmp;
        return left;
    }
}
