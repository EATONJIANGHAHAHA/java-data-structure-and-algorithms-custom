package Collections.list;

import Collections.Iter;
import com.sun.istack.internal.Nullable;

import java.util.NoSuchElementException;

/**
 * 单端单项链表实现
 * @param <T>
 */
public class LinkedList<T> implements List<T>{

    Node head;
    protected int size = 0;
    private ListIter itr;

    /**
     * 内部维护的节点
     */
    class Node {

        T data;
        Node next;
        Node previous;

        Node(T data, @Nullable Node next, @Nullable Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    /**
     * 定制的迭代器， 客户端程序猿可以进行对封装过的泛型数据进行迭代访问。
     */
    class ListIter implements Iter<T> {

        Node current;
        int index;

        public ListIter() { this.current = head; }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * 返回下一个节点的值，并移动到下一个节点
         * @return
         */
        @Override
        public T next() {
            assert !hasNext() : "iter is at it's last position.";
            current = current.next;
            index++;
            return current.data;
        }

        @Override
        public T getFirst() {
            assert isEmpty() : "list is empty.";
            return head.data;
        }
    }

    public Iter<T> getIter() {
        if (itr == null) itr = new ListIter();
        return itr;
    }

    /**
     * 检查给定下标是否越界
     *
     * @param index
     * @return
     */
    private boolean checkIndex(Integer index) {
        return index < size;
    }

    /**
     * 返回给定下表的数据
     *
     * @param index
     * @return
     */
    public T get(Integer index) {
        if (!checkIndex(index))
            throw new IndexOutOfBoundsException(index.toString());
        Node current = node(index);
        return current.data;
    }

    /**
     * 返回给定数据第一次出现的对应下标
     *
     * @param data
     * @return
     */
    public Integer indexOf(T data) {
        Node current = head;
        for (int index = 0; current != null; index++) {
            if (current.data.equals(data)) return index;
            current = current.next;
        }
        return null;
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

    /**
     * 检查是否包括给定数据项的第一个节点
     *
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {
        if (isEmpty()) throw new NoSuchElementException();
        Node current = node(data);
        return current != null;
    }

    protected Node node(T data) {
        if (isEmpty()) throw new NoSuchElementException();
        Node current = head;
        while (!current.data.equals(data)) current = current.next;
        return current;
    }

    protected Node node(Integer index) {
        if (isEmpty()) throw new NoSuchElementException();
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current;
    }

    /**
     * 返回链表长度
     *
     * @return
     */
    @Override
    public Integer size() {
        return size;
    }

    private void addFirstElement(T data) {
        head = new Node(data, null, null);
        size++;
    }

    /**
     * 向链表头添加元素
     *
     * @param data
     */
    public void add(T data) {
        if (!isEmpty()) {
            Node newNode = new Node(data, head, null);
            head = newNode;
            size++;
        } else addFirstElement(data);
    }

    /**
     * 向链表的某一下标后添加元素
     *
     * @param insertAfter
     * @param newData
     */
    public void add(T insertAfter, T newData) {
        if (contains(newData)) {
            final Node newNode = new Node(insertAfter, null, null), current = node(newData);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        } else addFirstElement(insertAfter);
    }

    /**
     * 从链表头移除元素
     */
    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public T peek() {
        return head.data;
    }

    /**
     * 移除制定值的节点
     * @param data
     * @return
     */
    public T remove(T data) {
        if (isEmpty()) throw new NoSuchElementException();
        Node current = node(data);
        Node previous = current.previous;
        previous.next = current.next;
        return current.data;
    }

    /**
     * 替换给定下标的数据
     * @param index
     * @param newData
     * @return
     */
    public boolean set(Integer index, T newData){
        if (!checkIndex(index) && index < 0) throw new IndexOutOfBoundsException();
        Node current = node(index);
        if (current == null) return false;
        current.data = newData;
        return true;
    }

    /**
     * 替换第一次出现与oldData数据相同的节点的数据
     * @param oldData
     * @param newData
     * @return
     */
    public boolean set(T oldData, T newData) {
        Node current = node(oldData);
        if (current.data.equals(oldData)) {
            current.data = newData;
            return true;
        }
        return false;
    }

    /**
     * 【题目】 分别实现反转单向链表和反转双向链表的函数。
     * 【要求】 如果链表长度为N,时间复杂度要求为O(N),额外空间
     * 复杂度要求为O(1)
     */
    public void revertLinkedList() {
        Node last = head, current = head.next, next = head.next.next;
        last.next = null;
        while (next != null) {
            current.next = last;
            last = current;
            current = next;
            next = next.next;
        }
        current.next = last;
        head = current;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node current = head;
        for (int i = 0; i < size; i++) {
            stringBuilder.append(current.data + "  ");
            current = current.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(7, 4);
        System.out.println(linkedList);
    }
}
