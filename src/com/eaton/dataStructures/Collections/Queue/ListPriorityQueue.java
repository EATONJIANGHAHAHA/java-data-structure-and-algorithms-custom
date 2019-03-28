package com.eaton.dataStructures.Collections.Queue;

import com.eaton.dataStructures.Collections.list.DoubleLinkedList;

import java.util.Iterator;

public class ListPriorityQueue<T extends Comparable<T>> implements Queue<T> {

    DoubleLinkedList<T> list;

    class LPQIterator implements Iterator<T> {

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

    public ListPriorityQueue() {
        list = new DoubleLinkedList<>();
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
    public void offer(T data) {
        if (isEmpty()) list.add(0, data);
        else {
            int index = getInsertIndex(data);
            if (index == -1) list.addFirst(data);
            else list.add(index, data);
        }
    }

    @Override
    public void offerAll(Queue<? extends T> items) {
        for (T item : items) offer(item);
    }

    private int getInsertIndex(T data) {
        Iterator<T> it = list.iterator(); //反向迭代器
        int index = -1;
        while (it.hasNext() && it.next().compareTo(data) > 0)
            index ++;
        return index;
    }

    @Override
    public T poll() {
        return list.removeFirst();
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    @Override
    public Iterator<T> iterator() {
        return new LPQIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<T> it = list.iterator();
        while (it.hasNext())
            stringBuilder.append(it.next()).append(" ");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ListPriorityQueue<Integer> queue = new ListPriorityQueue<>();
        queue.offer(33);
        queue.offer(34);
        System.out.println(queue);
        queue.offer(12);
        System.out.println(queue);
        queue.offer(110);
        System.out.println(queue);
        queue.offer(31);
        System.out.println(queue);
        queue.offer(1);
        System.out.println(queue + "\n");
        queue.poll();
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue);

        ListPriorityQueue<Integer> queue2 = new ListPriorityQueue<>();
        queue2.offer(345345345);
        queue2.offer(11323);
        queue.offerAll(queue2);
        System.out.println(queue);
    }
}
