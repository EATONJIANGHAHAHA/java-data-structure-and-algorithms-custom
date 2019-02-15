package Collections.Queue;

import Collections.Collections;

public interface Queue<T> extends Collections<T> {

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    void offer(T data);

    void offerAll(Collections<? extends T> items);

    T poll();

    T peek();

}
