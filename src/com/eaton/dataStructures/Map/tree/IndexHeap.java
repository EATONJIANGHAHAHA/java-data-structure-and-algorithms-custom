package com.eaton.dataStructures.Map.tree;

import com.eaton.dataStructures.Collections.Queue.Queue;
import com.eaton.dataStructures.Collections.list.Array;
import com.eaton.dataStructures.Pair;

import java.util.Comparator;
import java.util.Iterator;

public class IndexHeap<I, V> implements Tree<I, V> {

    Array<Integer> index;
    Array<Pair<I, V>> data;
    Comparator<? super I> comparator;

    public IndexHeap(int size, Comparator<? super I> comparator) {
        index = new Array<>(size);
        data = new Array<>(size);
        this.comparator = comparator;
    }

    private boolean checkIndex(int index) {
        return index < data.size();
    }

    private int getLIndex(int current) {
        return current * 2 + 1;
    }

    private Integer indexOf(I key) {
        for (int i = 0; i < data.size(); i ++) {
            if (data.get(i).getKey().equals(key))
                return i;
        }
        return null;
    }

    private void heapify(int index) {
        int left = 1, size = data.size();
        while (left < data.size()) {

            int largest = left + 1 < size && //如果右边孩子不越界并且
                    comparator.compare(data.get(left).getKey(), data.get(left + 1).getKey()) < 0 ? //右边孩子比左边孩子大
                    left + 1 : left; //选择右边孩子，否则选择左边孩子。

            if (comparator.compare(data.get(index).getKey(), data.get(largest).getKey()) >= 0) //如果当前数值比最大的孩子还大，退出
                break;

            //否则将最大的孩子与当前交换
            swap(index, largest);

            //继续下一次循环，index变成被交换过的位置，并更新孩子下标。
            index = largest;
            left = 2 * index + 1;
        }
    }

    private void swap(int i, int j) {
        Pair<I, V> temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void insert(I index) {
        insert(index, null);
    }

    @Override
    public void insert(I index, V value) {
        data.add(new Pair<>(index, value));
        this.index.addFront(data.size() - 1);

        Iterator<Integer> it = this.index.iterator();
        Integer next;
        while (it.hasNext()){
            next = it.next();

        }
    }

    @Override
    public void insertAll(Tree<? extends I, ? extends V> items) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V delete(I index) {
        return null;
    }

    @Override
    public V delete() {
        return null;
    }

    @Override
    public I deleteARI() {
        return null;
    }

    @Override
    public boolean set(I index, V value) {
        return false;
    }

    @Override
    public V find(I index) {
        return null;
    }

    @Override
    public Queue<Pair<I, V>> breadthFirst() {
        return null;
    }

    @Override
    public Queue<Pair<I, V>> inOrder() {
        return null;
    }

    @Override
    public boolean contains(I index) {
        return false;
    }

    @Override
    public Iterator<Pair<I, V>> iterator() {
        return null;
    }
}
