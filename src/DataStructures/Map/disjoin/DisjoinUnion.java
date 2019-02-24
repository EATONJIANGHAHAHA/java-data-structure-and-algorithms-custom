package DataStructures.Map.disjoin;

import DataStructures.Collections.list.Array;
import DataStructures.Map.Map;
import DataStructures.Pair;
import com.sun.istack.internal.Nullable;

import java.util.Iterator;

public class DisjoinUnion<S extends Integer, K, V> implements Disjoin<S, K, V> {

    Array<Entry> parrents = new Array<>(10);
    Array<Integer> size = new Array<>(10);
    Integer setIncremental = 0;

    private class Entry {

        S parrent;
        V value;
        K key;

        private Entry(S parrent, @Nullable K key, @Nullable V value) {
            this.parrent = parrent;
            this.value = value;
            this.key = key;
        }
    }

    private class DJUIterator implements Iterator<Pair<S, V>> {

        Iterator<Entry> it = parrents.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Pair<S, V> next() {
            Entry entry = it.next();
            return new Pair<>(entry.parrent, entry.value);
        }
    }

    /**
     * 寻找根节点, 使用了路径压缩算法
     * @param index
     * @return
     */
    private S findSetRoot(Integer index) {
        Entry current = parrents.get(index);
        if (current == null) return null;
        if (current.parrent == null) return (S) index;
        while (!current.parrent.equals(parrents.indexOf(current))) {
            parrents.set(index, new Entry(parrents.get(current.parrent).parrent, current.key, current.value));
            current = parrents.get(current.parrent);
        }
        return current.parrent;
    }

    private boolean checkIndex(int set) {
        return set >= 0 && set < parrents.size();
    }

    @Override
    public boolean isConnected(int p, int q) {
        if (!checkIndex(p) || !checkIndex(q)) return false;
        return findSetRoot(p).equals(findSetRoot(q));
    }

    @Override
    public void unionElements(int p, int q) {
        Integer pRoot = findSetRoot(p);
        Integer qRoot = findSetRoot(q);
        if (pRoot == null || qRoot == null) return;
        if (qRoot.equals(pRoot)) return;
        Entry pEntry = parrents.get(pRoot);
        Entry qEntry = parrents.get(qRoot);
        Integer pHeight = size.get(pRoot);
        Integer qHeight = size.get(qRoot);
        if (pHeight > qHeight)
            parrents.set(qRoot, new Entry((S) pRoot, qEntry.key, qEntry.value));
        else parrents.set(pRoot, new Entry((S) qRoot, pEntry.key, pEntry.value));
        refreshHeight(qRoot);
    }

    @Override
    public int size() {
        return parrents.size();
    }

    @Override
    public boolean isEmpty() {
        return parrents.isEmpty();
    }

    @Override
    public boolean contains(V value) {
        for (Entry entry : parrents) if (entry.value.equals(value)) return true;
        return false;
    }

    @Override
    public void add(S set, V value) {
        parrents.add(new Entry(set, null, value));
        setIncremental = (Integer) set > setIncremental ? (Integer) set + 1 : setIncremental;
        refreshHeight(set);
    }

    private void refreshHeight(Integer set) {
        Integer root = findSetRoot(set);
        if (root == null) return;
        if (root >= size.size()) size.add(set);
        else size.set(root, size.get(root) + 1);
    }

    @Override
    public void addAllElements(Map<S, V> items) {
        for (Pair<S, V> pair : items) add(pair.getKey(), pair.getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addMaps(Map<K, V> items) {
        Integer heightCount = 0;
        for (Pair<K, V> pair : items) {
            parrents.add(new Entry((S) setIncremental, pair.getKey(), pair.getValue()));
            heightCount ++;
        }
        refreshHeight(heightCount);
        setIncremental ++;
    }

    @Override
    public boolean set(int key, V newValue) {
        if (!checkIndex(key)) return false;
        parrents.set(key, new Entry(parrents.get(key).parrent, null, newValue));
        return true;
    }

    @Override
    public Integer find(V value) {
        for (int i = 0; i < parrents.size(); i ++) if (parrents.get(i).value.equals(value)) return i;
        return null;
    }

    @Override
    public Iterator<Pair<S, V>> iterator() {
        return new DJUIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < parrents.size(); i++) {
            stringBuilder.append("Id:").append(i).append(", Key:");
            if (parrents.get(i).key != null) stringBuilder.append(parrents.get(i).key.toString());
            else stringBuilder.append("null");
            stringBuilder.append(", Parrent:").append(parrents.get(i).parrent.toString()).append(", Value:");
            if (parrents.get(i).value != null) stringBuilder.append(parrents.get(i).value.toString());
            else stringBuilder.append("null");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Disjoin<Integer, Integer, Double> disJoin = new DisjoinUnion<>();
        disJoin.add(0, 1.23);
        disJoin.add(2, 1.2);
        disJoin.add(3, 3.2);
        disJoin.add(3, 1.0);
        disJoin.add(4, 44.0);
        disJoin.add(6, 6.6);
        disJoin.add(3, 3.1);
        disJoin.add(0, 554.0);
        System.out.println(disJoin);
        disJoin.unionElements(0, 2);
        System.out.println(disJoin);
        System.out.println(disJoin.isConnected(7, 4));
    }
}
