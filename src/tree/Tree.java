package tree;


import javafx.util.Pair;
import list.Queue;

public interface Tree<T> {

    class Node{}

    boolean isEmpty();

    void insert(Integer index, T value);

    T delete(Integer index);

    boolean set(Integer index, T value);

    T find(Integer index);

    Queue<Pair<Integer, T>> breadFirst();
}
