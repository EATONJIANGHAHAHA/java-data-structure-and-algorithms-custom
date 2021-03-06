package com.eaton.dataStructures.Collections.Queue;

import com.eaton.dataStructures.Collections.list.DoubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 这是一个循环队列的链表实现
 * @param <T>
 */
public class LinkedListQueue<T> implements Queue<T>{

    private DoubleLinkedList<T> list;

    class LQIterator implements Iterator<T> {

        Iterator<T> listIter;

        public LQIterator() { listIter = list.iterator(); }

        @Override
        public boolean hasNext() { return listIter.hasNext(); }

        @Override
        public T next() { return listIter.next(); }
    }

    public LinkedListQueue() { list = new DoubleLinkedList<>(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public boolean contains(T data) { return false; }

    @Override
    public Integer size() { return list.size(); }

    @Override
    public void offer(T data) { list.add(data); }

    @Override
    public T poll() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.getLast();
    }

    @Override
    public void offerAll(Queue<? extends T> items) {
        for (T item : items) offer(item);
    }

    @Override
    public Iterator<T> iterator() {
        return new LQIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T object: list) {
            stringBuilder.append(object.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        queue.offer(73);
        queue.offer(66);
        queue.offer(712);
        queue.offer(9);
        queue.offer(5);
        queue.offer(32);
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);

    }
}
