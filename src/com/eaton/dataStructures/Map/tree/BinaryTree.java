package com.eaton.dataStructures.Map.tree;

import com.eaton.dataStructures.Collections.Queue.LinkedListQueue;
import com.eaton.dataStructures.Collections.Queue.Queue;
import com.eaton.dataStructures.Collections.stack.LinkedListStack;
import com.eaton.dataStructures.Collections.stack.Stack;
import com.eaton.dataStructures.Pair;
import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * A binary search DataStructures.Dict.tree implementation.
 * @param <I>
 */
public class BinaryTree<I, V> implements Tree<I, V>{

    Node root;
    protected int size;

    protected class Node {

        protected I index;
        protected V value;
        Node parrent;
        Node left;
        Node right;
        Integer height;
        boolean color;

        Node(I index, V value, @Nullable Node parrent, @Nullable Node left, @Nullable Node right) {
            this.index = index;
            this.value = value;
            this.parrent = parrent;
            this.left = left;
            this.right = right;
            height = 1;
            color = true; // represent red.
        }

    }

    /**
     * 迭代器默认使用宽度优先遍历
     */
    private class BTIterator implements Iterator<Pair<I, V>> {

        Queue<Node> parrents = new LinkedListQueue<>();
        Queue<Node> children = new LinkedListQueue<>();
        Node current;

        BTIterator() {
            super();
            current = root;
            if (current != null) parrents.offer(current);
        }

        @Override
        public boolean hasNext() {
            if (current == null) return false;
            return !parrents.isEmpty() || !children.isEmpty();
        }

        @Override
        public Pair<I, V> next() {
            if (current == null) throw new IllegalArgumentException();
            if (hasNext() && parrents.isEmpty()) {
                parrents = children;
                children = new LinkedListQueue<>();
            }
            current = parrents.poll();
            if (current.left != null) children.offer(current.left);
            if (current.right != null) children.offer(current.right);
            return new Pair<>(current.index, current.value);
        }
    }

    BinaryTree() {
        this.size = 0;
    }

    public Iterator<Pair<I, V>> iterator() {
        return new BTIterator();
    }

    /**
     * 寻找一个键的对应值
     * @param index 键
     * @return 值
     */
    @Override
    public V find(I index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return find(root, index);
    }

    @Override
    public Queue<Pair<I, V>> breadthFirst() { //todo:
        Queue<Node> parrents = new LinkedListQueue<>();
        Queue<Node> children = new LinkedListQueue<>();
        Queue<Pair<I, V>> result = new LinkedListQueue<>();
        parrents.offer(root);
        while (!parrents.isEmpty() || !children.isEmpty()) {
            while (!parrents.isEmpty()) {
                Node node = parrents.poll();
                result.offer(new Pair<>(node.index, node.value));
                if (node.left != null) children.offer(node.left);
                if (node.right != null) children.offer(node.right);
            }
            parrents = children;
            children = new LinkedListQueue<>();
        }
        return result;
    }

