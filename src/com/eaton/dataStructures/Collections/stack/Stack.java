package com.eaton.dataStructures.Collections.stack;

import com.eaton.dataStructures.Collections.Collections;

public interface Stack<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    T peek();

    void push(T data);

    void pushAll(Stack<? extends T> items);

    T pop();
}
