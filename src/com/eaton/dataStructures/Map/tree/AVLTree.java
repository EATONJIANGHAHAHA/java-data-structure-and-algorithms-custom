package com.eaton.dataStructures.Map.tree;

import com.eaton.dataStructures.Pair;
import com.sun.istack.internal.Nullable;

//todo: improve rotation.
public class AVLTree<I extends Comparable<I>, V> extends BinarySearchTree<I, V> {

    @Override
    public void insertAll(Tree<? extends I, ? extends V> items) {
        for (Pair<? extends I, ? extends V> pair : items) insert(pair.getKey(), pair.getValue());
    }

    @Override
    public void insert(I index) {
        insert(index, null);
    }

    @Override
    public void insert(I index, V value) {
        root = insert(root, null, index, value);
    }

    private Node insert(@Nullable Node head, @Nullable Node parrent, I index, V value) {
        if (head == null) {
            size ++;
            return new Node(index, value, parrent, null, null);
        }
        if (index.compareTo(head.index) > 0)
            head.right = insert(head.right, head, index, value);
        else if (index.compareTo(head.index) < 0)
            head.left = insert(head.left, head, index, value);
        else
            head.value = value;
        return maintainAVL(head);
    }

    private Node maintainAVL(Node head) {
        if (head == null) return null;
        head.height = getMaxHeight(head) + 1;
        int balanceFactor = getBalanceFactor(head);
        if (balanceFactor < -1 && getBalanceFactor(head.left) > 0) {
            head.left = leftRotate(head.left);
            return rightRotate(head);
        }
        else if (balanceFactor > 1 && getBalanceFactor(head.right) < 0) {
            head.right = rightRotate(head.right);
            return leftRotate(head);
        }
        else if (balanceFactor > 1) return leftRotate(head);
        else if (balanceFactor < -1) return rightRotate(head);
        return head;
    }

    @Override
    public V delete() {
        return delete(root.index);
    }

    @Override
    public V delete(I index) {
        Pair<Node, Node> result = delete(root, index);
        root = (result != null) ? result.getKey() : root;
        return (result != null) ? result.getValue().value : null;
    }

    @Override
    public I deleteARI() {
        Pair<Node, Node> result = delete(root, root.index);
        root = (result != null) ? result.getKey() : root;
        return result != null ? result.getValue().index : null;
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

        result.setKey(maintainAVL(result.getKey()));
        return result;
    }

    Node rightRotate(Node head) {
        Node x = head.left;
        Node t3 = x.right;
        x.parrent = head.parrent;
        x.right = head;
        head.parrent = x;
        head.left = t3;
        if (t3 != null) t3.parrent = head;
        refreshRotatedHeight(head, x);
        return x;
    }

    Node leftRotate(Node head) {
        Node x = head.right;
        Node t3 = x.left;
        x.parrent = head.parrent;
        x.left = head;
        head.parrent = x;
        head.right = t3;
        if (t3 != null) t3.parrent = head;
        refreshRotatedHeight(head, x);
        return x;
    }

    private void refreshRotatedHeight(Node head, Node x) {
        head.height = Math.max((head.left != null) ? head.left.height : 0,
                (head.right != null) ? head.right.height : 0) + 1;
        x.height = Math.max((x.left != null) ? x.left.height : 0,
                (x.right != null) ? x.right.height : 0) + 1;
    }

    private Integer getMaxHeight(Node head) {
        return Math.max((head.left != null) ? head.left.height : 0,
                (head.right != null) ? head.right.height : 0);
    }

    /**
     * 获取平衡因子。如果大于0, 则右边高度高， 如果小与0, 则左边高度高
     * @param head
     * @return
     */
    private int getBalanceFactor(Node head) {
        return ((head.right != null) ? head.right.height : 0) - ((head.left != null) ? head.left.height : 0);
    }

    public static void main(String[] args) {
        Tree<Integer, Integer> tree = new AVLTree<>();
        tree.insert(52);
        tree.insert(34);
        tree.insert(77);
        tree.insert(28);
        tree.insert(36);
        tree.insert(62);
        tree.insert(81);
        tree.insert(21);
        tree.insert(32);
        tree.insert(35);
        tree.insert(30);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);
        tree.insert(11);
        tree.insert(51);
        tree.insert(31);
        tree.insert(62);
        tree.insert(52);
        tree.insert(73);
        tree.insert(70);
        tree.insert(9);
        tree.insert(7);
        tree.insert(8);
        System.out.println(tree);
        tree.delete(30);
        System.out.println(tree);
//        tree.insert(10);
//        System.out.println(tree);
//        tree.insert(11);
//        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
//        System.out.println(tree);
//        tree.insert(12);
//        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
//        System.out.println(tree);
    }
}
