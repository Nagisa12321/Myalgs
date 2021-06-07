package com.jtchen.typeofdata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RingBufferTest {
	private final RingBuffer<String> buffer = new RingBuffer<>(5);

	@Before
	public void setUp() {
		buffer.enqueue("I");
		buffer.enqueue("love");
		buffer.enqueue("you");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void isEmpty() {
		assertFalse(buffer.isEmpty());
		buffer.free();
		assertTrue(buffer.isEmpty());
	}

	@Test
	public void dequeue() {
		assertEquals("I", buffer.dequeue());
		assertEquals("love", buffer.dequeue());
		assertEquals("you", buffer.dequeue());
		assertNull(buffer.dequeue());
	}

	@Test
	public void free() {
		buffer.free();
		assertTrue(buffer.enqueue("I"));
		assertTrue(buffer.enqueue("love"));
		assertTrue(buffer.enqueue("you"));
		assertTrue(buffer.enqueue("l"));
		assertTrue(buffer.enqueue("yy"));
		assertFalse(buffer.enqueue("false"));
	}

}