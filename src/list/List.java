package list;

public interface List<T> {

    class Node {}

    boolean isEmpty();

    boolean contains(T data);

    Integer size();

    void add(Integer index, T data);

    T remove(Integer index);

    boolean set(T oldData, T newData);

    boolean set(Integer index, T newData);

    T get (Integer index);

    Integer indexOf(T data);
}
