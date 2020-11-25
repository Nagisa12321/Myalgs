package com.JTChen.TypeOfData;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyLinkListTest {
    private Object[] arr = {1, 2, 3, 4, 5, 6};

    @Test
    public void remove() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        a.remove(3);
        System.out.println(a);
        System.out.println(a);
        System.out.println(a.length());
    }

    @Test
    public void removeHead() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        a.removeHead();
        System.out.println(a);
    }

    @Test
    public void removeTail() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        a.removeTail();
        System.out.println(a);
    }

    @Test
    public void length() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        assertEquals(6, a.length());
    }

    @Test
    public void testToString() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        System.out.println(a);
    }

    @Test
    public void get() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        assertEquals(Integer.valueOf(4), a.get(3));
        System.out.println(a);
        System.out.println(a.length());
    }

    @Test
    public void search() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        assertEquals(4, a.search(5));
        assertEquals(-1, a.search(7));
        assertEquals(5, a.search(6));
        System.out.println(a);
        System.out.println(a.length());
    }

    @Test
    public void insert() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        a.insert(3, 123);
        System.out.println(a);
        System.out.println(a.length());

    }

    @Test
    public void insertHead() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        a.insertHead(123);
        System.out.println(a);
    }

    @Test
    public void insertTail() {
        MyLinkList<Integer> a = new MyLinkList(arr);
        a.insertTail(123);
        System.out.println(a);
    }

    public static void main(String[] args) {
        MyLinkList<Integer> a = new MyLinkList<>(123);
//        MyLinkList<Integer> b = new MyLinkList("");
    }
}