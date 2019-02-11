package Collections.map;

import Collections.Collections;

public interface Map<K, V> extends Collections<V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int size();

    boolean isEmpty();
}