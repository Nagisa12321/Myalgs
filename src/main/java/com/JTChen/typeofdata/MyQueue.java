package com.JTChen.typeofdata;

import java.util.Collection;
import java.util.Iterator;

/*******************************
 * @author JT Chen
 * @version 2.1
 * @date 2020/11/05
 ******************************/
public class MyQueue<Item> implements Cloneable, Collection<Item> {

	private Node head, tail;
	private int length;

	/**
	 * 构建MyQueue
	 */
	public MyQueue() {
		this.head = null;
		this.tail = null;
        this.length = 0;
    }

    /**
     * 入队
     *
     * @param i 入队元素
     */
    public void enqueue(Item i) {
        if (isEmpty()) {
            head = new Node(i);
            tail = head;
        } else {
            tail.next = new Node(i);
            tail = tail.next;
        }
        length++;
    }

    /**
     * 出队元素
     *
     * @return 元素值
     */
    @SuppressWarnings("UnusedReturnValue")
    public Item dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("The queue is empty!");
        Item tmp = head.item;
        head = head.next;
        length--;
        return tmp;
    }

    /**
     * 观察队头元素
     *
     * @return 队头元素值
     */
    public Item peek() {
        return head.item;
    }

	/**
	 * 判断是否为空
	 *
	 * @return 是否为空
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * 是否包含元素 o
	 *
	 * @param o 元素o
	 * @return 是否包含？
	 */
	@Override
	public boolean contains(Object o) {
		Iterator<Item> it = iterator();
		if (o == null) {
			while (it.hasNext())
				if (it.next() == null) return true;
		} else
			while (it.hasNext())
				if (o.equals(it.next())) return true;
		return false;
	}


	/**
	 * 返回队列大小
	 *
	 * @return 队列大小
	 */
	public int size() {
		return length;
	}

	/**
	 * 克隆
	 *
	 * @return 克隆后队列
	 */
	@SuppressWarnings("MethodDoesntCallSuperMethod")
	public MyQueue<Item> clone() {
		MyQueue<Item> cloned = new MyQueue<>();
		for (Item i : this)
			cloned.enqueue(i);
		return cloned;
	}

	/**
	 * 可连接的队列
	 *
	 * @param queue 待链接的队列
	 */
	public void catenation(MyQueue<Item> queue) {
		for (Item item : queue) enqueue(item);
	}

	@Override
	public String toString() {
		Node tmp = head;
		StringBuilder builder = new StringBuilder("[");
		while (tmp != null) {
			builder.append(tmp.item);
			if (tmp.next != null) builder.append(", ");
			tmp = tmp.next;
		}
		builder.append("]");
		if (isEmpty()) return "[]";
		else return builder.toString();
	}

	@Override
	public Iterator<Item> iterator() {
		return new MyQueueIterator();
	}

	/**
	 * @return an array, whose {@linkplain Class#getComponentType runtime component
	 * type} is {@code Object}, containing all of the elements in this collection
	 */
	@Override
	public Object[] toArray() {
		Object[] objects = new Object[length];
		if (length == 0) return objects;
		Node tmp = head;
		for (int i = 0; i < length; i++, tmp = tmp.next)
			objects[i] = tmp.item;
		return objects;
	}
    /*public Object[] toArray() {
        Iterator<Item> iterator = iterator();
        Object[] objects = new Object[size()];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = iterator.next();
        }
        return objects;
    }*/

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(Item item) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o)) return false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Item> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}

	private class MyQueueIterator implements Iterator<Item> {
		private Node current = head;
		private Node lastNext;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			lastNext = current;
			current = current.next;
			return item;
		}

		/**
		 * 不太好的remove方法, 时间复杂度为O(n)
		 */
		@Override
		public void remove() {
			if (lastNext == null)
				throw new IllegalStateException();
			else {
				if (lastNext == head) head = head.next;
				else {
					Node tmp = head;
					while (tmp.next != null) {
						if (tmp.next == lastNext) break;
						tmp = tmp.next;
					}
					assert tmp.next != null;
					tmp.next = tmp.next.next;
				}
			}
		}
	}

    /**
     * 私有链表数据结构
     */
    private class Node {
        public Node next;
        public Item item;

        public Node(Item item) {
            this.item = item;
        }
    }


}
