package list;

import com.sun.istack.internal.Nullable;

/**
 * 这是一个双端双向循环链表实现
 *
 * @param <T>
 */
public class DoubleLinkedList<T> extends LinkedList<T> {

    private Node head;
    private Node tail;
    private int size = 0;

    public class Node {

        private T data;
        private Node next;
        private Node previous;

        Node(T data, @Nullable Node next, @Nullable Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    private boolean checkIndex(Integer index) {
        return index < size;
    }

    /**
     * 返回给定下表的数据
     * @param index
     * @return
     */

    public T get (Integer index) {
        if (!checkIndex(index)) throw new IndexOutOfBoundsException(index.toString());
        Node current = head;
        for (int i = 0; i < index; i ++) current = current.next;
        return current.data;
    }

    /**
     * 检查是否包括给定数据项的第一个节点
     * @param data
     * @return
     */

    public boolean contains(T data) {
        Node current = head;
        for (int index = 0; current != null; index++) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    /**
     * 返回给定数据第一次出现的对应下标
     * @param data
     * @return
     */
    public Integer indexOf(T data) {
        Node current = head;
        Integer index = 0;
        while (!current.data.equals(data) && checkIndex(index))
            current = current.next;
        if (current.data.equals(data)) return index;
        else return null;
    }

    /**
     * 返回给定数据项对应的的第一个节点
     * @param data
     * @return
     */
    private Node node(T data) {
        Node current = head;
        int index = 0;
        while (!current.data.equals(data) && checkIndex(index))
            current = current.next;
        if (current.data.equals(data)) return current;
        else return null;
    }

    /**
     * 返回链表长度
     *
     * @return
     */
    public Integer size() {
        return size;
    }

    /**
     * 检查链表是否为空
     *
     * @return
     */
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

    /**
     * 向链表中的一个位置添加元素
     *
     * @param data
     */
    public void add(T data, Integer index) {
        if (!isEmpty()) {
            if (!checkIndex(index)) throw new IndexOutOfBoundsException(index.toString());
            Node current = head;
            for (int i = 0; i < index; i++) current = current.next;
            final Node newNode = new Node(data, current.next, current);
            current.next.previous = newNode;
            current.next = newNode;
            if (index == size - 1) tail = newNode;
            size++;
        } else addFirstElement(data);
    }

    /**
     * 向链表头添加元素
     *
     * @param data
     */
    public void addFront(T data) {
        final Node newNode = new Node(data, head, tail);
        head.previous = newNode;
        tail.next = newNode;
        head = newNode;
        size++;
    }

    /**
     * 向链表尾部添加元素
     *
     * @param data
     */
    public void add(T data) {
        add(data, size - 1);
    }

    public T remove(Integer index) {
        if (!isEmpty()) {
            if (size > 1) {
                if (!checkIndex(index)) throw new IndexOutOfBoundsException(index.toString());
                Node current = head;
                for (int i = 0; i < index; i++) current = current.next;
                if (index == 0) head = current.next;
                else if (index == size - 1) tail = current.previous;
                current.next.previous = current.previous;
                current.previous.next = current.next;
                size--;
                return current.data;
            } else return removeLastNode();
        } else throw new IndexOutOfBoundsException("List is empty.");
    }

    private T removeLastNode() {
        head = null;
        tail = null;
        size = 0;
        return null;
    }

    public T remove() {
        return remove(size - 1);
    }

    public T removeFront() {
        return remove(0);
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(0);
        linkedList.removeFront();
    }
}
