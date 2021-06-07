package com.jtchen.typeofdata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BufferTest {
    private final Buffer buffer = new Buffer();

    @Test
    @Before
    public void insert() {
        String s = "I love you, mrs Lin~";
        for (int i = 0; i < s.length(); i++) {
            buffer.insert(s.charAt(i));
        }
    }


    @Test
    public void operating() {
        buffer.right(123);
        buffer.left(5);
        assertEquals('s', buffer.delete());
        assertEquals("I love you, mr Lin~", buffer.toString());
        buffer.right(2);
        buffer.delete();
        assertEquals("I love you, mr in~", buffer.toString());
        assertEquals(18, buffer.size());
        buffer.left(132);
    }

    @Test
    public void size() {
        assertEquals(20, buffer.size());
    }

    @Test
    public void testToString() {
        assertEquals("I love you, mrs Lin~", buffer.toString());
    }
}