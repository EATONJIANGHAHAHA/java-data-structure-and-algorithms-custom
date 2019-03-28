package com.eaton.dataStructures.Map.tree;

import com.eaton.dataStructures.Pair;
import com.sun.istack.internal.Nullable;

public class RedBlackTree<I extends Comparable<I>, V> extends AVLTree<I, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    @Override
    public void insert(I index) {
        insert(index, null);
    }

    @Override
    public void insertAll(Tree<? extends I, ? extends V> items) {
        for (Pair<? extends I, ? extends V> pair : items) insert(pair.getKey(), pair.getValue());
    }

    @Override
    public void insert(I index, V value) {
        root = insert(root, null, index, value);
        root.color = BLACK;
        System.out.println(this);
    }

    private Node insert(Node head, @Nullable Node parrent, I index, V value) {
        if (head == null){
            size ++;
            return new Node(index, value, parrent, null, null);
        }
        if (index.compareTo(head.index) > 0)
            head.right = insert(head.right, head, index, value);
        else if (index.compareTo(head.index) < 0)
            head.left = insert(head.left, head, index, value);
        else
            head.value = value;
        return maintainRB(head);
    }

    @Override
    public V delete(I index) {
        Pair<Node, Node> result = delete(root, index);
        root = (result != null) ? result.getKey() : root;
        System.out.println(this);
        return (result != null) ? result.getValue().value : null;
    }

    private Pair<Node, Node> delete(Node head, I index) {
        Pair<Node, Node> result;
        if (head == null) return null;
        else if (index.compareTo(head.index) < 0) {
            result = delete(head.left, index);
            if (result == null) return null;
            head.left = result.getKey();
            result.setKey(head);
        }
        else if (index.compareTo(head.index) > 0) {
            result = delete(head.right, index);
            if (result == null) return null;
            head.right = result.getKey();
            result.setKey(head);
        }
        else
            result = deleteCurrentNode(head, index);
        //todo: 完成删除操作的颜色更新
        result.setKey(maintainRB(result.getKey()));
        return result;
    }

    private Node maintainRB(Node head) {
        if (head == null) return null;
        if (!isRed(head.left) && isRed(head.right))
            head = leftRotateColor(head);
        if (isRed(head.left) && isRed(head.left.right))
            head.left = leftRotateColor(head.left);
        if (isRed(head.left) && isRed(head.left.left))
            head = rightRotateColor(head);
        if (isRed(head.left) && isRed(head.right))
            flipColors(head);
        return head;
    }

    private boolean isRed(Node head) {
        if (head == null) return false; //默认一个空节点的颜色是黑色
        return head.color;
    }

    private Node leftRotateColor(Node head) {
        head = leftRotate(head);
        head.color = head.left.color;
        head.left.color = RED;
        return head;
    }

    private Node rightRotateColor(Node head) {
        head = rightRotate(head);
        head.color = head.right.color;
        head.right.color = RED;
        return head;
    }

    private void flipColors(Node head) {
        head.color = RED;
        head.left.color = BLACK;
        head.right.color = BLACK;
    }

    @Override
    String printCurrentNode(Node head, String to) {
        String isRed = (head.color) ? "Red" : "Black";
        return to + "-" + head.index + "-" + head.value + "-" + isRed + "-" + to;
    }

    public static void main(String[] args) {
        Tree<Integer, Integer> tree = new RedBlackTree<>();
        tree.insert(40);
        tree.insert(39);
        tree.insert(38);
        tree.insert(37);
        tree.insert(36);
        tree.delete(37);

    }
}
