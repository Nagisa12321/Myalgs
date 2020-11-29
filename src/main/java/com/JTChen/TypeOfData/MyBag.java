package com.JTChen.TypeOfData;

import java.util.Iterator;

/************************************************
 * @description Bag是一种不支持从中删除元素的数据类型——
 * 他的目的就是帮助用例收集元素，并且迭代遍历所有的元素（用例也可
 * 检查背包是否为空或者背包里元素个数的数量）迭代的顺序不确定，而
 * 且与用例无关
 * @author jtchen
 * @date 2020/11/25 22:36
 * @version 1.0
 ************************************************/
public class MyBag<Item> implements Iterable<Item> {
    private Node head;
    private int size;

    /**
     * 创建一个空的背包
     */
    public MyBag() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 向背包添加一个元素
     *
     * @param item 所添加的元素
     */
    public void add(Item item) {
        if (isEmpty()) head = new Node(item);
        else {
            Node newHead = new Node(item);
            newHead.next = head;
            head = newHead;
        }
        size++;
    }


    /**
     * 判断背包是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 返回背包的大小
     *
     * @return 背包元素个数
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyBagIterator();
    }

    /**
     * 私有Node链表数据结构
     */
    private class Node {
        Node next;
        Item val;

        public Node(Item val) {
            this.val = val;
        }
    }

    private class MyBagIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.val;
            current = current.next;
            return item;
        }
    }

}
