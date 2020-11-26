package com.JTChen.TypeOfData;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResizingArrayStackTest {

    @Test
    public void push() {
        ResizingArrayStack<Integer> a = new ResizingArrayStack<>(2);
        a.push(1);
    }

    @Test
    public void pop() {
        ResizingArrayStack<Integer> a = new ResizingArrayStack<>(2);
        a.push(1);
        a.push(2);
        assertEquals(2, a.pop().intValue());
        assertEquals(1, a.pop().intValue());

    }

    @Test
    public void isEmpty() {
        ResizingArrayStack<Integer> a = new ResizingArrayStack<>(2);
        assertTrue(a.isEmpty());
        a.push(1);
        assertFalse(a.isEmpty());
    }

    @Test
    public void size() {
        ResizingArrayStack<Integer> a = new ResizingArrayStack<>(2);
        assertEquals(0, a.size());
        a.push(1);
        assertEquals(1, a.size());
    }
}