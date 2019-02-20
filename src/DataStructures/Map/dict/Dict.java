package DataStructures.Map.dict;

import DataStructures.Collections.Collections;
import DataStructures.Collections.Queue.Queue;
import javafx.util.Pair;

public interface Dict<K, V> extends Collections<V> {

    void add(K key, V value);

    void addAll(Dict<? extends K, ? extends V> items);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    Queue<Pair<K, V>> getAll();

    void set(K key, V newValue);

    int size();

    boolean isEmpty();
}