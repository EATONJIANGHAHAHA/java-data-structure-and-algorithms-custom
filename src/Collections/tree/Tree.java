package Collections.tree;

import Collections.Collections;
import Collections.Queue.Queue;
import javafx.util.Pair;
import Collections.Queue.LinkedListQueue;

public interface Tree<I, V> extends Collections<V> {

    boolean isEmpty();

    void insert(I index, V value);

    V delete(I index);

    V delete();

    boolean set(I index, V value);

    V find(I index);

    Queue<Pair<I, V>> breadthFirst();

    Queue<Pair<I, V>> inOrder();

    boolean contains(I index);
}
