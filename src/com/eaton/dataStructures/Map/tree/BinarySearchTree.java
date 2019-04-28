package com.eaton.dataStructures.Map.tree;

import com.eaton.dataStructures.Collections.Queue.Queue;
import com.eaton.dataStructures.Pair;

/**
 * 搜索二叉树, 同值键将被忽略
 *
 * @param <I>
 */
public class BinarySearchTree<I extends Comparable<I>, V> extends BinaryTree<I, V> {

    public void insert(I index) {
        insert(index, null);
    }

    /**
     * 插入一个节点
     *
     * @param index 节点的键
     * @param value 节点的值
     */
    @Override
    public void insert(I index, V value) {
        size++;
        Node newNode = new Node(index, value, null, null, null);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        while (current.left != null && current.right != null) {
            if (index.compareTo(current.index) < 0) current = current.left;
            else if (index.compareTo(current.index) > 0) current = current.right;
        }
        if (index.compareTo(current.index) == 0) {
            set(current.index, value);
            return;
        }
        newNode.parrent = current;
        if (index.compareTo(current.index) < 0) current.left = newNode;
        else current.right = newNode;
    }

    @Override
    public void insertAll(Tree<? extends I, ? extends V> items) {
        for (Pair<? extends I, ? extends V> pair : items.breadthFirst()) {
            insert(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public V find(I index) {
        Node current = root;
        while (current != null) {
            if (index.compareTo(current.index) == 0) return current.value;
            if (index.compareTo(current.index) < 0) current = current.left;
            else if (index.compareTo(current.index) > 0) current = current.right;
        }
        return null;
    }

    @Override
    protected Node findNode(I index) {
        Node current = root;
        while (current != null) {
            if (index.compareTo(current.index) == 0) return current;
            if (index.compareTo(current.index) < 0) current = current.left;
            else if (index.compareTo(current.index) > 0) current = current.right;
        }
        return null;
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

    /**
     * 删除一个索引的元素
     * @param head 递归头
     * @param index 待删除下标
     * @return 键值对，key为递归使用，value为待删除的节点的值。
     */
    private Pair<Node, Node> delete(Node head, I index) {
        Pair<Node, Node> result;
        if (head == null) return null;
        else if (index.compareTo(head.index) < 0) {
            result = delete(head.left, index);
            if (result == null) return null;
            head.left = result.getKey();
            result.setKey(head);
            return result;
        }
        else if (index.compareTo(head.index) > 0) {
            result = delete(head.left, index);
            if (result == null) return null;
            head.right = result.getKey();
            result.setKey(head);
            return result;
        }
        return deleteCurrentNode(head, index);
    }

    Pair<Node, Node> deleteCurrentNode(Node head, I index) {
        if (head.left == null && head.right == null) return new Pair<>(null, head);
        if (head.left == null) {
            Node right = head.right;
            head.right = null;
            size --;
            return new Pair<>(right, head);
        }
        if (head.right == null) {
            Node left = head.left;
            head.left = null;
            size --;
            return new Pair<>(left, head);
        }
        Node successor = getSuccessorNode(head);
        unLinkSuccessor(successor);
        successor.parrent = head.parrent;
        successor.left = head.left;
        successor.right = (head.right != successor) ? head.right : null;
        if (head.left != null) head.left.parrent = successor;
        if (head.right != null) head.right.parrent = successor;
        size --;
        return new Pair<>(successor, head);
    }

    private void unLinkSuccessor(Node successor) {
        if (isLeftChild(successor)) successor.parrent.left = null;
        else successor.parrent.right = null;
    }

    private boolean isLeftChild(Node head) {
        return head.parrent.left.equals(head);
    }

    public static void main(String[] args) {
        BinaryTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.insert(28, 1);
        tree.insert(9, 2);
        tree.insert(73, 3);
        tree.insert(8, 4);
        tree.insert(12, 5);
        tree.insert(7, 6);
        tree.insert(98, 8);
        tree.insert(98, 7499);
        tree.insert(60, 111);
        Queue queue = tree.postOrder();
//        System.out.println(tree.delete(28));
//        System.out.println(tree);
//        Integer result = tree.find(5444);
//        System.out.println(tree);
//        Queue queue = tree.breadthFirst();
//        System.out.println(queue);
//
//
//        BinarySearchTree<Integer, Integer> tree2 = new BinarySearchTree<>();
//        tree2.insert(11, 11);
//        tree2.insert(22, 11);
//        tree2.insert(33, 11);
//
//        tree.insertAll(tree2);

    }
}
