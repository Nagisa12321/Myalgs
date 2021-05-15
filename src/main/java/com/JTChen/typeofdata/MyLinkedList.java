package com.JTChen.typeofdata;

import java.util.Arrays;
import java.util.Iterator;

/************************************************
 * @description
 * @author jtchen
 * @date 2020/11/24 0:21
 * @version 1.1
 ************************************************/
public class MyLinkedList<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int length;

    private class Node {
        public Node next;
        public E val;

        public Node() {
        }

        public Node(E val) {
            this.val = val;
        }

    }

    /**
     * 两种初始化方式
     */
    public MyLinkedList() {
        this.length = 0;
        this.head = new Node();
        this.tail = head;
    }

    public MyLinkedList(E val) {
        this.length = 1;
        this.head = new Node(val);
        this.tail = head;
    }

    /**
     * 两种方式创建链表
     *
     * @param item 多个val组成的数组
     */
    public MyLinkedList(E[] item) {
        this.head = new Node(item[0]);
        this.tail = head;
        this.length = item.length;
        Node tmp = head;
        for (int i = 1; i < item.length; ++i) {
            tmp.next = new Node(item[i]);
            tmp = tmp.next;
        }
        tail = tmp;
    }

    public MyLinkedList(String str) {

    }

    /**
     * 可能是一个微不足道的操作，返回（基于 0 的索引），
     *
     * @param i 索引i
     * @return 索引i处的value值
     */
    public E get(int i) {
        if (i >= length) throw new IllegalArgumentException("The provided index is " +
                "longer than the length of the linked list");
        else {
            Node tmp = head;
            for (int j = 0; j < i; ++j)
                tmp = tmp.next;
            return tmp.val;
        }
    }

    /**
     * 确定项目/数据项目v是否存在（并报告其位置
     * 索引）或不存在（通常报告列表中的不存在索引 -1）
     *
     * @param val 数据项
     * @return 返回的index值
     */
    public int search(E val) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (tmp.val == val) return i;
            ++i;
            tmp = tmp.next;
        }
        return -1;
    }

    /**
     * 在索引i之后插入数据val
     *
     * @param i   索引i
     * @param val 要插入的数据val
     */
    public void insert(int i, E val) {
        if (i >= length) throw new IllegalArgumentException("The provided index is " +
                "longer than the length of the linked list");
        else {
            if (i == 0) insertHead(val);
            else if (i == length - 1) insertTail(val);
            else {
                Node tmp = head;
                for (int j = 0; j < i; ++j)
                    tmp = tmp.next;
                Node node = new Node(val);
                node.next = tmp.next;
                tmp.next = node;
                length++;
            }
        }
    }

    /**
     * 头节点插入
     *
     * @param val 插入元素值
     */
    public void insertHead(E val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
        } else {
            Node node = new Node(val);
            node.next = head;
            head = node;
        }
        length++;
    }

    /**
     * 尾节点插入
     *
     * @param val 插入元素值
     */
    public void insertTail(E val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
        } else {
            tail.next = new Node(val);
            tail = tail.next;
        }
        length++;
    }

    /**
     * 删除节点i
     *
     * @param i 要删除的节点的索引值
     */
    public void remove(int i) {
        if (i >= length) throw new IllegalArgumentException("The provided index is " +
                "longer than the length of the linked list");
        else {
            if (i == 0) removeHead();
            else if (i == length - 1) removeTail();
            else {
                Node tmp = head;
                for (int j = 0; j < i; ++j)
                    tmp = tmp.next;
                tmp.val = tmp.next.val;
                Node node = tmp.next;
                tmp.next = tmp.next.next;
                node.next = null;
                --length;
            }
        }
    }

    /**
     * 删除头节点
     */
    public void removeHead() {
        if (head == null) throw new IllegalArgumentException("the head is NULL");
        else {
            Node tmp = head;
            head = head.next;
            tmp.next = null;
            --length;
        }
    }

    /**
     * 删除尾节点
     */
    public void removeTail() {
        if (head == null) throw new IllegalArgumentException("the head is NULL");
        else {
            if (head.next == null) {
                head = null;
                length = 0;
            } else {
                Node node = head;
                while (node.next.next != null)
                    node = node.next;
                node.next = null;
                --length;
            }
        }
    }

    /**
     * @return 返回当前长度
     */
    public int length() {
        return length;
    }

    /**
     * 判断链表是否为空
     *
     * @return 判断空
     */
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        Object[] items = new Object[length];
        Node tmp = head;
        for (int i = 0; i < length; ++i) {
            items[i] = tmp.val;
            tmp = tmp.next;
        }
        return Arrays.toString(items);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E> {
        private Node current;

        public MyLinkedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E e = current.val;
            current = current.next;
            return e;
        }
    }


}
