package DataStructures.Map.disjoin;


import DataStructures.Collections.list.Array;
import DataStructures.Map.Map;
import DataStructures.Map.tree.BinarySearchTree;
import DataStructures.Map.tree.Tree;
import DataStructures.Pair;
import com.sun.istack.internal.Nullable;

import java.util.Iterator;

/**
 * 本并查集实现保证查找时间复杂度为常数级别.
 *
 * @param <S>
 * @param <V>
 */
public class DisjoinFind<S extends Integer, K, V> implements Disjoin<S, K, V> {

    private Array<Entry> array = new Array<>(10);
    private Integer setIncremental = 0;

    private class Entry {

        S set;
        V value;
        K key;

        private Entry(S set, @Nullable K key, @Nullable V value) {
            this.set = set;
            this.value = value;
            this.key = key;
        }
    }

    private class DJFIterator implements Iterator<Pair<S, V>> {

        Iterator<Entry> it = array.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Pair<S, V> next() {
            Entry result = it.next();
            return new Pair<>(result.set, result.value);
        }
    }

    public DisjoinFind() {
        new DisjoinFind<>(10);
    }

    public DisjoinFind(int size) {
        array = new Array<>(size);
    }

    private boolean checkIndex(int key) {
        return key >= 0 && key < array.size();
    }

    private S findSet(int index) {
        return array.get(index).set;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findSet(p).equals(findSet(q));
    }

    @Override
    public void unionElements(int p, int q) {
        S pSet = findSet(p);
        S qSet = findSet(q);
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
    public boolean contains(V value) {
        for (Entry entry : array) if (entry.value.equals(value)) return true;
        return false;
    }

    @Override
    public void add(S set, V value) {
        array.add(new Entry(set, null, value));
        setIncremental = ((Integer) set > setIncremental) ? (Integer) set + 1 : setIncremental;
    }

    @Override
    public void addAllElements(Map<S, V> items) {
        for (Pair<S, V> pair : items) add(pair.getKey(), pair.getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addMaps(Map<K, V> items) {
        for (Pair<K, V> pair : items)
            array.add(new Entry((S) setIncremental, pair.getKey(), pair.getValue()));
        setIncremental++;
    }

    @Override
    public boolean set(int key, V newValue) {
        if (!checkIndex(key)) return false;
        array.set(key, new Entry(array.get(key).set, null, newValue));
        return true;
    }

    @Override
    public Integer find(V value) {
        for (int i = 0; i < array.size(); i++) if (array.get(i).value.equals(value)) return i;
        return null;
    }

    @Override
    public Iterator<Pair<S, V>> iterator() {
        return new DJFIterator();
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
        Disjoin<Integer, Integer, Double> disJoin = new DisjoinFind<>();
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
