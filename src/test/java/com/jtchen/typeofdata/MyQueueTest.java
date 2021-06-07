package com.jtchen.typeofdata;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.peek());
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println(queue.size());
    }

    @Test
    public void enqueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        queue.enqueue(null);
        System.out.println(queue);
    }

    @Test
    public void dequeue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        System.out.println(queue);
    }

    @Test
    public void peek() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println(queue.peek());
    }

    @Test
    public void isEmpty() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.isEmpty());

    }

    @Test
    public void size() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println(queue.size());
    }

    @Test
    public void iterator() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        Iterator<Integer> iterator = queue.iterator();
        iterator.remove();
        while (iterator.hasNext()) {
            int i = iterator.next();
            System.out.println(i);
            if (i == 5) {
                iterator.remove();
            }
        }
        for (int i : queue) {
            System.out.println(i);
        }
    }

    @Test
    public void catenation() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        MyQueue<Integer> queue1 = new MyQueue<>();
        queue1.enqueue(4);
        queue1.enqueue(5);
        queue1.enqueue(6);
        System.out.println(queue);
        System.out.println(queue1);
        queue.catenation(queue1);
        System.out.println(queue);
        System.out.println(queue1);
    }

    @Test
    public void contains() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(null);
        System.out.println(queue);
        assertTrue(queue.contains(null));
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));
        assertFalse(queue.contains(4));
        assertFalse(queue.contains("4"));
    }

    @Test
    public void containsAll() {
        MyQueue<Integer> queue1 = new MyQueue<>();
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);
        queue1.enqueue(null);
        MyQueue<Integer> queue2 = new MyQueue<>();
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.enqueue(3);
        assertTrue(queue1.containsAll(queue2));
        assertFalse(queue2.containsAll(queue1));
        assertTrue(queue1.containsAll(queue1));

        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(2);
        list2.add(null);
        System.out.println(list1.containsAll(list2));
        System.out.println(list2.containsAll(list1));
    }

    @Test
    public void toArray() {
        MyQueue<Integer> queue1 = new MyQueue<>();
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);
        queue1.enqueue(null);
        System.out.println(Arrays.toString(queue1.toArray()));
    }
}