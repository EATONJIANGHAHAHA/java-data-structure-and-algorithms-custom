package Collections.Queue;

import Collections.Iter;
import Collections.list.DoubleLinkedList;
import Collections.list.List;

import java.util.NoSuchElementException;

/**
 * 这是一个循环队列的链表实现
 * @param <T>
 */
public class Queue<T>{

    private DoubleLinkedList<T> list;
    private Iter<T> iter;

    class QueueIter implements Iter<T> {

        Iter<T> listIter;

        public QueueIter () { listIter = list.getIter(); }

        @Override
        public boolean hasNext() { return listIter.hasNext(); }

        @Override
        public T next() { return listIter.next(); }

        @Override
        public T getFirst() { return listIter.getFirst(); }
    }

    public Queue() { list = new DoubleLinkedList<>(); }

    public boolean isEmpty() { return list.isEmpty(); }

    public boolean contains(T data) { return false; }

    public Integer size() { return list.size(); }

    public void add(T data) { list.addFirst(data); }

    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.remove();
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.getLast();
    }

    public Iter<T> getIter() {
        if (iter == null) iter = new QueueIter();
        return iter;
    }
}
