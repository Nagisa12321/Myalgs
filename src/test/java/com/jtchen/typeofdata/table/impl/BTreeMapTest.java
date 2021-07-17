package com.jtchen.typeofdata.table.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class BTreeMapTest {

	private BTreeMap<Character, String> chsMap;
	private BTreeMap<Integer, String> intMap;

	@Before
	public void init() {
		chsMap = new BTreeMap<>(2, Comparator.comparingInt(x -> x));
		intMap = new BTreeMap<>(2);
	}

	@Test
	public void testPut() {
		char[] chs = {'F', 'S', 'Q', 'K', 'C', 'L',
				'H', 'T', 'V', 'W', 'M', 'R', 'N',
				'P', 'A', 'B', 'X', 'Y', 'D', 'Z', 'E'};

		for (char ch : chs) {
			chsMap.put(ch, "#");
		}

		System.out.println(chsMap);
	}

	@Test
	public void testPutRandom() {
		int[] nums = new int[20000];
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < 20000; i++) {
			nums[i] = (int) (Math.random() * 20000);
		}

		for (int i = 0; i < 20000; i++) {
			intMap.put(nums[i], "$");
			set.add(nums[i]);
		}

		// System.out.println(intMap);
		System.out.println(intMap.size());
		System.out.println(set.size());
	}
	// [19, 6, 14, 2, 14, 8, 11, 2, 3, 11, 1, 12, 2, 17, 6, 9, 10, 15, 1, 18]

	@Test
	public void testDelete() {
		char[] chs = {'F', 'S', 'Q', 'K', 'C', 'L',
				'H', 'T', 'V', 'W', 'M', 'R', 'N',
				'P', 'A', 'B', 'X', 'Y', 'D', 'Z', 'E'};

		for (char ch : chs) {
			chsMap.put(ch, "#");
		}

		System.out.println(chsMap);

		chsMap.delete('K');

		System.out.println(chsMap);
	}

	@Test
	public void testDeleteRandom() {
		int[] nums = new int[3000];
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < 3000; i++) {
			int num = (int) (Math.random() * 3000);
			nums[i] = num;
		}

//		int[] nums = {9, 9, 2, 0, 5, 7, 3, 2, 6, 4};

		// System.out.println(Arrays.toString(nums));

		for (int i = 0; i < 3000; i++) {
			intMap.put(nums[i], "$");
			set.add(nums[i]);
		}

		System.out.println(intMap.size());
		System.out.println(set.size());

		for (int i = 0; i < 1000; i++) {
			int num = (int) (Math.random() * 3000);
			intMap.delete(num);
			set.remove(num);
		}
		System.out.println(set.size());
		System.out.println(intMap.size());

		System.out.println(intMap);
		System.out.println(set);
	}

	@Test
	public void testFindEquals() {
		Random random = new Random(System.currentTimeMillis());
		RBTreeMap<Integer, Integer> m1 = new RBTreeMap<Integer, Integer>();
		NavigableMap<Integer, Integer> m2 = new TreeMap<Integer, Integer>();
		BTreeMap<Integer, Integer> m3 = new BTreeMap<>();

		Set<Integer> set = new HashSet<Integer>();

		while (set.size() < 30000) {
			Integer next = random.nextInt();
			if (!set.contains(next)) {
				set.add(next);
				m1.put(next, next);
				m2.put(next, next);
				m3.put(next, next);
			}
		}
		for (int i = 0; i < 10000; i++) {
			Integer i2 = m2.get(i);
			Integer i1 = m1.get(i);
			Integer i3 = m3.get(i);
			assertEquals(i1, i2);
			assertEquals(i1, i3);
		}
	}

	@Test
	public void testComparePutMap() throws Exception {

		Random random = new Random(System.currentTimeMillis());
		RBTreeMap<Integer, Integer> m1 = new RBTreeMap<Integer, Integer>();
		NavigableMap<Integer, Integer> m2 = new TreeMap<Integer, Integer>();
		BTreeMap<Integer, Integer> m3 = new BTreeMap<>(10);

		Set<Integer> set = new HashSet<Integer>();

		while (set.size() < 1000000) {
			Integer next = random.nextInt();
			set.add(next);
		}

		long t1 = System.currentTimeMillis();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m1.put(next, next);
		}
		long t2 = System.currentTimeMillis();

		long t3 = System.currentTimeMillis();
		it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m2.put(next, next);
		}
		long t4 = System.currentTimeMillis();

		long t5 = System.currentTimeMillis();
		it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m3.put(next, next);
		}
		long t6 = System.currentTimeMillis();

		long b = t6 - t5;
		long rb = t2 - t1;
		long time = t4 - t3;
		System.out.println("b tree time: " + b);
		System.out.println("rb tree time: " + rb);
		System.out.println("time: " + time);
		System.out.println("rb tree time / time = " + (double) (rb) / time);
		System.out.println("b tree time / time = " + (double) (b) / time);
