package DataStructures.Collections.Queue;

import DataStructures.Map.tree.Heap;
import DataStructures.Pair;

import java.util.Iterator;

public class HeapPriorityQueue<T extends Comparable<T>> implements Queue<T> {

    Heap<T, T> heap = new Heap<>();

    private class HPQIterator implements Iterator<T> {

        Iterator<Pair<T, T>> it = heap.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next().getKey();
        }
    }

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
        heap.insert(data, data);
    }

    public void offerAll(Queue<? extends T> items) {
        for (T item : items) {
            offer(item);
        }
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
        return new HPQIterator();
    }
}