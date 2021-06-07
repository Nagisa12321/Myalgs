package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.AbstarctSortedTable;

import java.util.Comparator;

public class BTreeMap<K, V> extends AbstarctSortedTable<K, V> {
	@Override
	public void put(K key, V value) {

	}

	@Override
	public V get(K key) {
		return null;
	}

	@Override
	public void delete(K key) {

	}

	@Override
	public boolean contains(K key) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparator<K> comparator() {
		return null;
	}

	@Override
	public K min() {
		return null;
	}

	@Override
	public K max() {
		return null;
	}

	@Override
	public K floor(K key) {
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

	/**
	 * B-tree 的节点
	 */
	private class BPage {
		/**
		 * 该节点里面的关键字个数
		 */
		private int n;
		/**
		 * 一页中保存的键值对(n对)
		 */
		private BEntry[] entries;
		/**
		 * 一页中保存的孩子Page的数量. (n + 1)
		 */
		private BPage[] children;
		/**
		 * 是否为叶节点.
		 */
		private boolean leaf;
	}

	/**
	 * 键值对
	 */
	private class BEntry {
		/**
		 * 键
		 */
		private K key;
		/**
		 * 值
		 */
		private V value;
	}
}