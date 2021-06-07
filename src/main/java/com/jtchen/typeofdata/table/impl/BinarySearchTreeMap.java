package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.AbstarctSortedTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTreeMap<K, V> extends AbstarctSortedTable<K, V> {

	private TreeNode root;

	private final Comparator<K> comparator;

	public BinarySearchTreeMap() {
		this(null);
	}

	public BinarySearchTreeMap(Comparator<K> comparator) {
		this.root = null;
		this.comparator = comparator;
	}

	@Override
	public K ceiling(K key) {
		TreeNode ceiling = ceiling(root, key);
		return ceiling == null ? null : ceiling.key;
	}

	/**
	 * 大于k的最小值
	 */
	private TreeNode ceiling(TreeNode node, K key) {
		if (node == null) return null;
		int c = compare(node.key, key);
		if (c == 0) return node;
			// node.key > key
		else if (c < 0) return ceiling(node.right, key);

		TreeNode l = ceiling(node.left, key);
		return l == null ? node : l;
	}

	@Override
	public Comparator<K> comparator() {
		return comparator;
	}

	/**
	 * 返回一个key <= 参数key(最大)
	 */
	@Override
	public K floor(K key) {
		TreeNode floor = floor(root, key);
		return floor == null ? null : floor.key;
	}

	/**
	 * 以node为根节点, 返回一个TreeNode, 它的key <= 参数key
	 */
	public TreeNode floor(TreeNode node, K key) {
		if (node == null) return null;
		int c = compare(node.key, key);
		if (c == 0) return node;
			// node.key > key
		else if (c > 0) return floor(node.left, key);
		// node.key < key
		TreeNode r = floor(node.right, key);
		// 因为想要最大, 因此有right, 要取right
		return r == null ? node : r;
	}

	@Override
	public Iterable<K> keys(K lo, K hi) {
		List<K> list = new ArrayList<>();
		keys(root, list, lo, hi);
		return list;
	}

	private void keys(TreeNode node, List<K> result, K lo, K hi) {
		if (node != null) {
			if (compare(node.key, lo) < 0) keys(node.right, result, lo, hi);
			else if (compare(node.key, hi) > 0) keys(node.left, result, lo, hi);

			else {
				result.add(node.key);
				keys(node.right, result, lo, hi);
				keys(node.left, result, lo, hi);
			}
		}
	}

	@Override
	public K max() {
		return max(root).key;
	}

	private TreeNode max(TreeNode node) {
		if (node.right == null) return node;
		else return max(node.right);
	}

	@Override
	public K min() {
		return min(root).key;
	}

	private TreeNode min(TreeNode node) {
		if (node.left == null) return node;
		else return min(node.left);
	}

	/**
	 * 找到节点值为key的节点的排名
	 */
	@Override
	public int rank(K key) {
		return rank(root, key);
	}

	private int rank(TreeNode node, K key) {
		if (node == null) return -1;

		int c = compare(node.key, key);
		int leftSize = size(node.left);
		// node.key < key
		if (c > 0) return rank(node.left, key);
		else if (c < 0) return rank(node.right, key) + leftSize + 1;
		else return leftSize + 1;
	}

	/**
	 * 找到排名为k的节点值
	 */
	@Override
	public K select(int k) {
		TreeNode select = select(root, k - 1);
		return select == null ? null : select.key;
	}

	private TreeNode select(TreeNode node, int k) {
		if (node == null) return null;
		int leftSize = size(node.left);

		if (leftSize > k) return select(node.left, k);
		else if (leftSize < k) return select(node.right, k - leftSize - 1);
		else return node;
	}

	@Override
	public boolean contains(K key) {
		return search(key) != null;
	}

	@Override
	public void delete(K key) {
		root = delete(root, key);
	}


	private TreeNode delete(TreeNode node, K key) {
		if (node == null) return null;
		int c = compare(node.key, key);
		// node.key < key
		if (c > 0) {
			node.left = delete(node.left, key);
		} else if (c < 0) {
			node.right = delete(node.right, key);
		} else {
			// 有一个孩子 -> 直接return 孩子
			if (node.left == null) return node.right;
			if (node.right == null) return node.left;
			TreeNode tmp = node;
			node = min(tmp);
			node.right = deleteMin(tmp);
			node.left = tmp.left;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	@Override
	public V get(K key) {
		TreeNode node = search(key);
		return node == null ? null : node.value;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}

	private TreeNode put(TreeNode node, K key, V value) {
		if (node == null) return new TreeNode(key, value, 1);
		else {
			int c = compare(key, node.key);
			if (c < 0) {
				node.left = put(node.left, key, value);
			} else if (c > 0) {
				node.right = put(node.right, key, value);
			} else {
				node.value = value;
			}
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	private int size(TreeNode node) {
		return node == null ? 0 : node.N;
	}

	@Override
	public int size() {
		return size(root);
	}

	private TreeNode search(K key) {
		if (isEmpty()) return null;
		TreeNode cur = root;
		while (cur != null) {
			int c = compare(key, cur.key);
			if (c == 0) {
				return cur;
			} else if (c < 0) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		return null;
	}

	@Override
	public void deleteMax() {
		root = deleteMax(root);
	}

	private TreeNode deleteMax(TreeNode node) {
		if (node == null) return null;
		else if (node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	@Override
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Iterable<TreeNode> nodes() {
		List<TreeNode> list = new ArrayList<>();
		dfs(list, root);
		return list;
	}

	private void dfs(List<TreeNode> list, TreeNode node) {
	    if (node == null) return;

	    dfs(list, node.left);
	    list.add(node);
	    dfs(list, node.right);
    }

	@Override
	public String toString() {
        return nodes().toString();
	}

	private TreeNode deleteMin(TreeNode node) {
		if (node == null) return null;
		else if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	@SuppressWarnings("unchecked")
	private int compare(K k1, K k2) {
		if (comparator == null) {
			return ((Comparable<K>) k1).compareTo(k2);
		} else {
			return comparator.compare(k1, k2);
		}
	}

	private class TreeNode {
		private final K key;
		private V value;

		private TreeNode left;
		private TreeNode right;

		private int N;

		public TreeNode(K key, V val, int n) {
			this.key = key;
			this.value = val;
			this.N = n;
		}

		public String toString() {
		    return key + "=" + value;
        }
	}
}
