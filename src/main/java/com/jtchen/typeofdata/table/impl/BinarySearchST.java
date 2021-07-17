package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.AbstarctSortedTable;

import java.util.Comparator;
import java.util.HashMap;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/29 16:11
 */
@SuppressWarnings("unchecked")
public class BinarySearchST<K, V> extends AbstarctSortedTable<K, V> {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	private Comparator<K> comparator;
	private Object[] keys;
	private Object[] values;
	private int size;

	private BinarySearchST() {
		this(DEFAULT_INITIAL_CAPACITY, null);
	}

	private BinarySearchST(int initialCapacity ) {
		this(initialCapacity, null);
	}

	private BinarySearchST(Comparator<K> comparator) {
		this(DEFAULT_INITIAL_CAPACITY, comparator);
	}

	private BinarySearchST(int initialCapacity, Comparator<K> comparator) {
		if (initialCapacity <= 0) throw new IllegalArgumentException();
		this.comparator = comparator;
		this.size = 0;
		this.keys = new Object[initialCapacity];
		this.values = new Object[initialCapacity];
	}

	@Override
	public void put(K key, V value) {

	}

	@Override
	public V get(K key) {
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < size && compare((K) keys[i], key) == 0) return (V) values[i];
		else return null;
	}

	@Override
	public void delete(K key) {
	}

	@Override
	public boolean contains(K key) {
		if (isEmpty()) return false;
		int i = rank(key);
		if (i < size && compare((K) keys[i], key) == 0) return true;
		else return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Comparator<K> comparator() {
		return comparator;
	}

	@Override
	public K min() {
		if (isEmpty()) return null;
		return (K) keys[0];
	}

	@Override
	public K max() {
		if (isEmpty()) return null;
		return (K) keys[size - 1];
	}

	@Override
	public K floor(K key) {
		if (isEmpty()) return null;
		int i = rank(key);

		return null;
	}

	@Override
	public K ceiling(K key) {
		return null;
	}

	@Override
	public int rank(K key) {
		return 0;
	}

	@Override
	public K select(int k) {
		return null;
	}

	@Override
	public Iterable<K> keys(K lo, K hi) {
		return null;
	}

	@SuppressWarnings("unchecked")
	private int compare(K k1, K k2) {
		if (comparator == null) {
			return ((Comparable<K>) k1).compareTo(k2);
		} else return comparator.compare(k1, k2);
	}

	private void raise() {
		if (keys.length == size) {
			Object[] obj_key = new Object[keys.length * 2];
			Object[] obj_val = new Object[keys.length * 2];
			System.arraycopy(keys, 0, obj_key, 0, obj_key.length);
			System.arraycopy(values, 0, obj_val, 0, obj_val.length);

			this.keys = obj_key;
			this.values = obj_val;
		}
	}
}
