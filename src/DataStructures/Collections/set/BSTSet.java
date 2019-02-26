package DataStructures.Collections.set;

import DataStructures.Map.tree.BinarySearchTree;
import DataStructures.Map.tree.Tree;
import DataStructures.Pair;

import java.util.Iterator;

public class BSTSet<T extends Comparable<T>> implements Set<T> {

    Tree<T, Void> bst;
    int size = 0;

    class BSTSIterator implements Iterator<T> {

        Iterator<Pair<T, Void>> it = bst.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next().getKey();
        }
    }

    public BSTSet () {
        bst = new BinarySearchTree<>();
    }

    @Override
    public void add(T data) {
        bst.insert(data, null);
        size++;
    }

    @Override
    public void addAll(Set<? extends T> items) {
        for (T item : items) add(item);
    }

    @Override
    public void remove(T data) {
        bst.delete(data);
        size--;
    }

    @Override
    public boolean set(T oldData, T newData) {
        if (!bst.contains(oldData)) return false;
        bst.delete(oldData);
        bst.insert(newData, null);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public boolean contains(T data) {
        return bst.contains(data);
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BSTSIterator();
    }
}
