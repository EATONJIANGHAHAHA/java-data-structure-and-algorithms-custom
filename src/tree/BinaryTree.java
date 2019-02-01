package tree;

import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * A binary search tree implementation.
 * @param <T>
 */
public class BinaryTree<T> {

    protected Node root;

    protected class Node {

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
     * @param key 键
     * @return 值
     */
    protected T findByKey(Integer key) {
        return findByKey(root, key);
    }

    private T findByKey(Node head, Integer key) {
        if (head == null) return null;
        if (head.index.equals(key)) return head.value;
        T result = findByKey(head.left, key);
        if (result != null) return result;
        return findByKey(head.right, key);
    }

    /**
     * 寻找一个键的对应节点
     * @param key
     * @return
     */
    protected Node node(Integer key) {
        return node(root, key);
    }

    private Node node(Node head, Integer key) {
        if (head == null) return null;
        if (head.index.equals(key)) return head;
        Node result = node(head.left, key);
        if (result != null) return result;
        return node(head.right, key);
    }

    /**
     * 插入一个节点, 位置完全随机
     * @param key 节点的键
     * @param value 节点的值
     */
    public void insert(Integer key, T value) {
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

    public void delete(Integer key) {
        throw new NotImplementedException();
    }

    public Queue<Node> breadthFirstTraversal() {
        Queue<Node> currentLevel = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();
        Queue<Node> result = new LinkedList<>();
        currentLevel.offer(root);
        while (!currentLevel.isEmpty()) {
            while (!currentLevel.isEmpty()) {
                Node current = currentLevel.poll();
                if (current.left != null) nextLevel.offer(current.left);
                if (current.right != null) nextLevel.offer(current.right);
                result.offer(current);
            }
            currentLevel = nextLevel;
            nextLevel = new LinkedList<>();
        }
        return result;
    }

    public void preOrder() {
        if (root == null) return;
        Stack<Node> nodes = new Stack<>();
        Node current;
        nodes.push(root);
        while (!nodes.empty()) {
            current = nodes.pop();
            System.out.println(current.index);
            if (current.right != null) nodes.push(current.right);
            if (current.left != null) nodes.push(current.left);
        }
    }

    public void inOrder() {
        if (root == null) return;
        Stack<Node> nodes = new Stack<>();
        Node current = root;
        do {
            if (current == null) {
                current = nodes.pop();
                System.out.println(current.index);
                current = current.right;
            }
            if (current != null) {
                nodes.push(current);
                current = current.left;
            }
        } while (!nodes.empty());
    }

    public void postOrder() {
        if (root == null) return;
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
            System.out.println(help.pop().index);
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

    private Node getLeftMost(Node head) {
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
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.index + "," + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "∧", len);
    }

    public String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public boolean isCompleteBinaryTree() {
        Queue<Node> traversal = breadthFirstTraversal();
        boolean stage = false;
        for (Node node: traversal) {
            if (!stage) {
                if (node.left == null && node.right != null) return false;
                if (isStageTwo(node)) stage = true;
            } else
                if (!isLeave(node)) return false;
        }
        return true;
    }

    private boolean isLeave(Node node) {
        return node.left == null || node.right == null;
    }

    private boolean isStageTwo(Node node) {
        return isLeave(node) || node.left != null && node.right == null;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(28, 1);
        tree.insert(9, 1);
        tree.insert(73, 1);
        tree.insert(8, 1);
        tree.insert(12, 1);
        tree.insert(7, 1);
        tree.insert(54, 1);
        tree.insert(98, 1);
        tree.printTree();
        Integer result = (Integer) tree.findByKey(12);
        System.out.println(result);
    }
}
