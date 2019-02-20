package DataStructures.Collections.set;


import DataStructures.Collections.list.DoubleLinkedList;

import java.util.Iterator;

public class LinkedListSet<T> implements Set<T> {

    DoubleLinkedList<T> list;

    class LLSIterator implements Iterator<T> {

        Iterator<T> it = list.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next();
        }
    }

    public LinkedListSet() { list = new DoubleLinkedList<>(); }

    @Override
    public void add(T data) {
        if (!list.contains(data)) list.addFirst(data);
    }

    @Override
    public void addAll(Set<? extends T> items) {
        for (T item : items) add(item);
    }

    @Override
    public void remove(T data) {
        list.remove();
    }

    @Override
    public boolean set(T oldData, T newData) {
        return list.set(oldData, newData);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(T data) {
        return list.contains(data);
    }

    @Override
    public Integer size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new LLSIterator();
    }
}
