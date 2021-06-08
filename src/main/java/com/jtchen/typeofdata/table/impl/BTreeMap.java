package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.AbstarctSortedTable;

import java.util.Comparator;

public class BTreeMap<K, V> extends AbstarctSortedTable<K, V> {

	/**
	 * 默认度数
	 */
	private static final int DEFAULT_DEGREE = 2;

	/**
	 * degree(度)
	 */
	private final int t;

	/**
	 * 常驻内存中的根节点
	 */
	private BPage root;

	/**
	 * 比较器
	 */
	private Comparator<K> comparator;

	public BTreeMap() {
		this(null, DEFAULT_DEGREE);
	}

	public BTreeMap(Comparator<K> comparator) {
		this(comparator, DEFAULT_DEGREE);

	}

	public BTreeMap(int degree) {
		this(null, degree);
	}

	public BTreeMap(Comparator<K> comparator, int degree) {
		this.comparator = comparator;
		this.t = degree;
		// 创建空的root节点
		this.root = new BPage(degree, true);
		diskWrite(root);
	}

	@Override
	public void put(K key, V value) {

	}

	@Override
	public V get(K key) {
		SearchEntry search = search(root, key);
		return search == null ?
				null
				:
				search.bPage.value(search.i);
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
	 * 通过一个BPage(一般是root)和一个key值搜索叶子节点
	 * 如果找不到, 返回null
	 */
	private SearchEntry search(BPage node, K key) {
		int i = 0;
		while (i < node.n && compare(node.key(i), key) < 0) {
			i++;
		}
		if (i < node.n && compare(node.key(i), key) == 0) {
			return new SearchEntry(node, i);
		} else if (node.leaf) {
			return null;
		} else {
			return search(node.children(i), key);
		}
	}

	/**
	 * 祖传泛型比较方法
	 */
	@SuppressWarnings("unchecked")
	private int compare(K k1, K k2) {
		if (comparator != null) {
			return comparator.compare(k1, k2);
		} else {
			return ((Comparable<K>) k1).compareTo(k2);
		}
	}

	/**
	 * 将一个BPage写到磁盘中
	 */
	private void diskWrite(BPage bPage) {
		// do nothing
	}

	private void diskRead(BPage childPage) {
		// do nothing
	}

	/**
	 * B-tree 的节点
	 */
	@SuppressWarnings("unchecked")
	private class BPage {
		/**
		 * 该节点里面的关键字个数
		 */
		private int n;
		/**
		 * 一页中保存的键值对(n对)
		 */
		private Object[] entries;
		/**
		 * 一页中保存的孩子Page的数量. (n + 1)
		 */
		private Object[] children;
		/**
		 * 是否为叶节点.
		 */
		private boolean leaf;

		/**
		 * 返回第i个关键字
		 */
		public K key(int i) {
			return ((BEntry) entries[i]).key;
		}

		/**
		 * 返回第i个关键字对应的值.
		 */
		public V value(int i) {
			return ((BEntry) entries[i]).value;
		}

		/**
		 * 返回第i个孩子节点.
		 */
		public BPage children(int i) {
			return (BPage) children[i];
		}

		/**
		 * 存入键值对
		 */
		public void putEntry(int i, K k, V v) {
			entries[i] = new BEntry(k, v);
		}

		/**
		 * 放入子节点
		 */
		public void putChildren(int i, BPage children) {
			this.children[i] = children;
		}

		public BPage(int degree, boolean leaf) {
			this.leaf = leaf;
			this.n = 0;
			entries = new Object[2 * degree - 1];
			children = new Object[2 * degree];
		}
	}

	/**
	 * 查找(search)返回的结果对
	 */
	private class SearchEntry {
		BPage bPage;
		int i;

		public SearchEntry(BPage bPage, int i) {
			this.bPage = bPage;
			this.i = i;
		}
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

		public BEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}