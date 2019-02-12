package Collections.list;

import Collections.Collections;

public interface List<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    int size();

    void add(T data);

    T remove();

    boolean set(int index, T newData);

    T get (int index);

    Integer indexOf(T data);

    T remove(T data);
}
