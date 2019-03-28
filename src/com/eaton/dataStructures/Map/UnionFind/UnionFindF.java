package com.eaton.dataStructures.Map.UnionFind;


import com.eaton.dataStructures.Collections.list.Array;
import com.eaton.dataStructures.Map.Map;
import com.eaton.dataStructures.Map.tree.BinarySearchTree;
import com.eaton.dataStructures.Map.tree.Tree;
import com.eaton.dataStructures.Pair;
import com.sun.istack.internal.Nullable;

import java.util.Iterator;

/**
 * 本并查集实现保证查找时间复杂度为常数级别.
 *
 * @param <SET>
 * @param <VALUE>
 */
public class UnionFindF<SET extends Integer, KEY, VALUE> implements UnionFind<SET, KEY, VALUE> {

    private Array<Entry> array = new Array<>(10);
    private Integer setIncremental = 0;

    private class Entry {

        SET set;
        VALUE value;
        KEY key;

        private Entry(SET set, @Nullable KEY key, @Nullable VALUE value) {
            this.set = set;
            this.value = value;
            this.key = key;
        }
    }

    private class UFFIterator implements Iterator<Pair<SET, VALUE>> {

        Iterator<Entry> it = array.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Pair<SET, VALUE> next() {
            Entry result = it.next();
            return new Pair<>(result.set, result.value);
        }
    }

    public UnionFindF() {
        new UnionFindF<>(10);
    }

    public UnionFindF(int size) {
        array = new Array<>(size);
    }

    private boolean checkIndex(int key) {
        return key >= 0 && key < array.size();
    }

    private SET findSet(int index) {
        return array.get(index).set;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findSet(p).equals(findSet(q));
    }

    @Override
    public void unionElements(int eOne, int eTwo) {
        SET pSet = findSet(eOne);
        SET qSet = findSet(eTwo);
        if (pSet == null || qSet == null) throw new IllegalArgumentException();
        if (pSet.equals(qSet)) return;
        for (Entry entry : array) if (entry.set.equals(qSet)) entry.set = pSet;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public boolean contains(VALUE value) {
        for (Entry entry : array) if (entry.value.equals(value)) return true;
        return false;
    }

    @Override
    public void add(SET set, VALUE value) {
        array.add(new Entry(set, null, value));
        setIncremental = ((Integer) set > setIncremental) ? (Integer) set + 1 : setIncremental;
    }

    @Override
    public void add(SET set, KEY key, VALUE value) {
        array.add(new Entry(set, key, value));
        setIncremental = ((Integer) set > setIncremental) ? (Integer) set + 1 : setIncremental;
    }

    @Override
    public void addAllElements(Map<SET, VALUE> items) {
        for (Pair<SET, VALUE> pair : items) add(pair.getKey(), pair.getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addMaps(Map<KEY, VALUE> items) {
        for (Pair<KEY, VALUE> pair : items)
            array.add(new Entry((SET) setIncremental, pair.getKey(), pair.getValue()));
        setIncremental++;
    }

    @Override
    public boolean set(int key, VALUE newValue) {
        if (!checkIndex(key)) return false;
        array.set(key, new Entry(array.get(key).set, null, newValue));
        return true;
    }

    @Override
    public Integer find(VALUE value) {
        for (int i = 0; i < array.size(); i++) if (array.get(i).value.equals(value)) return i;
        return null;
    }

    @Override
    public Iterator<Pair<SET, VALUE>> iterator() {
        return new UFFIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            stringBuilder.append("Id:").append(i).append(", Key:");
            if (array.get(i).key != null) stringBuilder.append(array.get(i).key.toString());
            else stringBuilder.append("null");
            stringBuilder.append(", Set:").append(array.get(i).set.toString()).append(", Value:");
            if (array.get(i).value != null) stringBuilder.append(array.get(i).value.toString());
            else stringBuilder.append("null");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        UnionFind<Integer, Integer, Double> disJoin = new UnionFindF<>();
        disJoin.add(0, 1.1);
        disJoin.add(1, 2.2);
        disJoin.add(3, 123.0);
        disJoin.add(2, 55.88);
        disJoin.add(7, 9.2);
        disJoin.add(0, 33.0);
        disJoin.add(5, 2.34343);
        disJoin.unionElements(1, 0);
        Tree<Integer, Double> tree = new BinarySearchTree<>();
        tree.insert(3343, 1.2);
        Tree<Integer, Double> tree2 = new BinarySearchTree<>();
        tree2.insert(431000, 556.3);
        tree2.insert(49, 00.3);
        disJoin.addMaps(tree);
        disJoin.addMaps(tree2);
        System.out.println(disJoin);

        disJoin.unionElements(1, 9);
        System.out.println(disJoin);
    }
}
