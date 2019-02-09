package tree;

import com.sun.istack.internal.Nullable;
import javafx.util.Pair;
import list.Queue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;
import java.util.Stack;

/**
 * A binary search tree implementation.
 * @param <T>
 */
public class BinaryTree<T> implements Tree<T>{

    protected Node root;

    public class Node {

        protected Integer index;
        protected T value;
        protected Node parrent;
        protected Node left;
        protected Node right;

        protected Node(Integer index, T value, @Nullable Node parrent, @Nullable Node left, @Nullable Node right) {
            this.index = index;
            this.value = value;
            this.parrent = parrent;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 寻找一个键的对应值
     * @param index 键
     * @return 值
     */
    @Override
    public T find(Integer index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return find(root, index);
    }

    @Override
    public Queue<Pair<Integer, T>> breadFirst() {
        throw new NotImplementedException();
    }

    private T find(Node head, Integer index) {
        if (head == null) return null;
        if (head.index.equals(index)) return head.value;
        T result = null;
        result = find(head.left, index);
        if (result != null) return result;
        return find(head.right, index);
    }

    /**
     * 寻找一个键的对应节点
     * @param index
     * @return
     */
    protected Node findNode(Integer index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return findNode(root, index);
    }

    private Node findNode(Node head, Integer index) {
        if (head == null) return null;
        if (head.index.equals(index)) return head;
        Node result = null;
        result = findNode(head.left, index);
        if (result != null) return result;
        return findNode(head.right, index);
    }

    public void insert(Integer key) {
        insert(key, null);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 插入一个节点, 位置完全随机
     * @param key 节点的键
     * @param value 节点的值
     */
    public void insert(Integer key, @Nullable T value) {
        if (root == null) {
            root = new Node(key, value, null, null, null);
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
    }

    /**
     * 删除一个节点，叶节点对接选则完全随机
     * @param index
     */
    public T delete(Integer index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return delete(root, index);
    }

    private T delete(Node head, Integer index) {
        if (head == null) return null;
        if (head.index.equals(index)) {
            if (isLeft(head.parrent, head)) head.parrent.left = null;
            else head.parrent.right = null;
            return head.value;
        }
        T result = null;
        result = delete(head.left, index);
        if (result != null) return result;
        return delete(head.right, index);
    }

    private boolean isLeft(Node parrent, Node head) {
        return parrent.left.equals(head);
    }


    public boolean set(Integer key, T newValue) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return set(root, key, newValue);
    }

    private boolean set(Node head, Integer index, T newValue){
        if (head == null) return false;
        if (head.index.equals(index)) {
            head.value = newValue;
            return true;
        }
        if (set(head.left, index, newValue)) return true;
        else if (set(head.right, index, newValue)) return true;
        else return false;
    }

    public Queue<Pair<Integer, T>> preOrder() {
        Queue<Pair<Integer, T>> result = new Queue<>();
        if (root == null) return result;
        Stack<Node> nodes = new Stack<>();
        Node current;
        nodes.push(root);
        while (!nodes.empty()) {
            current = nodes.pop();
            result.offer(new Pair<>(current.index, current.value));
            if (current.right != null) nodes.push(current.right);
            if (current.left != null) nodes.push(current.left);
        }
        return result;
    }

    public Queue<Pair<Integer, T>> inOrder() {
        Queue<Pair<Integer, T>> result = new Queue<>();
        if (root == null) return result;
        Stack<Node> nodes = new Stack<>();
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
        } while (!nodes.empty());
        return result;
    }

    public Queue<Pair<Integer, T>> postOrder() {
        Queue<Pair<Integer, T>> result = new Queue<>();
        if (root == null) return result;
        Stack<Node> nodes = new Stack<>();
        Stack<Node> help = new Stack<>();
        Node current = root;
        nodes.push(current);
        while (!nodes.empty()) {
            current = nodes.pop();
            help.push(current);
            if (current.left != null) nodes.push(current.left);
            if (current.right != null) nodes.push(current.right);
        }
        while (!help.empty())
            result.offer(new Pair<>(current.index, current.value));
        return result;
    }

    /**
     * 返回后继节点
     *
     * @param node
     * @return
     */
    public Node getSuccesserNode(Node node) {
        if (node.right != null) return getLeftMost(node.right);
        Node current = node, parrent = node.parrent;
        while (parrent != null && !parrent.left.equals(current)) {
            current = current.parrent;
            parrent = current.parrent;
        }
        return node;
    }

    public Node getLeftMost(Node head) {
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
    public void printTree() {
        printTree(root);
    }

    private void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "V", len);
        String val = to + head.index + "," + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "Λ", len);
    }

    public String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(28, 1);
        tree.insert(9, 1);
        tree.insert(73, 1);
        tree.insert(8, 1);
        tree.insert(12, 1);
        tree.insert(7, 1);
        tree.insert(54, 1);
        tree.insert(98, 1);
        tree.printTree();
        tree.set(999, 2);
        tree.printTree();
    }
}
