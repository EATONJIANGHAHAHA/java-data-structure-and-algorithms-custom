package Collections.tree;

import Collections.Collections;
import javafx.util.Pair;
import Collections.Queue.LinkedListQueue;

public interface Tree<I, V> extends Collections<V> {

    boolean isEmpty();

    void insert(I index, V value);

    V delete(I index);

    boolean set(I index, V value);

    V find(I index);

    LinkedListQueue<Pair<I, V>> breadFirst();

    boolean contains(I index);
}
