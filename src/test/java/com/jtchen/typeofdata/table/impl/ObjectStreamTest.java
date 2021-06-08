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
		 * �ýڵ�����Ĺؼ��ָ���
		 */
		private int n;
		/**
		 * һҳ�б���ļ�ֵ��(n��)
		 */
		private BEntry[] entries;
		/**
		 * һҳ�б���ĺ���Page������. (n + 1)
		 */
		private BPage[] children;
		/**
		 * �Ƿ�ΪҶ�ڵ�.
		 */
		private boolean leaf;
	}
	/**
	 * ��ֵ��
	 */
	private static class BEntry {
		/**
		 * ��
		 */
		private int key;
		/**
		 * ֵ
		 */
		private String value;
	}
}
