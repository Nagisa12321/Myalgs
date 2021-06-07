package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.AbstarctSortedTable;

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
	private final RBTreeNode nil;
	/**
	 * 键比较器
	 */
	private final Comparator<K> comparator;

	/**
	 * 红黑树大小
	 */
	private int size;

	public RBTreeMap() {
		this(null);
	}

	public RBTreeMap(Comparator<K> comparator) {
		this.comparator = comparator;
		this.root = new RBTreeNode(BLACK);
		this.nil = root;
		this.size = 0;
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

		size++;
		assert isRBTree();
	}

	/**
	 * 修补插入操作
	 */
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
		RBTreeNode search = search(key);
		return search == null ? null : search.value;
	}

	/**
	 * 删除一个键值为key的节点
	 */
	@Override
	public void delete(K key) {
		RBTreeNode z = search(key);
		if (z == null) return;

		RBTreeNode x;
		RBTreeNode y = z;
		boolean color = y.color;
		if (z.left == nil) {
			x = z.right;
			transplant(z, z.right);
		} else if (z.right == nil) {
			x = z.left;
			transplant(z, z.left);
		} else {
			y = min(z.right);
			color = y.color;
			x = y.right;
			if (y == z.right) {
				 x.parent = y; // 如果x是nil不至于后面爆空指针异常
			} else {
				transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (color == BLACK)
			fixUpDelete(x);

		size--;
		assert isRBTree();
	}

	/**
	 * 修补删除操作
	 */
	private void fixUpDelete(RBTreeNode x) {
		RBTreeNode w;
		while (x != root && x.color == BLACK) {
			if (x.parent.left == x) {
				w = x.parent.right;
				if (w.color == RED) {
					w.color = BLACK;
					x.parent.color = RED;
					leftRorate(x.parent);
				} else if (w.left.color == BLACK && w.right.color == BLACK) {
					w.color = RED;
					x = x.parent;
				} else {
					if (w.left.color == RED) {
						w.color = RED;
						w.left.color = BLACK;
						rightRorate(w);
						w = x.parent.right;
					}
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.right.color = BLACK;
					leftRorate(x.parent);
					x = root;
				}
			} else {
				w = x.parent.left;
				if (w.color == RED) {
					w.color = BLACK;
					x.parent.color = RED;
					rightRorate(x.parent);
				} else if (w.left.color == BLACK && w.right.color == BLACK) {
					w.color = RED;
					x = x.parent;
				} else {
					if (w.right.color == RED) {
						w.color = RED;
						w.right.color = BLACK;
						leftRorate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					rightRorate(x.parent);
					x  = root;
				}
			}
		}
		x.color = BLACK;
	}

	/**
	 * 返回是否存在key == key的键
	 */
	@Override
	public boolean contains(K key) {
		return search(key) != nil;
	}

	/**
	 * 判断红黑树是否为空
	 */
	@Override
	public boolean isEmpty() {
		return root == nil;
	}

	/**
	 * 返回红黑树大小
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 *
	 * 给出红黑树比较器
	 */
	@Override
	public Comparator<K> comparator() {
		return comparator;
	}

	/**
	 * 找到该红黑树的最小值
	 * 等价于找到该红黑树的最左边节点.
	 */
	@Override
	public K min() {
		RBTreeNode min = min(root);
		return min == nil ? null : min.key;
	}

	/**
	 * 找到一个节点的最小的左边子节点
	 */
	private RBTreeNode min(RBTreeNode node) {
		RBTreeNode cur = node;
		while (true) {
			if (cur.left == nil) return cur;
			cur = cur.left;
		}
	}

	/**
	 * 返回红黑树最大值
	 */
	@Override
	public K max() {
		RBTreeNode max = max(root);
		return max == nil ? null : max.key;
	}

	/**
	 * 找到一个节点最大的子节点
	 */
	private RBTreeNode max(RBTreeNode node) {
		RBTreeNode cur = node;
		while (true) {
			if (cur.right == nil) return cur;
			cur = cur.right;
		}
	}

	/**返回一个key <= 参数key(最大值) */
	@Override
	public K floor(K key) {
		RBTreeNode floor = floor(root, key);
		return floor == nil ? null : floor.key;
	}

	/**返回一个key <= 参数key(最大值) */
	private RBTreeNode floor(RBTreeNode node, K key) {
		if (node == nil) return nil;
		int c = compare(node.key, key);
		if (c == 0) return node;
		else if (c > 0) return floor(node.left, key);
		else {
			RBTreeNode r = floor(node.right, key);
			return r == nil ? node : r;
		}
	}

	/**大于等于key的最小键 */
	@Override
	public K ceiling(K key) {
		return null;
	}

	/**大于等于key的最小键 */
	private RBTreeNode ceiling(RBTreeNode node, K key) {
		if (node == nil) return nil;
		int c = compare(node.key, key);
		if (c == 0) return node;
		else if (c < 0) return ceiling(node.right, key);
		else {
			RBTreeNode l = ceiling(node.left, key);
			return l == nil ? node : l;
		}
	}

	/**小于key的键数 */
	@Override
	public int rank(K key) {
		return rank(root, key);
	}

	/**小于key的键数 */
	private int rank(RBTreeNode node, K key) {
		if (node == nil) return 0;

		int c = compare(node.key, key);
		if (c == 0) return 1 + rank(node.left, key);
		else if (c < 0) return rank(node.left, key);
		return 1 + rank(node.left, key) + rank(node.right, key);
	}

	/**
	 * 找到排名为k的节点值
	 */
	@Override
	public K select(int k) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 通过一个键查找相应的红黑节点, 如果找不到返回null
	 * @return a node who's key == key/null
	 */
	private RBTreeNode search(K key) {
		RBTreeNode cur = this.root;
		while (cur != nil) {
			int c = compare(cur.key, key);
			if (c > 0) cur = cur.left;
			else if (c < 0) cur = cur.right;
			else return cur;
		}
		return null;
	}

	/**
	 * 把v"种植"到u的位置,
	 * 意思就是说用v代替u
	 */
	private void transplant(RBTreeNode u, RBTreeNode v) {
		if (u.parent == nil) root = v;
		else if (u.parent.left == u) u.parent.left = v;
		else u.parent.right = v;
		v.parent = u.parent;
	}

	/**
	 * 返回红黑树的键集合
	 */
	@Override
	public Iterable<K> keys(K lo, K hi) {
		List<K> list = new ArrayList<>();
		keys(root, list, lo, hi);
		return list;
	}

	/**
	 * 递归搜索在范围内的键集合
	 */
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


	@Override
	public String toString() {
		return nodes().toString();
	}

	/**
	 * 层序遍历输出
	 */
	private void showFloor() {
		if (root == nil) {
			System.out.println("[]");
			return;
		}
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
	//--------------------------- Nature test (DEBUG) --------------------------------//
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
