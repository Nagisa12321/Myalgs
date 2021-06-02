package com.JTChen.typeofdata.table.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.JTChen.typeofdata.table.SimpleTable;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/29 15:30
 */
public class SequentialSearchST<K, V> implements SimpleTable<K, V> {
	private Node first;
	private Node last;
	private int size;

	public SequentialSearchST() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	@Override
	public void put(K key, V value) {
		if (first == null) {
			first = new Node(key, value);
			last = first;
		} else {
			Node cur = this.first;
			while (cur != null) {
				if (cur.key.equals(key)) {
					cur.value = value;
					return;
				}
				cur = cur.next;
			}
			last.next = new Node(key, value);
			last = last.next;
		}
		size++;
	}

	@Override
	public V get(K key) {
		for (Node cur = first; cur != null; cur = cur.next) {
			if (cur.key.equals(key)) {
				return cur.value;
			}
		}
		return null;
	}

	@Override
	public void delete(K key) {
		if (size == 0) return;
		if (first.key.equals(key)) {
			if (size == 1) {
				first = null;
				last = null;
			} else {
				first = first.next;
			}
			size--;
		} else {
			Node cur = first;
			while (cur.next != null) {
				if (cur.next.key.equals(key)) {
					cur.next = cur.next.next;
					size--;
					return;
				}
			}
		}
	}

	@Override
	public boolean contains(K key) {
		for (Node cur = first; cur != null; cur = cur.next) {
			if (cur.key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterable<K> keys() {
		List<K> list = new ArrayList<>();
		for (Node cur = first; cur != null; cur = cur.next) {
			list.add(cur.key);
		}
		return list;
	}

	private class Node {
		public K key;
		public V value;
		public Node next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SequentialSearchST[");
		for (Node cur = first; cur != null; cur = cur.next) {
			builder.append(cur.key).append("=").append(cur.value);
			if (cur.next != null) builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}
}
