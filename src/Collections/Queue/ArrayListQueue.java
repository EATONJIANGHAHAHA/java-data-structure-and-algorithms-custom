package Collections.Queue;

import Collections.Iter;
import Collections.list.ArrayList;

import java.util.NoSuchElementException;

public class ArrayListQueue<T> implements Queue<T> {

    ArrayList<T> list;
    Iter<T> it;

    class ArrayListQueueIter implements Iter<T> {

        Iter<T> it;

        public ArrayListQueueIter() {
            it = list.getIter();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next();
        }

        @Override
        public T getFirst() {
            return it.getFirst();
        }
    }

    public ArrayListQueue() {
        list = new ArrayList<>();
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
        list.add(data);
    }

    @Override
    public T poll() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.removeFront();
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    @Override
    public Iter<T> getIter() {
        if (it == null) it = new ArrayListQueueIter();
        return it;
    }
}