//		System.out.println("For " + set.size() + " elements TreeMap wins IndexedTreeMap in put by:" + ((t2 - t1) - (t4 - t3)) + " milliseconds");
	}

	@Test
	public void testCompareDeleteMap() throws Exception {

		Random random = new Random(System.currentTimeMillis());
		RBTreeMap<Integer, Integer> m1 = new RBTreeMap<Integer, Integer>();
		NavigableMap<Integer, Integer> m2 = new TreeMap<Integer, Integer>();
		BTreeMap<Integer, Integer> m3 = new BTreeMap<>(5);

		Set<Integer> set = new HashSet<Integer>();

		while (set.size() < 3000000) {
			Integer next = random.nextInt();
			if (!set.contains(next)) {
				set.add(next);
				m1.put(next, next);
				m2.put(next, next);
				m3.put(next, next);
			}
		}

		long t1 = System.currentTimeMillis();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m1.delete(next);
		}
		long t2 = System.currentTimeMillis();

		long t3 = System.currentTimeMillis();
		it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m2.remove(next);
		}
		long t4 = System.currentTimeMillis();

		long t5 = System.currentTimeMillis();
		it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m3.delete(next);
		}
		long t6 = System.currentTimeMillis();

		long b = t6 - t5;
		long rb = t2 - t1;
		long time = t4 - t3;
		System.out.println("b tree time: " + b);
		System.out.println("rb tree time: " + rb);
		System.out.println("time: " + time);
		System.out.println("rb tree time / time = " + (double) (rb) / time);
		System.out.println("b tree time / time = " + (double) (b) / time);
	}

	@Test
	public void testFind() {
		Random random = new Random(System.currentTimeMillis());
		RBTreeMap<Integer, Integer> m1 = new RBTreeMap<Integer, Integer>();
		NavigableMap<Integer, Integer> m2 = new TreeMap<Integer, Integer>();
		BTreeMap<Integer, Integer> m3 = new BTreeMap<>(2);

		Set<Integer> set = new HashSet<Integer>();

		while (set.size() < 1000000) {
			Integer next = random.nextInt();
			if (!set.contains(next)) {
				set.add(next);
				m1.put(next, next);
				m2.put(next, next);
				m3.put(next, next);
			}
		}

		long t1 = System.currentTimeMillis();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m1.get(next);
		}
		long t2 = System.currentTimeMillis();

		long t3 = System.currentTimeMillis();
		it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m2.get(next);
		}
		long t4 = System.currentTimeMillis();

		long t5 = System.currentTimeMillis();
		it = set.iterator();
		while (it.hasNext()) {
			Integer next = it.next();
			m3.get(next);
		}
		long t6 = System.currentTimeMillis();

		long b = t6 - t5;
		long rb = t2 - t1;
		long time = t4 - t3;
		System.out.println("b tree time: " + b);
		System.out.println("rb tree time: " + rb);
		System.out.println("time: " + time);
		System.out.println("rb tree time / time = " + (double) (rb) / time);
		System.out.println("b tree time / time = " + (double) (b) / time);
	}
}