package Collections.tree;

import Collections.Collections;
import javafx.util.Pair;
import Collections.Queue.Queue;

public interface Tree<I, V> extends Collections<V> {

    boolean isEmpty();

    void insert(I index, V value);

    V delete(I index);

    boolean set(I index, V value);

    V find(I index);

    Queue<Pair<I, V>> breadFirst();

    boolean contains(I index);
}
