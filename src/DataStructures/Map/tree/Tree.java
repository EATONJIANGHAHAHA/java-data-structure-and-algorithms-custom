package DataStructures.Map.tree;

import DataStructures.Collections.Queue.Queue;
import DataStructures.Map.Map;
import DataStructures.Pair;

public interface Tree<I, V> extends Map<I, V> {

    boolean isEmpty();

    void insert(I index);

    void insert(I index, V value);

    void insertAll(Tree<? extends I, ? extends V> items);

    int size();

    V delete(I index);

    V delete();

    boolean set(I index, V value);

    V find(I index);

    Queue<Pair<I, V>> breadthFirst();

    Queue<Pair<I, V>> inOrder();

    boolean contains(I index);
}
