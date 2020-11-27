package com.JTChen.TypeOfData;

/************************************************
 * @description 下压栈，链表实现，支持迭代~
 * @author jtchen
 * @date 2020/11/13
 * @version 2.0
 ************************************************/
public class MyStack<Item> {
    private Node head;//头节点
    private int size;

    public MyStack() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 删除最近添加的字符串
     *
     * @return 所删除串
     */
    public Item pop() {
        if (isEmpty()) throw new StackOverflowError("Stack empty");
        Node tmp = head;
        head = head.next;
        size--;
        return tmp.item;
    }

    /**
     * 增加一个字符串
     *
     * @param item 字符串
     */
    public void push(Item item) {
        Node newHead = new Node(item);
        newHead.next = head;
        head = newHead;
        size++;
    }

    public Item peek() {
        if (isEmpty()) throw new StackOverflowError("Stack empty");
        return head.item;
    }

    /**
     * 判断栈是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 栈中元素的数量
     *
     * @return 栈中元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 清空栈
     */
    public void Empty() {
        head = null;
    }

    /**
     * 私有Node数据结构
     */
    private class Node {
        Node next;
        Item item;

        public Node() {
        }

        public Node(Item item) {
            this.item = item;
        }
    }
}

