package DataStructures.Collections.stack;

import DataStructures.Collections.Collections;

public interface Stack<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    T peek();

    void push(T data);

    T pop();
}
