package com.JTChen.TypeOfData;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class MyLinkedListTest {
    private Object[] arr = {1, 2, 3, 4, 5, 6};

    @Test
    public void remove() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        a.remove(3);
        System.out.println(a);
        System.out.println(a);
        System.out.println(a.length());
    }

    @Test
    public void removeHead() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        a.removeHead();
        System.out.println(a);
    }

    @Test
    public void removeTail() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        a.removeTail();
        System.out.println(a);
    }

    @Test
    public void length() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        assertEquals(6, a.length());
    }

    @Test
    public void testToString() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        System.out.println(a);
    }

    @Test
    public void get() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        assertEquals(Integer.valueOf(4), a.get(3));
        System.out.println(a);
        System.out.println(a.length());
    }

    @Test
    public void search() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        assertEquals(4, a.search(5));
        assertEquals(-1, a.search(7));
        assertEquals(5, a.search(6));
        System.out.println(a);
        System.out.println(a.length());
    }

    @Test
    public void insert() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        a.insert(3, 123);
        System.out.println(a);
        System.out.println(a.length());

    }

    @Test
    public void insertHead() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        a.insertHead(123);
        System.out.println(a);
    }

    @Test
    public void insertTail() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        a.insertTail(123);
        System.out.println(a);
    }


    @Test
    public void iterator() {
        MyLinkedList<Integer> a = new MyLinkedList(arr);
        for (int i : a)
            System.out.println(i);
    }
}