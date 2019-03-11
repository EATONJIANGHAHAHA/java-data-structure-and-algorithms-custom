package DataStructures.Map.tree;

import DataStructures.Collections.Queue.Queue;
import DataStructures.Pair;

import java.util.HashMap;
import java.util.Iterator;

public class Trie<I, V> implements Tree<I, V>{

    private Node root;
    private int size;

    class Node {
        int arrive, asEnding;
        HashMap<I, Node> map;

        Node() {
            new Node(0, 0);
        }

        Node(I index) {
            arrive = 0;
            asEnding = 0;
            map = new HashMap<>();
            map.put(index, new Node());
        }

        Node(int arrive, int asEnding) {
            this.arrive = arrive;
            this.asEnding = asEnding;
            this.map = new HashMap<>();
        }
    }

    public Trie() {
        root = new Node();
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return root.map.isEmpty();
    }

    @Override
    public void insert(I index) {

    }

    @Override
    public void insert(I index, V value) {

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
