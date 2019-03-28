package com.eaton.dataStructures.Map.dict;

import com.eaton.dataStructures.Collections.set.LinkedListSet;
import com.eaton.dataStructures.Collections.set.Set;
import com.eaton.dataStructures.Pair;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SetDict<K, V> implements Dict<K, V> {

    Set<Pair<K, V>> set;

    private class SDIterator implements Iterator<Pair<K, V>> {

        Iterator<Pair<K, V>> it = set.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Pair<K, V> next() {
            return it.next();
        }
    }

    public SetDict() {
        set = new LinkedListSet<>();
    }

    private Pair<K, V> pair(K key) {
        Iterator<Pair<K, V>> it = set.iterator();
        Pair<K, V> current;
        while (it.hasNext()) {
            current = it.next();
            if (current.getKey().equals(key)) return current;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        set.add(new Pair<>(key, value));
    }

    @Override
    public void addAll(Dict<? extends K, ? extends V> items) {
        for (Pair<? extends K, ? extends V> pair : items)
            add(pair.getKey(), pair.getValue());
    }

    @Override
    public V remove(K key) {
        Pair<K, V> result = pair(key);
        if (result != null) {
            set.remove(result);
            return result.getValue();
        }
        else throw new NoSuchElementException();
    }

    @Override
    public boolean contains(K key) {
        return pair(key) != null;
    }

    @Override
    public V get(K key) {
        Pair<K, V> result = pair(key);
        if (result != null) return result.getValue();
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        set.remove(pair(key));
        set.add(new Pair<>(key, newValue));
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new SDIterator();
    }
}
