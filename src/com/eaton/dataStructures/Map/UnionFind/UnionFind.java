package com.eaton.dataStructures.Map.UnionFind;

import com.eaton.dataStructures.Map.Map;

public interface UnionFind<S extends Integer, K, V> extends Map<S, V> {

    boolean isConnected(int p, int q);

    void unionElements(int eOne, int eTwo);

    int size();

    boolean isEmpty();

    boolean contains(V value);

    void add(S set, V value);

    void add(S set, K key, V value);

    void addAllElements(Map<S, V> items);

    void addMaps(Map<K, V> items);

    boolean set(int key, V newValue);

    Integer find(V value);
}
