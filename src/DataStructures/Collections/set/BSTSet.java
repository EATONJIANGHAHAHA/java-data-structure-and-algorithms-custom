package DataStructures.Collections.set;

import DataStructures.Map.tree.BinarySearchTree;
import DataStructures.Pair;

import java.util.Iterator;

public class BSTSet<T extends Comparable<T>> implements Set<T> {

    BinarySearchTree<T, Void> bst;
    int size = 0;

    class BSTSetIterator implements Iterator<T> {

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
        bst.insert(data);
        size++;
    }

    @Override
    public void remove(T data) {
        bst.delete(data);
        size--;
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
        return null;
    }
}
