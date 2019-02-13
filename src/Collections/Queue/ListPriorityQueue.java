package Collections.Queue;

import Collections.Iter;
import Collections.list.DoubleLinkedList;

public class ListPriorityQueue<T extends Comparable<T>> implements Queue<T> {

    DoubleLinkedList<T> list;

    class ListPriorityQueueIter implements Iter<T> {

        Iter<T> it = list.getIter();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next();
        }

        @Override
        public T getFirst() {
            return it.getFirst();
        }
    }

    public ListPriorityQueue() {
        list = new DoubleLinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(T data) {
        return list.contains(data);
    }

    @Override
    public Integer size() {
        return list.size();
    }

    @Override
    public void offer(T data) {
        if (isEmpty()) list.add(0, data);
        else {
            int index = getInsertIndex(data);
            if (index == -1) list.addFirst(data);
            else list.add(index, data);
        }
    }

    private int getInsertIndex(T data) {
        Iter<T> it = list.getDIter(); //反向迭代器
        int index = list.size() - 1;
        T currentData = it.getFirst();
        do {
            if (currentData.compareTo(data) < 0) {
                currentData = it.next();
                index --;
            } else return index;
        } while (it.hasNext());
        return -1;
    }

    @Override
    public T poll() {
        return list.removeFirst();
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    @Override
    public Iter<T> getIter() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iter<T> it = list.getIter();
        stringBuilder.append(it.getFirst()).append(" ");
        while (it.hasNext()) //todo
            stringBuilder.append(it.next()).append(" ");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ListPriorityQueue<Integer> queue = new ListPriorityQueue<>();
        queue.offer(33);
        queue.offer(34);
        System.out.println(queue.contains(33));
        queue.offer(12);
        queue.offer(110);
        queue.offer(31);
        queue.offer(1);
        System.out.println(queue + "\n");
        queue.poll();
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue);
    }
}