    @Override
    public boolean contains(I value) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return contains(root, value);
    }

    private boolean contains(Node head, I index) {
        if (head == null) return false;
        if (head.index.equals(index)) return true;
        boolean result = false;
        result = contains(head.left, index);
        if (result) return true;
        return contains(head.right, index);

    }

    private V find(Node head, I index) {
        if (head == null) return null;
        if (head.index.equals(index)) return head.value;
        V result = null;
        result = find(head.left, index);
        if (result != null) return result;
        return find(head.right, index);
    }

    /**
     * 寻找一个键的对应节点
     * @param index
     * @return
     */
    protected Node findNode(I index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return findNode(root, index);
    }

    private Node findNode(Node head, I index) {
        if (head == null) return null;
        if (head.index.equals(index)) return head;
        Node result = null;
        result = findNode(head.left, index);
        if (result != null) return result;
        return findNode(head.right, index);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(I index) {
        insert(index, null);
    }

    /**
     * 插入一个节点, 位置完全随机
     * @param key 节点的键
     * @param value 节点的值
     */
    @Override
    public void insert(I key, @Nullable V value) {
        if (root == null) {
            root = new Node(key, value, null, null, null);
            size ++;
            return;
        }
        Node current = root, parrent = null;
        int rightLeft = 0;
        while (current != null) {
            Random random = new Random();
            rightLeft = random.nextInt() % 2;
            parrent = current;
            if (rightLeft == 0) current = current.left;
            else if (rightLeft == 1) current = current.right;
        }
        Node newNode = new Node(key, value, parrent, null, null);
        if (rightLeft == 0) parrent.left = newNode;
        else parrent.right = newNode;
        size ++;
    }

    @Override
    public void insertAll(Tree<? extends I, ? extends V> items) {
        for (Pair<? extends I, ? extends V> pair : items) {
            insert(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 删除一个节点，叶节点对接选则完全随机
     * @param index
     */
    @Override
    public V delete(I index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        Node result = delete(root, index);
        return result == null ? null : result.value;
    }

    @Override
    public V delete() {
        return delete(root.index);
    }

    @Override
    public I deleteARI() {
        if (isEmpty()) throw new IllegalArgumentException();
        Node result = delete(root, root.index);
        return result == null ? null : result.index;
    }

    private Node delete(Node head, I index) {
        size --;
        if (head == null) return null;
        if (head.index.equals(index)) {
            if (isLeft(head.parrent, head)) head.parrent.left = null;
            else head.parrent.right = null;
            return head;
        }
        Node result = null;
        result = delete(head.left, index);
        if (result != null) return result;
        return delete(head.right, index);
    }

    private boolean isLeft(Node parrent, Node head) {
        return parrent.left.equals(head);
    }


    @Override
    public boolean set(I key, V newValue) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return set(root, key, newValue);
    }

    private boolean set(Node head, I index, V newValue){
        if (head == null) return false;
        if (head.index.equals(index)) {
            head.value = newValue;
            return true;
        }
        if (set(head.left, index, newValue)) return true;
        else if (set(head.right, index, newValue)) return true;
        else return false;
    }

    public LinkedListQueue<Pair<I, V>> preOrder() {
        LinkedListQueue<Pair<I, V>> result = new LinkedListQueue<>();
        if (root == null) return result;
        Stack<Node> nodes = new LinkedListStack<>();
        Node current;
        nodes.push(root);
        while (!nodes.isEmpty()) {
            current = nodes.pop();
            result.offer(new Pair<>(current.index, current.value));
            if (current.right != null) nodes.push(current.right);
            if (current.left != null) nodes.push(current.left);
        }
        return result;
    }

    @Override
    public LinkedListQueue<Pair<I, V>> inOrder() {
        LinkedListQueue<Pair<I, V>> result = new LinkedListQueue<>();
        if (root == null) return result;
        Stack<Node> nodes = new LinkedListStack<>();
        Node current = root;
        do {
            if (current == null) {
                current = nodes.pop();
                result.offer(new Pair<>(current.index, current.value));
                current = current.right;
            }
            if (current != null) {
                nodes.push(current);
                current = current.left;
            }
        } while (!nodes.isEmpty());
        return result;
    }

    public LinkedListQueue<Pair<I, V>> postOrder() {
        LinkedListQueue<Pair<I, V>> result = new LinkedListQueue<>();
        if (root == null) return result;
        Stack<Node> nodes = new LinkedListStack<>();
        Stack<Node> help = new LinkedListStack<>();
        Node current = root;
        nodes.push(current);
        while (!nodes.isEmpty()) {
            current = nodes.pop();
            help.push(current);
            if (current.left != null) nodes.push(current.left);
            if (current.right != null) nodes.push(current.right);
        }
        while (!help.isEmpty()) {
            current = help.pop();
            result.offer(new Pair<>(current.index, current.value));
        }
        return result;
    }

    /**
     * 返回后继节点
     *
     * @param node
     * @return
     */
    protected Node getSuccessorNode(Node node) {
        if (node.right != null) return getLeftMost(node.right);
        Node current = node, parrent = node.parrent;
        while (parrent != null && !parrent.left.equals(current)) {
            current = current.parrent;
            parrent = current.parrent;
        }
        return node;
    }

    protected Node getLeftMost(Node head) {
        Node current = head;
        while (current.left != null)
            current = current.left;
        return current;
    }

    /**
     * 返回前驱节点
     *
     * @param node
     * @return
     */
    public Node getPrecursorNode(Node node) {
        if (node.left != null) return getRightMost(node.left);
        Node current = node, parrent = node.parrent;
        while (parrent != null && !parrent.right.equals(current)) {
            current = current.parrent;
            parrent = current.parrent;
        }
        return node;
    }

    private Node getRightMost(Node head) {
        Node current = head;
        while (current.right != null)
            current = current.right;
        return current;
    }

    /**
     * 持久化树
     *
     * @return
     */
    public String seriolize() {
        String[] result = seriolizeRecur(root);
        return result[0] + "value:" + result[1];
    }

    private String[] seriolizeRecur(Node node) {
        String[] result = {"",""};
        if (node == null) {
            result[0] = "#_";
            result[1] = "#_";
            return result;
        }
        result[0] += node.index + "_";
        result[1] += node.value + "_";
        String[] temp = seriolizeRecur(node.left);
        result[0] += temp[0];
        result[1] += temp[1];
        temp = seriolizeRecur(node.right);
        result[0] += temp[0];
        result[1] += temp[1];
        return result;
    }

    /**
     * 反持久化树
     * @param serial
     */
    public void deSeriolizePreorder(String serial) {
        throw new NotImplementedException();
    }

    private void deSeriolizeRecur(String[] tree) {

    }

    /**
     * 打印一棵树, H表示头,^表示这个节点的头是在上一层的靠上位置, v表示这个节点的头是再上一层的考下位置.
     */
    private String printTree(Node head) {
        return "--------------------------------------------------------------------------------------------------\n"
                + printInOrder(head, 0, "H", 17).toString();
    }

    /**
     * 复写本方法以实现打印每一个节点的特定内容
     * @param head
     * @param to
     * @return
     */
    String printCurrentNode(Node head, String to) {
        return to + head.index + "," + head.value + to;
    }

    private StringBuilder printInOrder(Node head, int height, String to, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        if (head == null) {
            return stringBuilder;
        }
        stringBuilder.append(printInOrder(head.right, height + 1, "V", len));
        String val = printCurrentNode(head, to);
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        stringBuilder.append(getSpace(height * len) + val + "\n");
        stringBuilder.append(printInOrder(head.left, height + 1, "Λ", len));
        return stringBuilder;
    }

    private String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return printTree(root);
    }

    public static void main(String[] args) {

    }
}
