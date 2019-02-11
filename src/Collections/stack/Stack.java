package Collections.stack;

import Collections.Iter;
import Collections.list.LinkedList;
import Collections.list.List;

public class Stack<T> {

    private LinkedList<T> list;

    class StackIter implements Iter<T> {

        Iter<T> listIter;

        public StackIter() {
            listIter = list.getIter();
        }

        @Override
        public boolean hasNext() {
            return listIter.hasNext();
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public T getFirst() {
            return null;
        }
    }

    public Stack() {
        list = new LinkedList<>();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public boolean contains(T data) {
        return list.contains(data);
    }

    public Integer size() {
        return list.size();
    }

    public T peek() {
        if (isEmpty()) throw new IndexOutOfBoundsException("This stack is empty.");
        return list.get(0);
    }

    public void add(T data) {
        list.add(data);
    }

    public T remove() {
        if (isEmpty()) throw new IndexOutOfBoundsException("This stack is empty.");
        return list.remove();
    }

    public Iter<T> getIter() {
        return null;
    }
}
