package Collections.stack;

import Collections.Iter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 这是一个定长栈的数组实现
 */
public class ArrayStack<T> implements Stack<T>{

    protected Object[] array;
    protected int index;

    public ArrayStack(int size){
        if (size < 0) throw new IllegalArgumentException();
        array = new Object[size];
        index = 0;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public boolean contains(T data) {
        for (Object o : array)
            if (o.equals(data)) return true;
        return false;
    }

    @Override
    public Integer size() {
        return index + 1;
    }

    public T peek() {
        if (index == 0) return null;
        return (T) array[index -1];
    }

    public void push(T object) {
        if (index == array.length) throw new ArrayIndexOutOfBoundsException();
        array[index++] = object;
    }

    public T pop() {
        if (index == 0) throw new ArrayIndexOutOfBoundsException();
        return (T) array[--index];
    }

    @Override
    public Iter<T> getIter() {
        throw new NotImplementedException();
    }
}
