package Collections;

public interface Iter<T> {

    boolean hasNext();

    T next();

    T getFirst();
}
