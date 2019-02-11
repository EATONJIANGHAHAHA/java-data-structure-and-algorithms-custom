package Collections.map;

import Collections.Iter;
import Collections.tree.BinarySearchTree;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{

    BinarySearchTree<K, V> bst;
    int size;

    public BSTMap() {
        bst = new BinarySearchTree<>();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        if (bst.contains(key)) bst.set(key, value);
        else bst.insert(key, value);
        size++;
    }

    @Override
    public V remove(K key) {
        size--;
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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iter<V> getIter() {
        return null;
    }
}
