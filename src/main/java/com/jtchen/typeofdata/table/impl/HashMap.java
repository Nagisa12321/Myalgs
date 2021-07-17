package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.SimpleTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/6/20 21:12
 */
public class HashMap<K, V> implements SimpleTable<K, V> {

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
	 * 散列表
	 */
	private Object[] table;

	/**
	 * 哈希表扩容阈值
	 */
	private final float loadFactor;

	/**
	 * 哈希表占据了多少个slot
	 */
	private int occupy;

	/**
	 * 哈希表大小
	 */
	private int size;

	public HashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public HashMap(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	public HashMap(float loadFactor) {
		this(DEFAULT_INITIAL_CAPACITY, loadFactor);
	}

	public HashMap(int capacity, float loadFactor) {
		this.loadFactor = loadFactor;
		this.capacity = capacity;
		this.table = new Object[capacity];
		this.size = 0;
		this.occupy = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(K key, V value) {
		int idx = hash(key);
		if (table[idx] == null) {
			table[idx] = new Node(key, value);
			occupy++;
			size++;
		} else {
			Node cur = (Node) table[idx];
			if (Objects.equals(cur.key, key)) {
				cur.value = value;
			} else {
				while (cur.next != null) {
					if (Objects.equals(cur.next.key, key)) {
						cur.next.value = value;
						return;
					}
					cur = cur.next;
				}
				cur.next = new Node(key, value);
				size++;
			}
		}
		resize();
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		int idx = hash(key);
		if (table[idx] == null) return null;
		else {
			Node cur = (Node) table[idx];
			while (cur != null) {
				if (Objects.equals(cur.key, key))
					return cur.value;
				cur = cur.next;
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(K key) {
		int idx = hash(key);
		if (table[idx] == null) return;
		else {
			Node cur = (Node) table[idx];
			if (Objects.equals(cur.key, key)) {
				table[idx] = cur.next;
				if (table[idx] == null)
					occupy--;
			}
			else {
				while (cur.next != null) {
					if (Objects.equals(cur.next.key, key)) {
						cur.next = cur.next.next;
						return;
					}
					cur = cur.next;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(K key) {
		int idx = hash(key);
		if (table[idx] == null) return false;
		else {
			Node cur = (Node) table[idx];
			while (cur != null) {
				if (Objects.equals(cur.key, key))
					return true;
				cur = cur.next;
			}
			return false;
		}
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
		for (Object obj : table) {
			if (obj == null) continue;
			Node cur = (Node) obj;
			while (cur != null) {
				list.add(cur.key);
				cur = cur.next;
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		List<Node> list = new ArrayList<>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) continue;
			Node cur = (Node) table[i];
			while (cur != null) {
				list.add(cur);
				cur = cur.next;
			}
		}
		return list.toString();
	}

	/**
	 * 哈希表扩容
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		if ((float) occupy / capacity < loadFactor)
			return;
		Object[] oldTab = table;
		capacity <<= 1;
		table = new Object[capacity];
		size = 0;
		occupy = 0;
		for (Object obj : oldTab) {
			if (obj != null) {
				Node cur = (Node) obj;
				while (cur != null) {
					put(cur.key, cur.value);
					cur = cur.next;
				}
			}
		}
		System.out.println("hash map resize to " + capacity);
	}

	/**
	 * 返回一个key的哈希值
	 */
	private int hash(K key) {
		return key == null ? 0 : (key.hashCode() & 0x7fffffff) % capacity;
	}

	/**
	 * 拉链法的节点
	 */
	private class Node {
		private final K key;
		private V value;
		private Node next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public String toString() {
			return key + "=" + value;
		}
	}
}
