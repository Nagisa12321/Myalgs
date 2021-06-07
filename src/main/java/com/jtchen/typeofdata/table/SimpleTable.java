package com.jtchen.typeofdata.table;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/5/27 11:36
 */
public interface SimpleTable<K, V> {

	void put(K key, V value);

	V get(K key);

	void delete(K key);

	boolean contains(K key);

	boolean isEmpty();

	int size();

	Iterable<K> keys();
}
