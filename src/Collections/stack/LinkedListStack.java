package Collections.stack;

import Collections.Iter;
import Collections.list.LinkedList;
import Collections.list.List;

import java.util.Iterator;
import java.util.Vector;

public class LinkedListStack<T> implements Stack<T>{

    private LinkedList<T> list;

    class StackIter implements Iterator<T> {

        Iterator<T> listIter;

        public StackIter() {
            listIter = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return listIter.hasNext();
        }

        @Override
        public T next() {
            return null;
        }
    }

    public LinkedListStack() {
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

    public void push(T data) {
        list.add(data);
    }

    public T pop() {
        if (isEmpty()) throw new IndexOutOfBoundsException("This stack is empty.");
        return list.remove();
    }

    public Iterator<T> iterator() {
        return null;
    }
}
