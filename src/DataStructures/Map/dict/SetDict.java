package DataStructures.Map.dict;

import DataStructures.Collections.set.LinkedListSet;
import DataStructures.Collections.set.Set;

public class SetDict<K extends Comparable<K>, V> implements Dict<K, V> {

    Set<K> keySet = new LinkedListSet<>();
    Set<V> valueSet = new LinkedListSet<>();

    @Override
    public void add(K key, V value) {
        keySet.add(key);
        valueSet.add(value);
    }

    @Override
    public V remove(K key) {

    }

    @Override
    public boolean contains(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K key, V newValue) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
