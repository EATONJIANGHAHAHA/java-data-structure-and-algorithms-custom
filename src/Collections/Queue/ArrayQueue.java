package Collections.Queue;

import Collections.Collections;
import Collections.Iter;
import Collections.list.Array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

    Array<T> array;
    Iter<T> it;

    class ArrayQueueIterator implements Iterator<T> {

        Iterator<T> it;

        public ArrayQueueIterator() {
            it = array.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next();
        }
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    public ArrayQueue(int size) {
        array = new Array<>(size);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public boolean contains(T data) {
        return array.contains(data);
    }

    @Override
    public Integer size() {
        return array.size();
    }

    @Override
    public void offer(T data) {
        array.add(data);
    }

    @Override
    public void offerAll(Collections<? extends T> items) {
        array.addAll(items);
    }

    @Override
    public T poll() {
        if (isEmpty()) throw new NoSuchElementException();
        return array.removeFront();
    }

    @Override
    public T peek() {
        return array.get(0);
    }

    public void offerAll(Queue<T> items) {

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }
}
