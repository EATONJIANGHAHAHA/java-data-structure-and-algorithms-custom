package sequentialList;

import com.sun.istack.internal.Nullable;

/**
 * 这是一个双端双向循环链表实现
 * @param <T>
 */
public class DoubleLinkedList<T> {

    protected Node head;
    protected Node tail;
    protected int size = 0;

    protected class Node {

        protected T data;
        protected Node next;
        protected Node previous;

        protected Node(T data, @Nullable Node next, @Nullable Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    private boolean checkIndex(Integer index) {
        return index <= size - 1;
    }

    public int size() {
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
        tail = head;
        size++;
    }

    /**
     * 向链表中的一个位置添加元素
     * @param data
     */
    public void add(T data, Integer index) {
        if(!isEmpty()) {
            if (!checkIndex(index)) throw new IndexOutOfBoundsException(index + ", with actual maximum index: " + (size() - 1));
            int count = 0;
            Node current = head;
            while (count < index) {
                current = current.next;
                count ++;
            }
            Node newNode = new Node(data, current.next, current);
            current.next = newNode;
            current.next.previous = newNode;
            size++;
        } else addFirstElement(data);
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(1, 0);
        linkedList.add(2, 0);
        linkedList.add(3, 1);
        linkedList.add(4, 2);
        linkedList.add(5, 3);
    }
}
