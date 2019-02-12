package questions;

import Collections.stack.ArrayStack;

/**
 * 有一个栈,怎样做才能在在任何时候返回整个栈的最小值的时间复杂度都为O(1)?
 */
public class MinStack<T extends Comparable<T>> extends ArrayStack<T> {

    private Object[] minStack;

    public MinStack(int size) {
        super(size);
        minStack = new Integer[size];
    }

    @Override
    public T peek() {
        if (index == 0) return null;
        return (T) minStack[index -1];
    }

    @Override
    public void push(T object) {
        if (index == array.length) throw new ArrayIndexOutOfBoundsException("This stack is full.");
        minStack[index] = object.compareTo(peek()) < 0 ? object : peek();
        array[index++] = object;
    }

    @Override
    public T pop() {
        if (index == 0) throw new ArrayIndexOutOfBoundsException("This stack is empty.");
        return (T) minStack[--index];
    }
}
