package DataStructures.Map.tree;

import DataStructures.Collections.Queue.ArrayQueue;
import DataStructures.Collections.Queue.LinkedListQueue;
import DataStructures.Collections.Queue.Queue;
import DataStructures.Collections.list.Array;
import DataStructures.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * 最大堆的基于动态数组的实现
 * @param <I>
 * @param <V>
 */
public class Heap<I extends Comparable<I>, V> implements Tree<I, V>{

    Array<Pair<I, V>> array;

    private class HeapIterator implements Iterator<Pair<I, V>> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < array.size();
        }

        @Override
        public Pair<I, V> next() {
            return new Pair<>(array.get(index).getKey(),
                    array.get(index ++).getValue());
        }
    }

    public Heap(int size) {
        array = new Array<>(size);
    }

    public Heap() {
        array = new Array<>();
    }

    private boolean checkIndex(int index) {
        return index < array.size();
    }

    private int getFIndex(int current) {
        return (current - 1) / 2;
    }

    private int getLIndex(int current) {
        return current * 2 + 1;
    }

    private Integer indexOf(I key) {
        for (int i = 0; i < array.size(); i ++) {
            if (array.get(i).getKey().equals(key))
                return i;
        }
        return null;
    }

    private Integer indexOf(V value) {
        for (int i = 0; i < array.size(); i ++) {
            if (array.get(i).getKey().equals(value))
                return i;
        }
        return null;
    }

    private void swap(int i, int j) {
        Pair<I, V> temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    private void heapify(int index) {
        int left = 1, size = array.size();
        while (left < array.size()) {
            int largest = left + 1 < size &&
                    array.get(left).getKey().compareTo(array.get(left + 1).getKey()) < 0
                    ? left + 1 : left;
            if (array.get(index).getKey().compareTo(array.get(largest).getKey()) >= 0)
                break;
            swap(index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void insert(I index) {
        insert(index, null);
    }

    @Override
    public void insert(I key, V value) {
        array.addFront(new Pair<>(key, value));
        heapify(0);
    }

    @Override
    public void insertAll(Tree<? extends I, ? extends V> items) {
        for (Pair<? extends I, ? extends V> pair : items.breadthFirst()) {
            insert((I) pair.getKey(), null);
        }
    }

    @Override
    public int size() {
        return array.size();
    }

    /**
     * 删除根节点并返回值
     * @param key
     * @return
     */
    @Deprecated
    @Override
    public V delete(I key) {
        throw new NotImplementedException();
    }

    @Override
    public V delete() {
        V result = array.get(0).getValue();
        swap(0, array.size() - 1);
        array.remove(array.size() - 1);
        heapify(0);
        return result;
    }

    /**
     * 更新对应键下的值,如果键不存在则返回false, 否则返回true.
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(I key, V value) {
        Integer index = indexOf(key);
        if (index == null) return false;
        array.set(index, new Pair<>(key, value));
        return true;
    }

    @Override
    public V find(I key) {
        for (Pair pair : array) {
            if (pair.getKey().equals(key))
                return (V) pair.getValue();
        }
        return null;
    }

    @Override
    public Queue<Pair<I, V>> breadthFirst() {
        Queue<Pair<I, V>> queue = new ArrayQueue<>(array.size());
        for (Pair pair : array)
            queue.offer(pair);
        return queue;
    }

    @Override
    public Queue<Pair<I, V>> inOrder() {
        return inorder(0);
    }

    private Queue<Pair<I, V>> inorder(int head) {
        if (!checkIndex(head) || isEmpty()) return null;
        Queue<Pair<I, V>> result = new LinkedListQueue<>();
        Queue<Pair<I, V>> left = inorder(getLIndex(head));
        if (left != null) result.offerAll(left);
        result.offer(new Pair<>(array.get(head).getKey(), array.get(head).getValue()));
        Queue<Pair<I, V>> right = inorder(getLIndex(head) + 1);
        if (right != null) result.offerAll(right);
        return result;
    }

    @Override
    public boolean contains(I key) {
        return array.contains(new Pair<>(key, find(key)));
    }

    @Override
    public Iterator<Pair<I, V>> iterator() {
        return new HeapIterator();
    }

    private String printTree(int head) {
        return "Heap:\n" + printInOrder(head, 0, "H", 17).toString();
    }

    private StringBuilder printInOrder(int head, int height, String to, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        if (isEmpty() || !checkIndex(head)) {
            return stringBuilder;
        }
        stringBuilder.append(printInOrder(getLIndex(head) + 1, height + 1, "V", len));
        String val = to + array.get(head).getKey() + "," + array.get(head).getValue() + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        stringBuilder.append(getSpace(height * len) + val + "\n");
        stringBuilder.append(printInOrder(getLIndex(head), height + 1, "Λ", len));
        return stringBuilder;
    }

    @SuppressWarnings("Duplicates")
    public String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return printTree(0);
    }

    public static void main(String[] args) {
        Tree<Integer, Integer> heap = new Heap<>();
        heap.insert(12, 1);
        heap.insert(1, 1);
        heap.insert(33, 1);
        heap.insert(67, 1);
        heap.insert(900, 1);
        heap.insert(2, 1);
        heap.insert(70, 1);
        heap.delete();
        System.out.println(heap);
        System.out.println(heap.inOrder());
    }


}
