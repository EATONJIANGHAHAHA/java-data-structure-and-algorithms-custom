package Collections.map;

import Collections.Iter;
import Collections.list.DoubleLinkedList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LinkedListMap<K, V> implements Map<K, V> {

    DoubleLinkedList<Pair> list;

    class Pair {

        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    protected Pair pair(K key) {
        int i = 0;
        Iter<Pair> it = list.getIter();
        while (it.hasNext()) {
            Pair pair = it.next();
            if (pair.key.equals(key)) return pair;
        }
        return null;
    }

    public LinkedListMap() {
        list = new DoubleLinkedList<>();
    }

    @Override
    public Iter<V> getIter() {
        throw new NotImplementedException();
    }

    @Override
    public void add(K key, V value) {
        list.addFirst(new Pair(key, value));
    }

    @Override
    public V remove(K key) {
        return list.remove(pair(key)).value;
    }

    @Override
    public boolean contains(K key) {
        return pair(key) != null;
    }

    @Override
    public V get(K key) {
        return pair(key).value;
    }

    @Override
    public void set(K key, V newValue) {
        list.set(pair(key), new Pair(key, newValue));
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
