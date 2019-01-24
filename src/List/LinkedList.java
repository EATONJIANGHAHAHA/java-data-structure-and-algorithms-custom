package List;

import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LinkedList<T> {

    protected Node head;
    protected int size = 0;

    protected class Node {

        protected T data;
        protected Node next;

        protected Node(T data, @Nullable Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public boolean hasNode(T data) {
        if (head == null) return false;
        Node current = head;
        while (!current.data.equals(data)) current = current.next;
        if (current != null) return true;
        else return false;
    }

    private Node findNode(T data) {
        if (head == null) return null;
        Node current = head;
        while (!current.data.equals(data)) current = current.next;
        return current;
    }

    public int size() {
        return size;
    }

    public void add(T data) {
        if (head == null) {
            head = new Node(data, null);
            size++;
            return;
        }
        Node current = head;
        while (current.next != null) current = current.next;
        current.next = new Node(data, null);
        size++;
    }

    public void remove(T data) {
        throw new NotImplementedException();
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
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.revertLinkedList();
    }
}
