package com.jtchen.typeofdata.table;

import java.util.Comparator;

public abstract class AbstarctSortedTable<K, V> implements SortedTable<K, V> {

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public int size(K lo, K hi) {
        Comparator<K> comparator = comparator();
        if (comparator.compare(lo, hi) == 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }
    

}
