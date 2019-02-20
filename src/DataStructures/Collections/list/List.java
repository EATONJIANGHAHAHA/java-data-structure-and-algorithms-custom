package DataStructures.Collections.list;

import DataStructures.Collections.Collections;
import DataStructures.Collections.Queue.Queue;

public interface List<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    int size();

    void add(T data);

    void add(int index, T data);

    void addAll(List<? extends T> items);

    boolean set(int index, T newData);

    T get (int index);

    Queue<T> getAll();

    Integer indexOf(T data);

    T remove();

    T remove(int index);
}
