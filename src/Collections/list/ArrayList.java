package Collections.list;

import Collections.Iter;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private Object[] objects = new Object[2];
    private int head, tail, size;
    //head: 下一个要插入的元素索引, tail: 下一个要删除的元素索引, size: 结构大小

    class ArrayListIter implements Iter<T> {

        int index;

        public ArrayListIter() {
            index = tail;
        }

        @Override
        public boolean hasNext() {
            return index != head - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            index = index == objects.length - 1 ? 0 : index + 1;
            return (T) objects[index];
        }

        @Override
        public T getFirst() {
            return (T) objects[tail];
        }
    }

    public ArrayList() {
        new ArrayList(10);
    }

    public ArrayList(int size) {
        objects = new Object[size];
        head = 0;
        this.size = 0;
        tail = 0;
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
        if (size == objects.length) expendCapacity();
    }

    private void expendCapacity() {
        Object[] newArray = new Object[objects.length * 2];
        Iter it = new ArrayListIter();
        newArray[0] = it.getFirst();
        for (int i = 1; it.hasNext(); i ++)
            newArray[i] = it.next();
        head = size;
        tail = 0;
        objects = newArray;
    }

    private boolean checkIndex(int index) {
        index = getInnerIndex(index);
        return index >= tail && index < head;
    }

    /**
     * 获取内部数组可以使用的绝对下标.
     *
     * @param index
     * @return
     */
    private int getInnerIndex(int index) {
        index += tail;
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
        if (head < tail && current > tail)
            return objects.length - Math.abs(head - current);
        else return head - current;
    }

    /**
     * 向表尾添加元素
     *
     * @param data
     */
    @Override
    public void add(T data) {
        ensureCapacity();
        if (head == objects.length) head = 0;
        objects[head++] = data;
        size++;
    }

    /**
     * 向表头添加元素
     *
     * @param data
     */
    public void
    addFront(T data) {
        ensureCapacity();
        tail = (tail == 0) ? objects.length - 1 : tail - 1;
        objects[tail] = data;
        size++;
    }

    /**
     * 向表中index元素后添加一个元素
     *
     * @param data
     * @param index
     */
    public void add(T data, Integer index) {
        ensureCapacity();
        int current = getInnerIndex(index) + 1;
        pushPartsOnec(current);
        objects[current] = data;
        size++;
    }

    private void pushPartsOnec(int current) {
        int pointer = head;
        while (pointer != current) {
            objects[pointer] = pointer == 0 ? objects[objects.length - 1] : objects[pointer - 1];
            pointer = pointer == 0 ? objects.length - 1 : pointer - 1;
        }
        head = head == objects.length - 1 ? 0 : head + 1;
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
        return (T) objects[--head];
    }

    /**
     * 移除第一个元素
     *
     * @return
     */
    public T removeFront() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;
        return (T) objects[tail++];
    }

    public T remove(int index) {
        if (!checkIndex(index)) throw new ArrayIndexOutOfBoundsException();
        int current = getInnerIndex(index);
        T data = (T) objects[current];
        pullPartsOnce(current);
        size--;
        return data;
    }

    @Override
    public T remove(T data) {
        Integer index = indexOf(data);
        if (index == null) throw new NoSuchElementException();
        return remove(index);
    }

    private void pullPartsOnce(int current) {
        int pointer = current;
        while (pointer != head) {
            objects[pointer] = pointer == objects.length - 1 ? objects[0] : objects[pointer + 1];
            pointer = pointer == objects.length - 1 ? 0 : pointer + 1;
        }
        head = head == 0 ? objects.length - 1 : head - 1;
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
        int index = tail;
        while (index != head) {
            if (objects[index].equals(data)) return index - tail;
            if (index ++ == objects.length) index = 0;
        }
        return null;
    }

    @Override
    public Iter<T> getIter() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int current = tail;
        while (current != head) {
            current = current == objects.length ? 0 : current;
            stringBuilder.append(objects[current ++] + " ");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.addFront(432);
        list.add(34);
        list.add(3);
        list.add(9);
        list.add(666, 3);
        list.add(143234);
        list.add(535);
        list.add(7499);
        System.out.println(list + "\n");

        list.remove();
        list.remove(0);
        list.remove(new Integer(34));
        list.removeFront();
        list.removeFront();
        System.out.println(list.indexOf(535));
        System.out.println(list.contains(98989898));
        list.add(999999);
        list.removeFront();
        list.removeFront();
        list.removeFront();
        list.removeFront();
        list.removeFront();

        list.add(7);
        list.add(0);
        System.out.println(list);



    }
}
