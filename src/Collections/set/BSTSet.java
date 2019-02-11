package Collections.set;

import Collections.Iter;
import Collections.tree.BinarySearchTree;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BSTSet<T extends Comparable<T>> implements Set<T> {

    BinarySearchTree<T, T> bst;
    int size = 0;

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
    public Iter<T> getIter() {
        throw new NotImplementedException();
    }
}
