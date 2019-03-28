package DataStructures.Map.tree;

import DataStructures.Collections.Queue.Queue;
import DataStructures.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * 字典树的定型实现，
 */
public class Trie<I extends String, V extends Integer> implements Tree<String, Integer> {

    private TrieNode root;

    @Override
    public Iterator<Pair<String, Integer>> iterator() {
        return null;
    }

    public static class TrieNode {
        public int path;
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }

    }

    public Trie() {
        root = new TrieNode();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void insert(String word) {
        if (word == null) return;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null)
                node.nexts[index] = new TrieNode();
            node = node.nexts[index];
            node.path++;
        }
        node.end++;
    }

    @Override
    public void insert(String index, Integer value) {

    }

    @Override
    public void insertAll(Tree<? extends String, ? extends Integer> items) {

    }

    @Override
    public int size() {
        return 0;
    }

    public Integer delete(String word) {
        if (find(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (--node.nexts[index].path == 0) {
                    node.nexts[index] = null;
                    return i;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
        return -1;
    }

    @Deprecated
    @Override
    public Integer delete() {
        throw new NotImplementedException();
    }

    @Deprecated
    @Override
    public String deleteARI() {
        throw new NotImplementedException();
    }

    @Override
    public boolean set(String index, Integer value) {
        return false;
    }

    @Override
    public Integer find(String word) {
        if (word == null) return 0;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) return 0;
            node = node.nexts[index];
        }
        return node.end;
    }

    @Override
    public Queue<Pair<String, Integer>> breadthFirst() {
        return null;
    }

    @Override
    public Queue<Pair<String, Integer>> inOrder() {
        return null;
    }

    @Override
    public boolean contains(String index) {
        return false;
    }

    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.path;
    }
}
