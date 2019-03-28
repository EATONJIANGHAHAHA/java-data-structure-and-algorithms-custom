package com.eaton.dataStructures.Collections.Queue;

import com.eaton.dataStructures.Collections.list.Array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

    Array<T> array;

    class AQIterator implements Iterator<T> {

        Iterator<T> it;

        public AQIterator() {
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
    public void offerAll(Queue<? extends T> items) {
        for (T item : items) offer(item);
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

    @Override
    public Iterator<T> iterator() {
        return new AQIterator();
    }
}
