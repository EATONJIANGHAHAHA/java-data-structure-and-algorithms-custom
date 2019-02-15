package Collections.stack;

import Collections.Iter;
import Collections.list.Array;

import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {

    Array<T> array = new Array<>();

    class ArrayListStackIter implements Iterator<T> {

        Iterator<T> it;

        public ArrayListStackIter() {
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
    public T peek() {
        return array.get(array.size() - 1);
    }

    @Override
    public void push(T data) {
        array.add(data);
    }

    @Override
    public T pop() {
        return array.remove(array.size() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListStackIter();
    }
}
