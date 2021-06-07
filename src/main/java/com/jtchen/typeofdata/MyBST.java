
package com.jtchen.typeofdata;

import com.jtchen.typeofdata.table.SimpleTable;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/27 11:39
 */
public class MyBST<K, V> implements SimpleTable<K, V> {
	
	@Override
	public void put(K key, V value) {

	}

	@Override
	public V get(K key) {
		return null;
	}

	@Override
	public void delete(K key) {

	}

	@Override
	public boolean contains(K key) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Iterable<K> keys() {
		return null;
	}
}
