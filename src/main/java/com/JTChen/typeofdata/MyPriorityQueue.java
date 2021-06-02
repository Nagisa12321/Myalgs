package com.JTChen.typeofdata;

import java.util.*;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/21 10:55
 */
@SuppressWarnings("unchecked")
public class MyPriorityQueue<E> extends AbstractQueue<E> {

	private Object[] heap;

	private final Comparator<E> comparator;

	private int size;

	private static final int DEFAULT_INITIAL_CAPACITY = 11;

	public MyPriorityQueue() {
		this(DEFAULT_INITIAL_CAPACITY, null);
	}

	public MyPriorityQueue(int initialCapacity) {
		this(initialCapacity, null);
	}

	public MyPriorityQueue(Collection<E> collection) {
		this(DEFAULT_INITIAL_CAPACITY, null);
		addAll(collection);
	}

	public MyPriorityQueue(int initialCapacity, Comparator<E> comparator) {
		this.heap = new Object[initialCapacity];
		this.comparator = comparator;
		this.size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		List<E> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add((E) heap[i]);
		}
		return list.iterator();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean offer(E e) {
		if (isFull()) raise();
		heap[size] = e;
		heapifyUp(this.heap, (size++ - 1) / 2, size);
		return true;
	}

	@Override
	public E poll() {
		if (isEmpty()) return null;
		E result = (E) heap[0];
		swap(this.heap, 0, --size);
		heapifyDown(this.heap, 0, size);
		return result;
	}

	@Override
	public E peek() {
		return (E) heap[0];
	}

	private boolean isFull() {
		return size == heap.length;
	}

	private void raise() {
		Object[] tmp = new Object[heap.length * 2];
		System.arraycopy(heap, 0, tmp, 0, heap.length);
		this.heap = tmp;
	}

	private void heapifyUp(Object[] arr, int i, int n) {
		if (i < 0) return;

		int max = heapifyHelper(arr, i, n);

		if (max != i) {
			swap(arr, i, max);
			heapifyUp(arr, (i - 1) / 2, n);
		}
	}

	private void heapifyDown(Object[] arr, int i, int n) {
		if (i >= n) return;

		int max = heapifyHelper(arr, i, n);

		if (max != i) {
			swap(arr, i, max);
			heapifyDown(arr, max, n);
		}
	}

	private int heapifyHelper(Object[] arr, int i, int n) {
		int max = i;
		int c1 = i * 2 + 1;
		int c2 = i * 2 + 2;

		if (c1 < n && compare(arr[c1], arr[max]) < 0) max = c1;
		if (c2 < n && compare(arr[c2], arr[max]) < 0) max = c2;

		return max;
	}

	private int compare(Object val1, Object val2) {
		if (this.comparator == null) {
			return ((Comparable<E>) val1).compareTo((E) val2);
		} else {
			return this.comparator.compare((E) val1, (E) val2);
		}
	}

	private void swap(Object[] arr, int i, int j) {
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
