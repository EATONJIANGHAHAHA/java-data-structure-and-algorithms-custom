package Collections.Queue;

/**
 * 这是一个定向定长循环队列的数组实现
 */
public class ArrayQueue {

    private Integer[] array;
    private int start, end, size;

    public ArrayQueue(int size) {
        if (size < 0) throw new ArrayIndexOutOfBoundsException();
        array = new Integer[size];
        start = 0;
        end = 0;
        this.size = 0;
    }

    public Integer peak() {
        if (size == 0) return null;
        return array[start];
    }

    public void push(Integer item) {
        if (size == array.length) throw new ArrayIndexOutOfBoundsException();
        if (end == array.length - 1) end = 0;
        array[end++] = item;
        size++;
    }

    public Integer poll() {
        if (size == 0) throw new ArrayIndexOutOfBoundsException();
        if (start == array.length - 1) start = 0;
        size--;
        return array[start++];
    }
}
