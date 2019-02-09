package list;

import com.sun.istack.internal.Nullable;

/**
 * 单端单项链表实现
 * @param <T>
 */
public class LinkedList<T> implements List<T>{

    private Node head;
    private int size = 0;

    public class Node extends List.Node {

        private T data;
        private Node next;

        Node(T data, @Nullable Node next) {
            this.data = data;
            this.next = next;
        }
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
    @Override
    public T get(Integer index) {
        if (!checkIndex(index))
            throw new IndexOutOfBoundsException(index + ", with actual maximum index: " + (size() - 1));
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    /**
     * 返回给定数据第一次出现的对应下标
     *
     * @param data
     * @return
     */
    @Override
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
        if (isEmpty()) throw new RuntimeException("The list is empty.");
        Node current = head;
        while (!current.data.equals(data)) current = current.next;
        if (current != null) return true;
        else return false;
    }

    /**
     * 返回给定数据项的第一个节点
     *
     * @param data
     * @return
     */
    private Node node(T data) {
        if (isEmpty()) throw new RuntimeException("The list is empty.");
        Node current = head;
        while (!current.data.equals(data)) current = current.next;
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

    @Override
    public void add(Integer index, Object data) {

    }

    private void addFirstElement(T data) {
        head = new Node(data, null);
        size++;
    }

    /**
     * 向链表头添加元素
     *
     * @param data
     */
    public void add(T data) {
        if (!isEmpty()) {
            Node newNode = new Node(data, head);
            head = newNode;
            size++;
        } else addFirstElement(data);
    }

    /**
     * 向链表的某一下标后添加元素
     *
     * @param data
     * @param index
     */
    public void add(T data, Integer index) {
        if (!isEmpty()) {
            if (!checkIndex(index)) throw new IndexOutOfBoundsException(index + ", with actual maximum index: " + (size() - 1));
            Node newNode = new Node(data, null), current = head;
            int count = 0;
            while (count < index) {
                current = current.next;
                count++;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        } else addFirstElement(data);
    }

    /**
     * 从链表头移除元素
     */
    public T remove() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("This list is already empty.");
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * 移除链表中指定位置的元素
     * @param index
     * @return
     */
    @Override
    public T remove(Integer index) {
        if (isEmpty())
            throw new IndexOutOfBoundsException("This list is already empty.");
        int count = 0;
        Node current = head, previous = null;
        while (count < index) {
            previous = current;
            current = current.next;
            count++;
        }
        T data = current.data;
        previous.next = current.next;
        size--;
        return data;
    }

    /**
     * 替换给定下标的数据
     * @param index
     * @param newData
     * @return
     */
    @Override
    public boolean set(Integer index, T newData){
        if (!checkIndex(index) && index < 0) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i ++) current = current.next;
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
    @Override
    public boolean set(T oldData, T newData) {
        Node current = head;
        int count = 0;
        while (!current.data.equals(oldData) && checkIndex(count))
            current = current.next;
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

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(7, 4);
    }
}
