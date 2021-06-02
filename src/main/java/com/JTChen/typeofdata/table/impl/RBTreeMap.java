package com.JTChen.typeofdata.table.impl;

import com.JTChen.typeofdata.table.AbstarctSortedTable;

import java.util.*;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/6/2 13:17
 */
public class RBTreeMap<K, V> extends AbstarctSortedTable<K, V> {
	/**
	 * 红黑树的红色节点属性
	 */
	private static final boolean RED = true;
	/**
	 * 红黑树的黑色节点属性
	 */
	private static final boolean BLACK = false;
	/**
	 * 根节点
	 */
	private RBTreeNode root;
	/**
	 * 共同的叶子节点
	 */
	private RBTreeNode nil;
	/**
	 * 键比较器
	 */
	private final Comparator<K> comparator;

	public RBTreeMap() {
		this(null);
	}

	public RBTreeMap(Comparator<K> comparator) {
		this.comparator = comparator;
		this.root = new RBTreeNode(BLACK);
		this.nil = root;
	}

	/**
	 * 往红黑树中存放一个节点
	 * 并且维持红黑树的红黑特性.
	 */
	@Override
	public void put(K key, V value) {
		RBTreeNode p = this.nil;
		RBTreeNode cur = root;
		while (cur != nil) {
			p = cur;
			int c = compare(key, cur.key);
			if (c < 0) cur = cur.left;
			else if (c > 0) cur = cur.right;
			else {
				cur.value = value;
				return;
			}
		}
		RBTreeNode z = new RBTreeNode(key, value, RED);
		z.parent = p;
		if (p == nil) root = z;
		else if (compare(key, p.key) < 0) p.left = z;
		else p.right = z;

		z.left = nil;
		z.right = nil;
		fixUp(z);

		assert isRBTree();
	}

