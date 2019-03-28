package com.eaton.dataStructures.Map.tree;

import com.eaton.dataStructures.Collections.Queue.Queue;
import com.eaton.dataStructures.Map.Map;
import com.eaton.dataStructures.Pair;

public interface Tree<I, V> extends Map<I, V> {

    boolean isEmpty();

    void insert(I index);

    void insert(I index, V value);

    void insertAll(Tree<? extends I, ? extends V> items);

    int size();

    V delete(I index);

    V delete();

    I deleteARI();

    boolean set(I index, V value);

    V find(I index);

    Queue<Pair<I, V>> breadthFirst();

    Queue<Pair<I, V>> inOrder();

    boolean contains(I index);
}
