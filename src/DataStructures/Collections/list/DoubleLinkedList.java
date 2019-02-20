package DataStructures.Collections.list;

import java.util.NoSuchElementException;

/**
 * 这是一个双端双向循环链表实现
 *
 * @param <T>
 */
public class DoubleLinkedList<T> extends LinkedList<T> {

    protected Node tail;

    private boolean checkIndex(Integer index) {
        return index < size;
    }

    /**
     * 返回给定下表的数据
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (!checkIndex(index)) throw new IndexOutOfBoundsException(String.valueOf(index));
        Node current = node(index);
        return current.data;
    }

    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.data;
    }

    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.data;
    }

    /**
     * 检查是否包括给定数据项的第一个节点
     *
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {
        Node current = node(data);
        return current != null;
    }

    /**
     * 返回链表长度
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 检查链表是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private void addFirstElement(T data) {
        head = new Node(data, null, null);
        head.next = head;
        head.previous = head;
        tail = head;
        size++;
    }

    private Node linkHeadTail(T data) {
        final Node newNode = new Node(data, head, tail);
        head.previous = newNode;
        tail.next = newNode;
        size++;
        return newNode;
    }

    /**
     * 向链表头添加元素
     *
     * @param data
     */
    public void addFirst(T data) {
        if (isEmpty()) addFirstElement(data);
        else head = linkHeadTail(data);
    }

    /**
     * 向链表尾部添加元素
     *
     * @param data
     */
    @Override
    public void add(T data) {
        if (isEmpty()) addFirstElement(data);
        else tail = linkHeadTail(data);
    }

    /**
     * 向链表中的给定位置后添加元素
     *
     * @param index
     */
    @Override
    public void add(int index, T data) {
        if (checkIndex(index)) {
            final Node current = node(index), newNode = new Node(data, current.next, current);
            current.next.previous = newNode;
            current.next = newNode;
            if (current.equals(tail)) tail = newNode;
            size++;
        } else addFirstElement(data);
    }

    @Override
    public void addAll(List<? extends T> items) {
        for (T item : items) add(item);
    }

    private T removeLastElement() {
        T data = head.data;
        head = null;
        tail = null;
        size = 0;
        return data;
    }

    /**
     * 移除链表尾部元素
     * @return
     */
    @Override
    public T remove() {
        T result = null;
        if (!isEmpty()) {
            if (size > 1) {
                Node current = tail;
                result = current.data;
                tail = current.previous;
                current.next.previous = current.previous;
                current.previous.next = current.next;
                size--;
            }
            else return removeLastElement();
        } else throw new NoSuchElementException();
        return result;
    }

    /**
     * 移除链表头部元素
     * @return
     */
    public T removeFirst() {
        T result = null;
        if (!isEmpty()) {
            if (size > 1) {
                Node current = head;
                result = current.data;
                head = current.next;
                current.next.previous = current.previous;
                current.previous.next = current.next;
                size--;
            } else return removeLastElement();
        } else throw new NoSuchElementException();
        return result;
    }

    /**
     * 移除链表中第一次遇到给定值的元素
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        T result = null;
        if (!isEmpty()) {
            if (size > 1) {
                Node current = node(index);
                result = current.data;
                current.next.previous = current.previous;
                current.previous.next = current.next;
                size--;
            } else return removeLastElement();
        } else throw new IllegalArgumentException();
        return result;
    }

    public static void main(String[] args) {
        List<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(0);
        linkedList.remove(0);
        linkedList.add(1);
        linkedList.add(123);
        System.out.println(linkedList);
    }
}