	private void fixUp(RBTreeNode z) {
		while (z.parent.color == RED) {
			if (z.parent.parent.left == z.parent) {
				RBTreeNode r = z.parent.parent.right;
				// case 1
				if (r.color == RED) {
					z.parent.parent.color = RED;
					z.parent.color = BLACK;
					r.color = BLACK;
					z = z.parent.parent;
				} else {
					// case 2
					if (z == z.parent.right) {
						z = z.parent;
						leftRorate(z);
					}
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					rightRorate(z.parent.parent);
				}
			} else {
				RBTreeNode l = z.parent.parent.left;
				if (l.color == RED) {
					z.parent.color = BLACK;
					l.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.left) {
						z = z.parent;
						rightRorate(z);
					}
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					leftRorate(z.parent.parent);
				}
			}
		}
		root.color = BLACK;
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
		List<K> list = new ArrayList<>();
		keys(root, list, lo, hi);
		return list;
	}

	private void keys(RBTreeNode node, List<K> list, K lo, K hi) {
		if (node == nil) return;
		if (compare(node.key, lo) < 0) {
			keys(node.right, list, lo, hi);
		} else if (compare(node.key, hi) > 0) {
			keys(node.left, list, lo, hi);
		} else {
			list.add(node.key);
			keys(node.left, list, lo, hi);
			keys(node.right, list, lo, hi);
		}
	}

	/**
	 * 打印出红黑树的各种信息
	 * debug用
	 */
	public void show() {
		System.out.println("////////////// black-red tree ///////////////");
		System.out.println("all nodes:");
		System.out.println(nodes());
		System.out.println("all floors:");
		showFloor();
		System.out.println("all relationship:");
		dfsPrint(root);
		System.out.println();
	}


	/**
	 * 层序遍历输出
	 */
	private void showFloor() {
		Queue<RBTreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			List<RBTreeNode> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				RBTreeNode tmp = q.poll();
				list.add(tmp);
				assert tmp != null;
				if (tmp.left != nil) q.offer(tmp.left);
				if (tmp.right != nil) q.offer(tmp.right);
			}
			System.out.println(list);
		}
	}

	/**
	 * 获得该树所有节点(中序遍历)
	 */
	private List<RBTreeNode> nodes() {
		List<RBTreeNode> list = new ArrayList<>();
		dfs(root, list);
		return list;
	}

	/**
	 * dfs加入list
	 */
	private void dfs(RBTreeNode node, List<RBTreeNode> list) {
		if (node == nil) return;

		dfs(node.left, list);
		list.add(node);
		dfs(node.right, list);
	}

	/**
	 * 左旋节点
	 */
	private void leftRorate(RBTreeNode node) {
		RBTreeNode r = node.right;
		node.right = r.left;
		if (r.left != nil) r.left.parent = node;

		r.parent = node.parent;
		if (node.parent == nil) root = r;
		else if (node.parent.left == node) node.parent.left = r;
		else node.parent.right = r;

		r.left = node;
		node.parent = r;
	}

	/**
	 * 右旋节点
	 */
	private void rightRorate(RBTreeNode node) {
		RBTreeNode l = node.left;
		node.left = l.right;
		if (l.right != nil) l.right.parent = node;

		l.parent = node.parent;
		if (node.parent == nil) root = l;
		else if (node.parent.left == node) node.parent.left = l;
		else node.parent.right = l;

		l.right = node;
		node.parent = l;
	}

	/**
	 * 不管有没有比较器, 都可以通过该私有方法进行比较
	 * 如果没有比较器, 将会对k1进行强制转换, 因此可能会抛出异常
	 *
	 * @return 如果k1 < k2, 将返回res < 0, 相等则 = 0, 否则大于0
	 */
	@SuppressWarnings("unchecked")
	private int compare(K k1, K k2) {
		if (comparator == null) {
			return ((Comparable<K>) k1).compareTo(k2);
		}
		return comparator.compare(k1, k2);
	}
	////////////////////////////////////////////////////////////////////////////////////
	/*--------------------------- Nature test (DEBUG) --------------------------------*/
	////////////////////////////////////////////////////////////////////////////////////


	/**
	 * dfs加入list
	 */
	private void dfsPrint(RBTreeNode node) {
		if (node == nil) return;

		dfsPrint(node.left);
		System.out.println(node + " -> " + Arrays.asList(node.left, node.right));
		dfsPrint(node.right);
	}
	/**
	 * 对红黑树四个性质进行检验.
	 * 1. 每个节点要么是红色的, 要么是黑色的
	 * 2. 根节点是黑色的
	 * 3. 每个叶节点(NIL)是黑色的
	 * 4. 如果一个节点是红色的, 那么它的两个子节点是黑色的
	 * 5. 对每个节点. 从该节点到其所有后代的叶节点的简单路径, 包含相同数目的黑色节点
	 * <p>
	 * 性质1, 3不必检查, 因为是初始化就已经确定的
	 *
	 * @return 返回该树是不是红黑树.
	 */
	private boolean isRBTree() {
		// 2. 检查根节点颜色是否为黑色
		if (root.color == RED) return false;

		return isBlackHeightBalance() && isRedWithTwoBlack();
	}

	/**
	 * 检测性质4. 如果一个节点是红色的, 那么它的两个子节点是黑色的
	 */
	private boolean isRedWithTwoBlack() {
		return isRedWithTwoBlack(root);
	}

	/**
	 * 检测性质4. 如果一个节点是红色的, 那么它的两个子节点是黑色的
	 */
	private boolean isRedWithTwoBlack(RBTreeNode node) {
		if (node == nil) return true;

		if (node.color == BLACK)
			return isRedWithTwoBlack(node.left) && isRedWithTwoBlack(node.right);
		else
			return node.left.color == BLACK && node.right.color == BLACK &&
					isRedWithTwoBlack(node.left) && isRedWithTwoBlack(node.right);
	}

	/**
	 * 检测性质5. 对每个节点. 从该节点到其所有后代的叶节点的简单路径, 包含相同数目的黑色节点
	 */
	private boolean isBlackHeightBalance() {
		return isBlackHeightBalance(root);
	}

	/**
	 * 检测性质5. 对每个节点. 从该节点到其所有后代的叶节点的简单路径, 包含相同数目的黑色节点
	 */
	private boolean isBlackHeightBalance(RBTreeNode node) {
		List<RBTreeNode> nodes = new ArrayList<>();
		isBlackHeightBalanceHelper(nodes, node);

		int bh = -1;
		for (RBTreeNode n : nodes) {
			if (bh == -1) bh = BlackHeight(n);
			else {
				int tmp = BlackHeight(n);
				if (tmp != bh) return false;
			}
		}
		return true;
	}

	/**
	 * 一个辅助函数, 进行dfs遍历, 找出left和right都为nil的节点, 加入list中
	 */
	private void isBlackHeightBalanceHelper(List<RBTreeNode> list, RBTreeNode node) {
		if (node == nil) return;

		if (node.right == nil && node.left == nil)
			list.add(node);
		isBlackHeightBalanceHelper(list, node.left);
		isBlackHeightBalanceHelper(list, node.right);
	}

	/**
	 * 递归找出该节点到root经历的黑色节点数
	 */
	private int BlackHeight(RBTreeNode node) {
		if (node == root) return 1;
		int p = BlackHeight(node.parent);
		return node.color == BLACK ? 1 + p : p;
	}


	/**
	 * 红黑树节点
	 */
	private class RBTreeNode {
		/**
		 * 节点键
		 */
		private K key;
		/**
		 * 节点值
		 */
		private V value;
		/**
		 * 节点左孩子
		 */
		private RBTreeNode left;
		/**
		 * 节点右孩子
		 */
		private RBTreeNode right;
		/**
		 * 节点父母
		 */
		private RBTreeNode parent;
		/**
		 * 节点颜色:RED/BLACK
		 */
		private boolean color;

		public RBTreeNode(boolean color) {
			this.color = color;
		}

		public RBTreeNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public RBTreeNode(K key, V value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}

		@Override
		public String toString() {
			return key + "=" + value + ", " +(color ? "□" : "■");
		}
	}
}
