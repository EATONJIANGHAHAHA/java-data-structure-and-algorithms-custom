package Collections.Queue;

import Collections.Collections;
import Collections.tree.Heap;
import Collections.tree.Tree;

import java.util.Iterator;

public class HeapPriorityQueue<T extends Comparable<T>> implements Queue<T> {

    Tree<T, T> heap = new Heap<>();

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public boolean contains(T data) {
        return heap.contains(data);
    }

    @Override
    public Integer size() {
        return heap.size();
    }

    @Override
    public void offer(T data) {
        heap.insert(data, null);
    }

    @Override
    public void offerAll(Collections<? extends T> items) {

    }

    @Override
    public T poll() {
        return heap.delete();
    }

    @Override
    public T peek() {
        T result = heap.delete();
        heap.insert(result, null);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
