package Collections.stack;

import Collections.Iter;
import Collections.list.ArrayList;

public class ArrayListStack<T> implements Stack<T> {

    ArrayList<T> list = new ArrayList<>();

    class ArrayListStackIter implements Iter<T> {

        Iter<T> it;

        public ArrayListStackIter() {
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
    public T peek() {
        return list.get(list.size() - 1);
    }

    @Override
    public void push(T data) {
        list.add(data);
    }

    @Override
    public T pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public Iter<T> getIter() {
        return new ArrayListStackIter();
    }
}
