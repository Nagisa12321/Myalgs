package com.jtchen.typeofdata.table.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashMapTest {
	@Test
	public void testPut() {
		LinearProbingHashMap<Integer, String> map1 = new LinearProbingHashMap<>(1);
		java.util.HashMap<Integer, String> map2 = new java.util.HashMap<>();

		for (int i = 0; i < 100; i++) {
			map1.put(i, String.valueOf(i));
			map2.put(i, String.valueOf(i));
		}

		System.out.println(map1);
		System.out.println(map2);

		System.out.println(map1.size());
		System.out.println(map2.size());
	}

	@Test
	public void testPut1() {
		LinearProbingHashMap<Integer, String> map1 = new LinearProbingHashMap<>();
		java.util.HashMap<Integer, String> map2 = new java.util.HashMap<>();

		for (int i = 0; i < 1000; i++) {
			int rnd = (int) (Math.random() * 100);
			map1.put(rnd, String.valueOf(i));
			map2.put(rnd, String.valueOf(i));
		}

		System.out.println(map1);
		System.out.println(map2);

		System.out.println(map1.size());
		System.out.println(map2.size());

		for (int i = 0; i < 100; i++) {
			int rnd = (int) (Math.random() * 100);
			map1.delete(rnd);
			map2.remove(rnd);
		}

		System.out.println(map1);
		System.out.println(map2);

		System.out.println(map1.size());
		System.out.println(map2.size());

		for (int i = 0; i < 100; i++) {
			String get1 = map1.get(i);
			String get2 = map2.get(i);

			System.out.println("[ " + get1 + ", " + get2 + " ]");
			assertEquals(get1, get2);
		}
	}

	@Test
	public void testGet() {
		LinearProbingHashMap<Integer, String> map1 = new LinearProbingHashMap<>();
		java.util.HashMap<Integer, String> map2 = new java.util.HashMap<>();

		for (int i = 0; i < 100000; i++) {
			int rnd = (int) (Math.random() * 100000);
			map1.put(rnd, String.valueOf(i));
			map2.put(rnd, String.valueOf(i));
		}

		for (int i = 0; i < 100; i++) {
			String get1 = map1.get(i);
			String get2 = map2.get(i);

			System.out.println("[ " + get1 + ", " + get2 + " ]");
			assertEquals(get1, get2);
		}
	}

	@Test
	public void testTime() {
		LinearProbingHashMap<Integer, String> map1 = new LinearProbingHashMap<>();
		java.util.HashMap<Integer, String> map2 = new java.util.HashMap<>();

		long start;
		long end;
		long mytime;
		long javatime;
		int[] randoms = new int[1000000];
		for (int i = 0; i < 1000000; i++) {
			randoms[i] = (int) (Math.random() * 1000000);
		}

		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			map1.put(randoms[i], String.valueOf(i));
		}
		end = System.currentTimeMillis();
		mytime = end - start;
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			map2.put(randoms[i], String.valueOf(i));
		}
		end = System.currentTimeMillis();
		javatime = end - start;
		System.out.println("//////test put (100000)//////");
		System.out.println("my time is " + mytime + "ms.");
		System.out.println("java time is " + javatime + "ms.");
		System.out.println("java is faster than me " + ((double)mytime / javatime));
	}
}