package DataStructures.Collections.set;

import DataStructures.Collections.Collections;

public interface Set<T> extends Collections<T> {

    void add(T data);

    void addAll(Set<? extends T> items);

    void remove(T data);

    boolean set(T oldData, T newData);

    boolean isEmpty();

    boolean contains(T data);

    Integer size();
}
