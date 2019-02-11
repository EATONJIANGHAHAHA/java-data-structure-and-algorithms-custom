package Collections.set;

import Collections.Collections;

public interface Set<T> extends Collections<T> {

    void add(T data);

    void remove(T data);

    boolean isEmpty();

    boolean contains(T data);

    Integer size();
}
