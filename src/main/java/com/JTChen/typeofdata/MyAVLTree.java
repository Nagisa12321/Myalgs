package com.JTChen.typeofdata;

import java.util.*;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/13 17:39
 */
public class MyAVLTree<E> extends AbstractCollection<E> {

	private final Comparator<? super E> comparator;

	private Node<E> root = null;

	private int size = 0;

	public MyAVLTree() {
		this.comparator = null;
	}

	public MyAVLTree(Collection<E> collection) {
		this.comparator = null;
		addAll(collection);
	}

	public MyAVLTree(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}

	public MyAVLTree(Collection<E> collection, Comparator<? super E> comparator) {
		this.comparator = comparator;
		addAll(collection);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return searchNode(o) != null;
	}

	private Node<E> searchNode(Object obj) {
		Node<E> cur = this.root;
		while (cur != null) {
			if (compare(cur.val, obj) < 0) {
				cur = cur.right;
			} else if (compare(cur.val, obj) > 0) {
				cur = cur.left;
			} else
				return cur;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private int compare(Object o1, Object o2) {
		return this.comparator == null ? ((Comparable<? super E>) o1).compareTo((E) o2)
				: comparator.compare((E) o1, (E) o2);
	}

	@Override
	public Iterator<E> iterator() {
		return inorder().iterator();
	}

	@Override
	public boolean add(E value) {
		Node<E> newNode = new Node<>(value, 1);
		if (root == null) {
			root = newNode;
		} else {
			Node<E> cur = this.root;
			// do the binary search
			while (true) {
				// just contain this value
				if (compare(cur.val, value) == 0)
					return false;
				// cur.val < value
				else if (compare(cur.val, value) < 0) {
					if (cur.right == null) {
						cur.right = newNode;
						newNode.parent = cur;
						break;
					} else {
						cur = cur.right;
					}
				} else {
					if (cur.left == null) {
						cur.left = newNode;
						newNode.parent = cur;
						break;
					} else {
						cur = cur.left;
					}
				}
			}
			updateHeight(newNode);
			// do the rotate
			rotate(newNode);
		}
		size++;
		return true;
	}

	private void updateHeight(Node<E> node) {
		if (node == null)
			return;
		node.height = height(node);
		updateHeight(node.parent);
	}

	private void rotate(Node<E> node) {
		if (node == null)
			return;
		Node<E> parent = node.parent;
		Node<E> newNode = null;

		// four cases
		if (bf(node) > 1 && bf(node.left) < 0) {
			node.left = leftRotate(node.left);
			newNode = rightRotate(node);
		} else if (bf(node) > 1 && bf(node.left) >= 0) {
			newNode = rightRotate(node);
		} else if (bf(node) < -1 && bf(node.right) > 0) {
			node.right = rightRotate(node.right);
			newNode = leftRotate(node);
		} else if (bf(node) < -1 && bf(node.right) <= 0) {
			newNode = leftRotate(node);
		}

		if (newNode != null) {
			if (parent == null)
				root = newNode;
			else if (node == parent.left) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
			newNode.parent = parent;
			updateHeight(parent);
		}

		rotate(node.parent);
	}

	public int height() {
		return height(this.root);
	}

	private int height(Node<E> node) {
		return node == null ? 0
				: 1 + Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height);
	}

	private Node<E> leftRotate(Node<E> T) {
		Node<E> w = T.right;
		w.parent = T.parent;
		T.parent = w;
		T.right = w.left;
		if (w.left != null)
			w.left.parent = T;
		w.left = T;
		// update the height of T and then w
		T.height = height(T);
		w.height = height(w);

		return w;
	}

	private Node<E> rightRotate(Node<E> T) {
		Node<E> w = T.left;
		w.parent = T.parent;
		T.parent = w;
		T.left = w.right;
		if (w.right != null)
			w.right.parent = T;
		w.right = T;
		// update the height of T and then w
		T.height = height(T);
		w.height = height(w);

		return w;
	}

	private int bf(Node<E> node) {
		return (node.left == null ? 0 : node.left.height) - (node.right == null ? 0 : node.right.height);
	}

	public List<E> inorder() {
		List<E> list = new ArrayList<>();
		inorder(this.root, list);
		return list;
	}

	private void inorder(Node<E> node, List<E> list) {
		if (node == null)
			return;

		inorder(node.left, list);
		list.add(node.val);
		inorder(node.right, list);
	}

	public List<E> preorder() {
		List<E> list = new ArrayList<>();
		preorder(this.root, list);
		return list;
	}

	private void preorder(Node<E> node, List<E> list) {
		if (node == null)
			return;

		list.add(node.val);
		preorder(node.left, list);
		preorder(node.right, list);
	}

	public boolean isHeightBalanced() {
		return isHeightBalanced(this.root);
	}

	private boolean isHeightBalanced(Node<E> node) {
		if (node == null)
			return true;
		if (Math.abs(bf(node)) >= 2)
			return false;
		else
			return isHeightBalanced(node.left) && isHeightBalanced(node.right);
	}

	@Override
	public boolean remove(Object o) {
		Node<E> removeNode = searchNode(o);
		if (removeNode == null)
			return false;
		// just find the node to remove, it is cur.
		Node<E> removeParent = remove(removeNode);
		// rotate up the parent
		rotate(removeParent);
		return true;
	}

	// will return the parent.
	private Node<E> remove(Node<E> removeNode) {
		int children = 0;
		if (removeNode.left != null)
			children++;
		if (removeNode.right != null)
			children++;
		Node<E> parent = removeNode.parent;

		if (children == 0) {
			// have no children
			if (parent == null) {
				// it's the root and have no children
				root = null;
			} else {
				if (removeNode == parent.left)
					parent.left = null;
				else
					parent.right = null;
				updateHeight(parent);
			}
		} else if (children == 1) {
			if (parent == null) {
				// it's the root and have a children
				if (removeNode.left != null)
					root = removeNode.left;
				else
					root = removeNode.right;
			} else {
				if (removeNode == parent.left) {
					if (removeNode.left != null)
						parent.left = removeNode.left;
					else
						parent.left = removeNode.right;
				} else {
					if (removeNode.left != null)
						parent.right = removeNode.left;
					else
						parent.right = removeNode.right;
				}
				if (removeNode.left != null)
					removeNode.left.parent = parent;
				else
					removeNode.right.parent = parent;
				updateHeight(parent);
			}
		} else {
			// find the succssior and replace the remove node
			Node<E> succssior = findMin(removeNode.right);
			if (succssior == removeNode.right) {
				succssior.left = removeNode.left;
				succssior.parent = removeNode.parent;
				updateHeight(succssior);
			} else {
				removeNode.val = succssior.val;
				// and then remove the succssior.
				succssior.parent.left = succssior.right;
				if (succssior.right != null) {
					succssior.right.parent = succssior.parent;
				}
				updateHeight(succssior.parent);
			}
		}
		size--;
		return parent;
	}

	private Node<E> findMin(Node<E> node) {
		Node<E> cur = node;
		while (cur.left != null) {
			cur = cur.left;
		}
		return cur;
	}

	@Override
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	private static class Node<E> {
		public E val;
		public int height;
		public Node<E> left;
		public Node<E> right;
		public Node<E> parent;

		public Node(E val) {
			this.val = val;
		}

		public Node(E val, int height) {
			this.val = val;
			this.height = height;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]").add("val=" + val).toString();
		}
	}

}
