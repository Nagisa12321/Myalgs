package com.jtchen.typeofdata.table.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/6/8 17:14
 */
public class ObjectStreamTest {

	public static void main(String[] args) throws FileNotFoundException {

	}

	private static class BPage {
		/**
		 * 该节点里面的关键字个数
		 */
		private int n;
		/**
		 * 一页中保存的键值对(n对)
		 */
		private BEntry[] entries;
		/**
		 * 一页中保存的孩子Page的数量. (n + 1)
		 */
		private BPage[] children;
		/**
		 * 是否为叶节点.
		 */
		private boolean leaf;
	}
	/**
	 * 键值对
	 */
	private static class BEntry {
		/**
		 * 键
		 */
		private int key;
		/**
		 * 值
		 */
		private String value;
	}
}
