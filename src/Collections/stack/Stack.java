package Collections.stack;

import Collections.Collections;

public interface Stack<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    T peek();

    void push(T data);

    T pop();
}
