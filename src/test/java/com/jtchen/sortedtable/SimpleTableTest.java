package com.jtchen.sortedtable;

import com.jtchen.typeofdata.table.SimpleTable;
import com.jtchen.typeofdata.table.impl.SequentialSearchST;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/29 15:26
 */
public class SimpleTableTest {

	private SimpleTable<String, Integer> st;
	private String path;

	@Before
	public void init() {
		st = new SequentialSearchST<>();
		path = "src/main/resources/tale.txt";
	}

	@Test
	public void testSimpleTable() {
		st.put("a", 1);
		st.put("b", 2);
		st.put("c", 3);
		st.put("d", 4);
		st.put("e", 5);
		st.put("e", 33);
		System.out.println(st);
		System.out.println(st.size());
		st.delete("a");
		System.out.println(st);
		System.out.println(st.size());
	}


	@Test
	public void testReading() {
		FrequencyCounter frequencyCounter = new FrequencyCounter(st);
		String s = frequencyCounter.calcutate(8, path);
		System.out.println(s + " " + st.get(s));
	}
}
