package Collections.list;

import Collections.Collections;

public interface List<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    int size();

    void add(T data);

    void add(int index, T data);

    void addAll(Collections<? extends T> items);

    boolean set(int index, T newData);

    T get (int index);

    Integer indexOf(T data);

    T remove();

    T remove(int index);
}
