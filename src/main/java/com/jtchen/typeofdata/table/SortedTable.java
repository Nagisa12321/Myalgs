package com.jtchen.typeofdata.table;

import java.util.Comparator;

public interface SortedTable<K, V> extends SimpleTable<K, V> {
    
    /**返回比较器 */
    Comparator<K> comparator();

    /**最小的键 */
    K min();

    /**最大的键 */
    K max();

    /**返回一个key <= 参数key */
    K floor(K key);

    /**大于等于key的最小键 */
    K ceiling(K key);

    /**小于key的键数 */
    int rank(K key);
    
    /**排名为k的键 */
    K select(int k);

    /**删除最小键 */
    void deleteMin();

    /**删除最大键 */
    void deleteMax();

    /**[lo, hi]之间键的数量 */
    int size(K lo, K hi);

    /**[lo, hi]之间的所有键, 已排序 */
    Iterable<K> keys(K lo, K hi);
}
