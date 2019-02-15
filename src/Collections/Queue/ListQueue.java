package Collections.Queue;

import Collections.Collections;
import Collections.Iter;
import Collections.list.DoubleLinkedList;
import Collections.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 这是一个循环队列的链表实现
 * @param <T>
 */
public class LinkedListQueue<T> implements Queue<T>{

    private DoubleLinkedList<T> list;

    class LinkedListQueueIterator implements Iterator<T> {

        Iterator<T> listIter;

        public LinkedListQueueIterator() { listIter = list.iterator(); }

        @Override
        public boolean hasNext() { return listIter.hasNext(); }

        @Override
        public T next() { return listIter.next(); }
    }

    public LinkedListQueue() { list = new DoubleLinkedList<>(); }

    public boolean isEmpty() { return list.isEmpty(); }

    public boolean contains(T data) { return false; }

    public Integer size() { return list.size(); }

    public void offer(T data) { list.add(data); }

    public T poll() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.removeFirst();
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.getLast();
    }

    public void offerAll(Collections<? extends T> items) {
        list.addAll(items);
    }

    public Iterator<T> iterator() {
        return new LinkedListQueueIterator();
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
