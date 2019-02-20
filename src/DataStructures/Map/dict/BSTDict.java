package DataStructures.Map.dict;

import DataStructures.Map.tree.BinarySearchTree;
import DataStructures.Pair;

import java.util.Iterator;

public class BSTDict<K extends Comparable<K>, V> implements Dict<K, V> {

    BinarySearchTree<K, V> bst;

    private class BSTDIterator implements Iterator<Pair<K, V>> {

        Iterator<Pair<K, V>> it = bst.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Pair<K, V> next() {
            return it.next();
        }
    }

    public BSTDict() {
        bst = new BinarySearchTree<>();
    }

    @Override
    public void add(K key, V value) {
        if (bst.contains(key)) bst.set(key, value);
        else bst.insert(key, value);
    }

    @Override
    public void addAll(Dict<? extends K, ? extends V> items) {
        for (Pair<? extends K, ? extends V> pair : items)
            add(pair.getKey(), pair.getValue());
    }

    @Override
    public V remove(K key) {
        return bst.delete(key);
    }

    @Override
    public boolean contains(K key) {
        return bst.contains(key);
    }

    @Override
    public V get(K key) {
        return bst.find(key);
    }

    @Override
    public void set(K key, V newValue) {
        bst.set(key, newValue);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new BSTDIterator();
    }


}
