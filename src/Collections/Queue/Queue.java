package Collections.Queue;

import Collections.Collections;

public interface Queue<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    void offer(T data);

    T poll();

    T peek();
}
