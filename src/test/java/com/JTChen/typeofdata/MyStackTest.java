package com.JTChen.typeofdata;

import org.junit.Test;

import static org.junit.Assert.*;


public class MyStackTest {

    @Test
    public void pop() {
        MyStack<Integer> a = new MyStack<>();
        a.push(1);
        a.push(2);
        assertEquals(2, a.pop().intValue());
        assertEquals(1, a.pop().intValue());
    }

    @Test
    public void push() {
        MyStack<Integer> a = new MyStack<>();
        a.push(1);
    }

    @Test
    public void peek() {
        MyStack<Integer> a = new MyStack<>();
        a.push(1);
        a.push(2);
        assertEquals(2, a.peek().intValue());
    }

    @Test
    public void isEmpty() {
        MyStack<Integer> a = new MyStack<>();
        assertTrue(a.isEmpty());
        a.push(1);
        assertFalse(a.isEmpty());

    }

    @Test
    public void size() {
        MyStack<Integer> a = new MyStack<>();
        assertEquals(0, a.size());
        a.push(1);
        assertEquals(1, a.size());
    }

    @Test
    public void empty() {
        MyStack<Integer> a = new MyStack<>();
        a.push(1);
        a.push(1);
        a.push(1);
        a.push(1);
        a.Empty();
        assertTrue(a.isEmpty());
    }

    @Test
    public void iterator() {
        MyStack<Integer> a = new MyStack<>();
        System.out.println("test1:");
        for (int i : a) System.out.println(i);
        System.out.println("test2:");
        a.push(1);
        a.push(2);
        a.push(3);
        a.push(4);
        for (int i : a) System.out.println(i);

    }
}