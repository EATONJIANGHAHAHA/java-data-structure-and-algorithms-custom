package DataStructures.Map.dict;

import DataStructures.Map.Map;

public interface Dict<K, V> extends Map<K, V> {

    void add(K key, V value);

    void addAll(Dict<? extends K, ? extends V> items);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int size();

    boolean isEmpty();
}