package com.jtchen.typeofdata.table.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

	BinarySearchTreeMap<String, Integer> b;

	@Before
	public void init() {
		b = new BinarySearchTreeMap<>();
	}

	@Test
	public void testPut() {
		b.put("alice", 100);
		b.put("alice", 100);
		assertEquals(1, b.size());
	}

	@Test
	public void testGet() {
		b.put("bob", 88);
		b.put("bob", 38);
		int i = b.get("bob");
		assertEquals(38, i);
	}

	@Test
	public void testDelete() {
		b.put("clice", 56);
		b.put("alice", 100);
		b.put("bob", 88);
		assertEquals(3, b.size());
		int i = b.get("bob");
		assertEquals(88, i);
		b.delete("bob");
		assertEquals(2, b.size());
	}
	@Test
	public void testMin()
	{
		b.put("clice", 56);
		b.put("alice", 100);
		b.put("bob", 88);
		b.put("cliced", 56);
		b.put("alicea", 100);
		b.put("abob", 88);
		b.put("cdlice", 56);
		b.put("adlice", 100);
		b.put("febob", 88);
		assertEquals("abob", b.min());
	}

	@Test
	public void testPrint()
	{
		b.put("clice", 56);
		b.put("alice", 100);
		b.put("bob", 88);
		b.put("cliced", 56);
		b.put("alicea", 100);
		b.put("abob", 88);
		b.put("cdlice", 56);
		b.put("adlice", 100);
		b.put("febob", 88);
		System.out.println(b);
		System.out.println("----------");
		b.delete("clice");
		System.out.println(b);
	}
	@Test
	public void testRank()
	{
		b.put("clice", 56);
		b.put("alice", 100);
		b.put("bob", 88);
		b.put("cliced", 56);
		b.put("alicea", 100);
		b.put("abob", 88);
		b.put("cdlice", 56);
		b.put("adlice", 100);
		b.put("febob", 88);
		System.out.println(b);
		System.out.println("----------");
		assertEquals(1, b.rank("abob"));
		assertEquals(5, b.rank("bob"));
		assertEquals(9, b.rank("febob"));
		System.out.println(b);
	}
	@Test
	public void testSelect()
	{
		b.put("clice", 56);
		b.put("alice", 100);
		b.put("bob", 88);
		b.put("cliced", 56);
		b.put("alicea", 100);
		b.put("abob", 88);
		b.put("cdlice", 56);
		b.put("adlice", 100);
		b.put("febob", 88);
		System.out.println(b);
		System.out.println("----------");
		assertEquals("abob", b.select(1));
		assertEquals("bob", b.select(5));
		assertEquals("febob", b.select(9));
		System.out.println(b);
	}
	@Test
	public void testFloor()
	{
		b.put("5", 56);
		b.put("6", 100);

		b.put("3", 56);
		b.put("9", 100);
		b.put("7", 88);
		b.put("1", 56);
		b.put("0", 100);
		b.put("2", 88);
		System.out.println(b);
		System.out.println("----------");
		assertEquals("3", b.floor("4"));
		assertEquals("7", b.floor("8"));
		assertEquals("9", b.floor("9"));
		System.out.println(b);
	}
	@Test
	public void testCeiling()
	{
		b.put("5", 56);
		b.put("6", 100);

		b.put("3", 56);
		b.put("9", 100);
		b.put("7", 88);
		b.put("1", 56);
		b.put("0", 100);
		b.put("2", 88);
		System.out.println(b);
		System.out.println("----------");
		assertEquals("5", b.ceiling("4"));
		assertEquals("9", b.ceiling("8"));
		assertEquals("9", b.ceiling("9"));
		System.out.println(b);
	}

}