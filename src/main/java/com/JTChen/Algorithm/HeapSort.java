package com.JTChen.Algorithm;

/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/10/29
 */
public class HeapSort {
    /**
     * 构造函数
     *
     * @param arr 想排序的数组
     */
    public static void Sort(int[] arr) {
        //构造大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //交换堆顶元素和末尾元素,重新调整堆的结构
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i);
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 对每个“小堆”进行排序
     *
     * @param arr    总数组
     * @param i      父节点index
     * @param length 数组长度
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素i
        int temp = arr[i];
        //从左节点开始，也就是2i + 1处开始
        for (int k = i * 2 + 1; k < length; k = 2 * k + 1) {
            //如果右节点还比左节点大，那么k++，即指向右节点
            if (k + 1 < length && arr[k] < arr[k + 1])
                ++k;
            //如果子节点大于父节点，则将子节点赋值给父节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                //子节点成为新的父节点
                i = k;
            } else break;
        }
        arr[i] = temp;
    }

    /**
     * 交换元素
     *  @param arr 总数组
     * @param k   子节点index
     */
    private static void swap(int[] arr, int k) {
        int temp = arr[0];
        arr[0] = arr[k];
        arr[k] = temp;
    }
}
