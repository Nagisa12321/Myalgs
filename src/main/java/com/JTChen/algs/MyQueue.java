package com.JTChen.algs;

import java.util.NoSuchElementException;
/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/11/05
 */
public class MyQueue {
    private ListNode head, last;

    public MyQueue() {
        this.head = new ListNode();
        this.last = head;
    }

    public void add(int i) {
        ListNode a = new ListNode();
        a.val = i;
        head.next = a;
        head = head.next;
    }

    public int remove() {
        if (isEmpty())
            throw new NoSuchElementException();
        last = last.next;
        return last.val;
    }

    public int peek() {
        return last.val;
    }

    public boolean isEmpty() {
        return head == last;
    }
}

class ListNode {
    public ListNode next;
    public int val;
}