package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.SimpleTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/6/21 20:39
 */
public class LinearProbingHashMap<K, V> implements SimpleTable<K, V> {
	/**
	 * 默认初始容量(数组大小)
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

	/**
	 * 哈希表默认扩容阈值
	 */
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * 哈希表当前容量
	 */
	private int capacity;

	/**
	 * 散列表key数组
	 */
	private Object[] keys;

	/**
	 * 散列表value数组
	 */
	private Object[] values;

	/**
	 * 哈希表扩容阈值
	 */
	private final float loadFactor;

	/**
	 * 哈希表大小
	 */
	private int size;

	public LinearProbingHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public LinearProbingHashMap(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	public LinearProbingHashMap(float loadFactor) {
		this(DEFAULT_INITIAL_CAPACITY, loadFactor);
	}

	public LinearProbingHashMap(int capacity, float loadFactor) {
		this.loadFactor = loadFactor;
		this.capacity = capacity;
		this.keys = new Object[capacity];
		this.values = new Object[capacity];
		this.size = 0;
	}

	@Override
	public void put(K key, V value) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
			if (keys[i].equals(key)) {
				values[i] = value;
				return;
			}
		}
		keys[i] = key;
		values[i] = value;
		size++;
		resize();
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
			if (keys[i].equals(key))
				return (V) values[i];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(K key) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
			if (keys[i].equals(key)) {
				keys[i] = null;
				values[i] = null;
				int j = (i + 1) % capacity;
				while (keys[j] != null) {
					Object key1 = keys[j];
					Object value1 = values[j];
					keys[j] = null;
					values[j++] = null;
					size--;
					put((K) key1, (V) value1);
				}
				size--;
				return;
			}
		}
	}

	@Override
	public boolean contains(K key) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
			if (keys[i].equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<K> keys() {
		List<K> list = new ArrayList<>();
		for (int i = 0; i < capacity; i++) {
			if (keys[i] != null)
				list.add((K) keys[i]);
		}
		return list;
	}

	/**
	 * 哈希表扩容
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		if ((float) size / capacity < loadFactor)
			return;
		Object[] oldKeys = keys;
		Object[] oldValues = values;
		capacity <<= 1;
		keys = new Object[capacity];
		values = new Object[capacity];
		size = 0;
		for (int i = 0; i < oldKeys.length; i++) {
			if (oldKeys[i] != null) {
				put((K) oldKeys[i], (V) oldValues[i]);
			}
		}
		System.out.println("Linear hash map resize to " + capacity);
	}

	/**
	 * 返回一个key的哈希值
	 */
	private int hash(K key) {
		return key == null ? 0 : (key.hashCode() & 0x7fffffff) % capacity;
	}

	public String toString() {
		class Node {
			final Object key;
			final Object value;

			Node(Object key, Object val) {
				this.key = key;
				this.value = val;
			}

			public String toString() {
				return key + "=" + value;
			}
		}
		List<Node> list = new ArrayList<>();
		for (int i = 0; i < capacity; i++) {
			if (keys[i] != null)
				list.add(new Node(keys[i], values[i]));
		}
		return list.toString();
	}
}
