package Collections.set;


import Collections.Iter;
import Collections.list.DoubleLinkedList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

public class LinkedListSet<T> implements Set<T> {

    DoubleLinkedList<T> list;

    public LinkedListSet() { list = new DoubleLinkedList<>(); }

    @Override
    public void add(T data) {
        if (!list.contains(data)) list.addFirst(data);
    }

    @Override
    public void remove(T data) {
        list.remove();
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
        throw new NotImplementedException();
    }
}
