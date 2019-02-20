package DataStructures;

public class ComparablePair<K extends Comparable<K>, V> extends Pair<K, V>
        implements Comparable<ComparablePair<K, V>> {

    public ComparablePair(K key, V value) {
        super(key, value);
    }

    @Override
    public int compareTo(ComparablePair<K, V> o) {
        return this.key.compareTo(o.getKey());
    }
}
