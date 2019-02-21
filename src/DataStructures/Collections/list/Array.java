package DataStructures.Collections.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 动态数组.
 * @param <T>
 */
public class Array<T> implements List<T> {

    private T[] objects = (T[]) new Object[5];
    private int tail, head, size;
    //tail: 下一个要插入的元素索引, head: 下一个要删除的元素索引, size: 结构大小

    class ArrayIterator implements Iterator<T> {

        int index;

        public ArrayIterator() {
            index = head - 1;
        }

        @Override
        public boolean hasNext() {
            if (tail == 0) return index != 0;
            return index != tail - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            index = index == objects.length - 1 ? 0 : index + 1;
            return (T) objects[index];
        }
    }

    public Array() {
        new Array(10);
    }

    public Array(int size) {
        objects = (T[]) new Object[size + 5];
        tail = 0;
        this.size = 0;
        head = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T data) {
        return indexOf(data) != null;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == 0) return;
        if (size < objects.length - 2) expendCapacity();
    }

    private void expendCapacity() {
        Object[] newArray = new Object[objects.length * 2];
        Iterator<T> it = new ArrayIterator();
        for (int i = 0; it.hasNext(); i ++)
            newArray[i] = it.next();
        tail = size;
        head = 0;
        objects = (T[]) newArray;
    }

    private boolean checkIndex(int index) {
        return index < size;
    }

    /**
     * 获取内部数组可以使用的绝对下标.
     *
     * @param index
     * @return
     */
    private int getInnerIndex(int index) {
        index += head;
        if (index >= objects.length) index -= objects.length;
        return index;
    }

    /**
     * 获取待拷贝数组的长度.
     *
     * @param current 保证此参数是内部数组的绝对下标.
     * @return
     */
    private int getCopyArrayLength(int current) {
        if (tail < head && current > head)
            return objects.length - Math.abs(tail - current);
        else return tail - current;
    }

    /**
     * 向表尾添加元素
     *
     * @param data
     */
    @Override
    public void add(T data) {
        ensureCapacity();
        if (tail == objects.length) tail = 0;
        objects[tail++] = data;
        size++;
    }

    /**
     * 向表头添加元素
     *
     * @param data
     */
    public void addFront(T data) {
        ensureCapacity();
        head = (head == 0) ? objects.length - 1 : head - 1;
        objects[head] = data;
        size++;
    }

    /**
     * 向表中index元素后添加一个元素
     *
     * @param data
     * @param index
     */
    @Override
    public void add(int index, T data) {
        if (!checkIndex(index)) throw new ArrayIndexOutOfBoundsException();
        ensureCapacity();
        int current = getInnerIndex(index) + 1;
        pushPartsOnec(current);
        objects[current] = data;
        size++;
    }

    @Override
    public void addAll(List<? extends T> items) {
        for (T item : items) add(item);
    }

    private void pushPartsOnec(int current) {
        int pointer = tail;
        while (pointer != current) {
            objects[pointer] = pointer == 0 ? objects[objects.length - 1] : objects[pointer - 1];
            pointer = pointer == 0 ? objects.length - 1 : pointer - 1;
        }
        tail = tail == objects.length - 1 ? 0 : tail + 1;
    }

    /**
     * 移除最后一个元素
     *
     * @return
     */
    @Override
    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;
        return (T) objects[--tail];
    }

    /**
     * 移除第一个元素
     *
     * @return
     */
    public T removeFront() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;
        return (T) objects[head++];
    }

    @Override
    public T remove(int index) {
        if (!checkIndex(index)) throw new ArrayIndexOutOfBoundsException();
        int current = getInnerIndex(index);
        T data = (T) objects[current];
        pullPartsOnce(current);
        size--;
        return data;
    }

    private void pullPartsOnce(int current) {
        int pointer = current;
        while (pointer != tail) {
            objects[pointer] = pointer == objects.length - 1 ? objects[0] : objects[pointer + 1];
            pointer = pointer == objects.length - 1 ? 0 : pointer + 1;
        }
        tail = tail == 0 ? objects.length - 1 : tail - 1;
    }

    @Override
    public boolean set(int index, T newData) {
        if (!checkIndex(index)) throw new ArrayIndexOutOfBoundsException();
        objects[getInnerIndex(index)] = newData;
        return true;
    }

    @Override
    public T get(int index) {
        return (T) objects[getInnerIndex(index)];
    }

    @Override
    public Integer indexOf(T data) {
        int index = head;
        while (index != tail) {
            if (objects[index].equals(data)) return index - head;
            if (index ++ == objects.length) index = 0;
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int current = head;
        while (current != tail) {
            current = current == objects.length ? 0 : current;
            stringBuilder.append(objects[current ++] + " ");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Array<Integer> list = new Array<>(6);
        list.addFront(432);
        list.addFront(1510);
        list.add(1);
        list.add(34);
        list.add(3);
        list.add(9);
        list.add(3, 666);
        list.add(143234);
        list.add(535);
        list.add(7499);
        System.out.println(list + "\n");

        Array<Integer> list2 = new Array<>();
        list2.add(11232);
        list2.add(888);

        list.addAll(list2);
        System.out.println(list);
/*
        list.remove();
        list.remove(0);
        list.removeFront();
        list.removeFront();
        System.out.println(list);
        System.out.println(list.indexOf(666));
        System.out.println(list.contains(98989898));
        list.add(999999);
        list.set(0, 7499);
        System.out.println(list);
        list.removeFront();
        list.removeFront();
        list.removeFront();
        list.removeFront();
        list.removeFront();

        list.add(7);
        list.add(0);
        System.out.println(list);
*/

        list.add(22);
    }
}
