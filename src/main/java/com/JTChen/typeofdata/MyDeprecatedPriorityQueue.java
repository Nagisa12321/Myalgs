package com.JTChen.typeofdata;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author jtchen
 * @version 1.0
 * @date 2020/12/29 15:20
 */
@Deprecated
@SuppressWarnings("unchecked")
public class MyDeprecatedPriorityQueue<E> extends AbstractQueue<E> {

	// 默认初始值
	private static final int DEFAULT_INITIAL_VALUE = 16;

	public Object[] tree;
	private int n;

	private Comparator<? super E> comparator;

	public MyDeprecatedPriorityQueue() {
		tree = new Object[DEFAULT_INITIAL_VALUE];
		n = 0;
	}

	public MyDeprecatedPriorityQueue(Comparator<E> comparator) {
		tree = new Object[DEFAULT_INITIAL_VALUE];
		n = 0;
		this.comparator = comparator;
	}

	// 自底向上的heapify, 用于增加节点使用
	private void heapify_bottom(int i) {
		if (i < 0) return;

		// 最大值下标
		int max = heapify_helper(tree, i, n);

		if (i != max) {
			swap(tree, i, max);
			if (i != 0)
				heapify_bottom((i - 1) / 2);
		}
	}

	private void swap(Object[] arr, int i, int j) {
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 自顶向下heapify
	private void heapify_down(Object[] tree, int n, int i) {
		if (i >= n) return;

		// 最大值下标
		int max = heapify_helper(tree, i, n);

		if (i != max) {
			swap(tree, i, max);
			heapify_down(tree, n, max);
		}
	}

	private int heapify_helper(Object[] tree, int i, int n) {
		int max = i;
		int c1 = 2 * i + 1;
		int c2 = 2 * i + 2;

		if (c1 < n && !compare((E) tree[c1], (E) tree[max]))
			max = c1;
		if (c2 < n && !compare((E) tree[c2], (E) tree[max]))
			max = c2;
		return max;
	}

	public boolean compare(E a, E b) {
		if (comparator != null)
			return comparator.compare(a, b) > 0;

		else {
			Comparable<E> comparable1 = (Comparable<E>) a;
			return comparable1.compareTo(b) > 0;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyPriorityQueueIterator();
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean offer(E e) {
		if (isFull()) raise();

		tree[n] = e;
		n++;
		heapify_bottom((n - 2) / 2);
		return true;
	}

	private boolean isFull() {
		return n == tree.length;
	}

	private void raise() {
		Object[] objs = new Object[2 * n];
		System.arraycopy(tree, 0, objs, 0, n);
		tree = objs;
	}

	@Override
	public E poll() {
		if (isEmpty()) return null;

		E result = (E) this.tree[0];
		swap(this.tree, 0, --n);
		// 从顶部到底部进行heapify;
		heapify_down(tree, n, 0);

		return result;
	}

	@Override
	public E peek() {
		if (isEmpty()) return null;
		else return (E) tree[0];
	}

	private class MyPriorityQueueIterator implements Iterator<E> {
		private final Object[] iteratorTree;
		private int idx;

		public MyPriorityQueueIterator() {
			idx = MyDeprecatedPriorityQueue.this.n;
			int len = MyDeprecatedPriorityQueue.this.tree.length;
			this.iteratorTree = new Object[len];
			System.arraycopy(MyDeprecatedPriorityQueue.this.tree, 0, this.iteratorTree, 0, len);
		}

		@Override
		public boolean hasNext() {
			return idx != 0;
		}

		@Override
		public E next() {
			E result = (E) iteratorTree[0];

			swap(iteratorTree, 0, --idx);
			heapify_down(iteratorTree, idx, 0);

			return result;
		}
	}
}
