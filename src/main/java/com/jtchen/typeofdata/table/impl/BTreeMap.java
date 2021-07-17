package com.jtchen.typeofdata.table.impl;

import com.jtchen.typeofdata.table.AbstarctSortedTable;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unused")
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
	 * btree 大小
	 */
	private int size;

	/**
	 * 比较器
	 */
	private final Comparator<K> comparator;

	public BTreeMap() {
		this(DEFAULT_DEGREE, null);
	}

	public BTreeMap(int degree) {
		this(degree, null);
	}

	public BTreeMap(Comparator<K> comparator) {
		this(DEFAULT_DEGREE, comparator);

	}

	public BTreeMap(int degree, Comparator<K> comparator) {
		this.comparator = comparator;
		this.t = degree;
		// 创建空的root节点
		this.root = new BPage(degree, true);
		diskWrite(root);
	}

	/**
	 * 往btree中存入節點
	 */
	@Override
	public void put(K key, V value) {
		BPage r = this.root;
		// if the root is full.
		if (r.n == 2 * t - 1) {
			BPage s = new BPage(t, false, 0);
			s.children[0] = r;
			root = s;
			splitChild(s, 0);
		}
		insertNonFull(root, key, value);

		assert isBTree();

	}

	/**
	 * 在一个不是满的节点上执行插入操作.
	 */
	private void insertNonFull(BPage x, K key, V value) {
		// is it exist?
		for (int i = 0; i < x.n; i++) {
			if (compare(key, x.key(i)) == 0) {
				x.setValue(i, value);
				return;
			}
		}

		if (x.leaf) {
			int i;
			for (i = x.n; i > 0; i--) {
				if (compare(x.key(i - 1), key) > 0) {
					x.entries[i] = x.entries[i - 1];
				} else break;
			}
			x.entries[i] = new BEntry(key, value);
			x.n++;
			size++;
			diskWrite(x);
		} else {
			int i;
			for (i = 0; i < x.n; i++) {
				if (compare(x.key(i), key) > 0)
					break;
			}
			diskRead(x.children(i));
			BPage c = x.children(i);
			// if the children is full
			if (c.n == 2 * t - 1) {
				splitChild(x, i);
				int compare = compare(key, x.key(i));
				if (compare == 0) {
					x.setValue(i, value);
					return;
				} else if (compare > 0)
					i++;
			}
			// ehhh..(有待思考 @_<)
			diskRead(x.children(i));
			insertNonFull(x.children(i), key, value);
		}
	}

	/**
	 * 通過key獲得value
	 */
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
		delete(root, key);

		fixDelete();
		assert isBTree();
	}

	/**
	 * 是否存在相應的鍵
	 */
	@Override
	public boolean contains(K key) {
		return search(root, key) != null;
	}

	@Override
	public boolean isEmpty() {
		return root.n == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Comparator<K> comparator() {
		return comparator;
	}

	/**
	 * 找到当前map中的最小值
	 */
	@Override
	public K min() {
		BPage min = min(root);
		return min.n == 0 ? null : min.key(0);
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
	 * 层序遍历toString() - -
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Queue<BPage> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				BPage page = queue.poll();
				builder.append(page).append(' ');

				assert page != null;

				if (!page.leaf)
					for (int j = 0; j <= page.n; j++) {
						BPage c = page.children(j);
						diskRead(c);
						queue.add(c);
					}
			}
			builder.append('\n');
		}
		return builder.toString();
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

	/***
	 * 找到以当前页面为root的最小值
	 */
	private BPage min(BPage page) {
		BPage cur = page;
		while (!cur.leaf) {
			diskRead(cur.children(0));
			cur = cur.children(0);
		}
		return cur;
	}

	/***
	 * 找到以当前页面为root的最大值
	 */
	private BPage max(BPage page) {
		BPage cur = page;
		while (!cur.leaf) {
			diskRead(cur.children(cur.n));
			cur = cur.children(cur.n);
		}
		return cur;
	}

	/**
	 * 对节点的下标为i的孩子节点进行分裂.
	 */
	private void splitChild(BPage x, int i) {
		BPage y = x.children(i);
		BPage z = new BPage(t, y.leaf, t - 1);

		System.arraycopy(y.entries, t, z.entries, 0, t - 1);
		if (!y.leaf) {
			System.arraycopy(y.children, t, z.children, 0, t);
		}
		y.n = t - 1;
		System.arraycopy(x.children, i, x.children, i + 1, x.n + 1 - i);
		System.arraycopy(x.entries, i, x.entries, i + 1, x.n - i);
		x.children[i + 1] = z;
		x.entries[i] = y.entries[t - 1];
		x.n++;

		for (int j = t - 1; j < 2 * t - 1; j++) {
			y.entries[j] = null;
			y.children[j + 1] = null;
		}


		diskWrite(x);
		diskWrite(y);
		diskWrite(z);
	}

	/**
	 * 递归删除
	 */
	private void delete(BPage page, K k) {
		int i;
		for (i = 0; i < page.n; i++) {
			if (compare(page.key(i), k) >= 0)
				break;
		}

		if (i != page.n && compare(page.key(i), k) == 0) {
			if (page.leaf) {

				for (int j = i; j < page.n - 1; j++) {
					page.entries[j] = page.entries[j + 1];
				}
				page.n--;
				size--;
				page.entries[page.n] = null;
			} else {
				BPage y = page.children(i);
				if (y.n >= t) {
					BPage cur = y;
					while (!cur.leaf) {
						cur = cur.children(cur.n);
					}
					page.entries[i] = cur.entries[cur.n - 1];
					K tmpK = cur.key(cur.n - 1);
					delete(y, tmpK);
				} else {
					BPage z = page.children(i + 1);
					if (z.n >= t) {
						BPage cur = z;
						while (!cur.leaf) {
							cur = cur.children(0);
						}
						page.entries[i] = cur.entries[0];
						K tmpK = cur.key(0);
						delete(z, tmpK);
					} else {
						// copy z, k to y
//						y.entries[t] = page.entries[i];
//
//						for (int j = 0; j < t; j++) {
//							y.children[j + t] = z.children[j];
//							if (j != t - 1)
//								y.entries[j + t] = z.entries[j];
//						}
//
//						for (int j = i; j < page.n; j++)
						merge(page, i, y, z);
						delete(y, k);
					}
				}
			}
		} else {
			if (page.leaf) return;
			BPage child = page.children(i);
			if (child.n == t - 1)
				if (i != 0 && page.children(i - 1).n >= t) {
					BPage leftChild = page.children(i - 1);
					BPage tmpChild = leftChild.children(leftChild.n);
					Object tmpEntry = page.entries[i - 1];
					page.entries[i - 1] = leftChild.entries[leftChild.n - 1];

					for (int j = child.n + 1; j > 0; --j) {
						if (j != child.n + 1) {
							child.entries[j] = child.entries[j - 1];
						}
						child.children[j] = child.children[j - 1];
					}
					child.children[0] = tmpChild;
					child.entries[0] = tmpEntry;
					child.n++;
					leftChild.n--;
					leftChild.children[leftChild.n + 1] = null;
					leftChild.entries[leftChild.n] = null;
					delete(child, k);
				} else if (i != page.n && page.children(i + 1).n >= t) {
					BPage rightChild = page.children(i + 1);
					BPage tmpChild = rightChild.children(0);
					Object tmpEntry = page.entries[i];
					page.entries[i] = rightChild.entries[0];

					for (int j = 0; j < rightChild.n; j++) {
						if (j != rightChild.n - 1)
							rightChild.entries[j] = rightChild.entries[j + 1];
						rightChild.children[j] = rightChild.children[j + 1];
					}
					child.entries[t - 1] = tmpEntry;
					child.children[t] = tmpChild;
					child.n++;
					rightChild.n--;
					rightChild.entries[rightChild.n] = null;
					rightChild.children[rightChild.n + 1] = null;
					delete(child, k);
				} else {

					if (i != 0) {
						BPage leftChild = page.children(i - 1);
						merge(page, i - 1, leftChild, child);
						delete(leftChild, k);
					} else {
						BPage rightChild = page.children(i + 1);
						merge(page, i, child, rightChild);
						delete(child, k);
					}
				}
			else delete(child, k);
		}
	}

	/**
	 * 如果树根变成空的, 树的高度要减少
	 */
	private void fixDelete() {
		if (!root.leaf && root.n == 0) {
			root = root.children(0);
		}
	}

	/**
	 * 将右边节点合并到左边
	 */
	private void merge(BPage page, int i, BPage left, BPage right) {
		left.entries[left.n] = page.entries[i];
		for (int j = 0; j < t; j++) {
			if (j != t - 1)
				left.entries[j + t] = right.entries[j];
			left.children[j + t] = right.children[j];
		}
		// release the child
		// child = null;

		for (int j = i; j < page.n; j++) {
			if (j != i)
				page.children[j] = page.children[j + 1];
			if (j != page.n - 1)
				page.entries[j] = page.entries[j + 1];
		}
		left.n = 2 * t - 1;
		page.n--;
		page.children[page.n + 1] = null;
		page.entries[page.n] = null;
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

	///////////////////////////////////////////////////////////////
	//////////////////////// DEBUG function ///////////////////////
	///////////////////////////////////////////////////////////////

	private boolean isBTree() {
		if (isEmpty()) return true;
		// 检查1.2: key值非降序存放
		if (!isKeyNonDescending(root)) return false;
		// 检查性质3: `k(1) <= x.key(1) <= key(2) <= x.key(2) <= .... <= x.key(n) <= k(x.n + 1)`
		if (!isComplianceRange(root)) return false;
		// 檢查性質4: 樹木的深度都爲h
		if (!isSameHeight()) return false;
		// 檢查性質5: t - 1 <= n <= 2t - 1
		return isWithinRange(root);
	}

	/**
	 * 递归检查key是否非降序存放
	 */
	private boolean isKeyNonDescending(BPage page) {
		for (int i = 1; i < page.n; i++) {
			// keyi > keyi - 1
			if (compare(page.key(i), page.key(i - 1)) < 0)
				return false;
		}
		boolean res = true;
		if (!page.leaf) {
			for (int i = 0; i <= page.n; i++) {
				res = res && isKeyNonDescending(page.children(i));
			}
		}
		return res;
	}

	/**
	 * 检查是否符合范围.
	 */
	private boolean isComplianceRange(BPage page) {
		if (page.leaf) return true;

		// 判断两侧
		BPage right = min(page.children(page.n));
		if (compare(right.key(0), page.key(page.n - 1)) < 0) return false;
		BPage left = max(page.children(0));
		if (compare(left.key(left.n - 1), page.key(0)) > 0) return false;

		for (int i = 0; i < page.n - 1; i++) {
			K pMin = page.key(i);
			K pMax = page.key(i + 1);
			K min = min(page.children(i + 1)).key(0);
			BPage maxPage = max(page.children(i + 1));
			K max = maxPage.key(maxPage.n - 1);

			if (compare(pMin, min) > 0) return false;
			if (compare(pMax, max) < 0) return false;
		}

		boolean res = true;
		for (int i = 0; i <= page.n; i++) {
			res = res && isComplianceRange(page.children(i));
		}
		return res;
	}

	/**
	 * 檢查每個leaf節點是否有相同的高度
	 */
	private boolean isSameHeight() {
		int height = getHeight(root);
		return isSameHeight(root, 1, height);
	}

	/**
	 * 上一個函數的輔助函數
	 */
	private boolean isSameHeight(BPage page, int nowHeight, int height) {
		if (page.leaf) {
			return nowHeight == height;
		} else {
			boolean res = true;
			for (int i = 0; i <= page.n; i++) {
				res = res && isSameHeight(page.children(i), 1 + nowHeight, height);
			}
			return res;
		}
	}

	/**
	 * 得到min節點深度
	 */
	private int getHeight(BPage page) {
		if (page.leaf) return 1;
		else {
			return 1 + getHeight(page.children(0));
		}
	}

	/**
	 * 檢查性質5
	 */
	private boolean isWithinRange(BPage page) {
		if (page.n < t - 1 || page.n > 2 * t - 1)
			return false;
		boolean res = true;
		if (!page.leaf)
			for (int i = 0; i <= page.n; i++) {
				res = res && isWithinRange(page.children(i));
			}
		return res;
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
		private final Object[] entries;
		/**
		 * 一页中保存的孩子Page的数量. (n + 1)
		 */
		private final Object[] children;
		/**
		 * 是否为叶节点.
		 */
		private final boolean leaf;

		public BPage(int degree, boolean leaf) {
			this(degree, leaf, 0);
		}

		public BPage(int degree, boolean leaf, int n) {
			this.leaf = leaf;
			this.n = n;
			entries = new Object[2 * degree - 1];
			children = new Object[2 * degree];
		}

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
		 * 设置关键字value(针对put找到相同关键字)
		 * isn't it SHIT CODE ? :-(
		 */
		public void setValue(int i, V value) {
			((BEntry) (entries[i])).value = value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append('[');
			for (int i = 0; i < n; i++) {
				builder.append(entries[i]);
				if (i == n - 1) builder.append(']');
				else builder.append(", ");
			}
			return builder.toString();
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
		private final K key;
		/**
		 * 值
		 */
		private V value;

		public BEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
}