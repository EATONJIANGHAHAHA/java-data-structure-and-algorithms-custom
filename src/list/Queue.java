package list;

/**
 * 这是一个循环队列的链表实现
 * @param <T>
 */
public class Queue<T> {

    private DoubleLinkedList<T> list;

    public Queue() {
        list = new DoubleLinkedList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void offer(T data) {
        list.addFront(data);
    }

    public T poll() {
        return list.remove();
    }

    public T peek() {
        return list.get(list.size() - 1);
    }
}
