package Collections.list;

import Collections.Collections;

public interface List<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    void add(T data);

    T remove();

    T peek();

    boolean set(T oldData, T newData);

    boolean set(Integer index, T newData);

    T get (Integer index);

    Integer indexOf(T data);

    T remove(T data);
}
