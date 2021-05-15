package com.JTChen.typeofdata;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/3/15 10:45
 */
public class MyHeap {
	/*

	Heap:
		1. Complete Binary Tree (完全二叉树)
			生成节点顺序: 从上到下, 从左到右+
		2. parent > children

	parent = (i - 1) / 2
	c1 	   = 2i + 1
	c2	   = 2i + 2

	1. heapify --> 维护一个三角关系
	2. buildHeap --> 从最后一个非叶子节点开始heapify直至0, 最后形成一个堆
	3. heapSort --> 每次都交换第一个节点和最后一个节点, 然后砍断一个, 重新从0节点开始heapify
	 */


	/**
	 * 使得某个节点和它的两个孩子构成一个堆
	 *
	 * @param tree 树
	 * @param n    树中有多少个节点
	 * @param i    要对哪个节点进行heapify操作
	 */
	public void heapify(int[] tree, int n, int i) {
		if (i >= n) return; // 递归出口

		int c1 = 2 * i + 1;
		int c2 = 2 * i + 2;
		int max = i; // 最大值下标
		if (c1 < n && tree[c1] > tree[max]) {
			max = c1;
		}
		if (c2 < n && tree[c2] > tree[max]) {
			max = c2;
		}
		if (i != max) {
			swap(tree, max, i);
			heapify(tree, n, max);
		}
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * 若要从一个乱序数组中构建堆, 则应该对每个节点都进行heapify
	 * 并且从最后一个结点的parent节点开始, 从后往前heapify
	 *
	 * @param tree 待构建堆
	 * @param n    堆之大小
	 */
	public void buildHeap(int[] tree, int n) {
		int last_index = (n - 2) / 2;
		for (int i = last_index; i >= 0; i--) {
			heapify(tree, n, i);
		}
	}

	/**
	 * 每次都用堆顶节点(最大节点)和最后的节点进行交换, 交换之后砍断最后的节点
	 * 因此是heapify(tree, i, 0) 用i来代表现在的树的长度
	 * <p>
	 * 因此最后会得到从小到大排序的数组
	 *
	 * @param tree 待排序的数组
	 * @param n    数组的大小
	 */
	public void heapSort(int[] tree, int n) {
		buildHeap(tree, n);

		for (int i = n - 1; i >= 0; i--) {
			// 交换最后一个节点和第0个节点
			swap(tree, 0, i);
			heapify(tree, i, 0);
		}
	}


	@Test
	public void testHeapify() {
		int[] tree = {4, 10, 3, 5, 1, 2};
		heapify(tree, 6, 0);
		System.out.println(Arrays.toString(tree));
	}

	@Test
	public void testBuildHeap() {
		int[] tree = {2, 5, 3, 1, 10, 4};
		int n = 6;
		buildHeap(tree, n);

		System.out.println(Arrays.toString(tree));
	}

	@Test
	public void testHeapSort() {
		int[] tree = {2, 5, 3, 1, 10, 4};
		int n = 6;
		heapSort(tree, n);

		System.out.println(Arrays.toString(tree));
	}
}
