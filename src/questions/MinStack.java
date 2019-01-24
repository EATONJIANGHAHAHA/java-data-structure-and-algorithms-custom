package questions;

import List.ArrayStack;

/**
 * 有一个栈,怎样做才能在在任何时候返回整个栈的最小值的时间复杂度都为O(1)?
 */
public class MinStack extends ArrayStack {

    private Integer[] minStack;

    public MinStack(int size) {
        super(size);
        minStack = new Integer[size];
    }

    @Override
    public Integer peek() {
        if (index == 0) return null;
        return minStack[index -1];
    }

    @Override
    public void push(Integer object) {
        if (index == array.length) throw new ArrayIndexOutOfBoundsException("This stack is full.");
        minStack[index] = object < peek() ? object : peek();
        array[index++] = object;
    }

    @Override
    public Integer pop() {
        if (index == 0) throw new ArrayIndexOutOfBoundsException("This stack is empty.");
        return minStack[--index];
    }
}
