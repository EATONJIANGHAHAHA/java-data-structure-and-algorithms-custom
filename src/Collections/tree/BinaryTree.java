package Collections.tree;

import Collections.Queue.ListQueue;
import Collections.Queue.Queue;
import Collections.stack.LinkedListStack;
import Collections.stack.Stack;
import com.sun.istack.internal.Nullable;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.Random;

/**
 * A binary search Collections.tree implementation.
 * @param <I>
 */
public class BinaryTree<I, V> implements Tree<I,V>{

    protected Node root;
    protected int size;

    public class Node {

        protected I index;

        protected V value;
        protected Node parrent;
        protected Node left;
        protected Node right;
        protected Node(I index, V value, @Nullable Node parrent, @Nullable Node left, @Nullable Node right) {
            this.index = index;
            this.value = value;
            this.parrent = parrent;
            this.left = left;
            this.right = right;
        }

    }

    public BinaryTree() {
        this.size = 0;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
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
        Queue<Node> parrents = new ListQueue<>();
        Queue<Node> children = new ListQueue<>();
        Queue<Pair<I, V>> result = new ListQueue<>();
        parrents.offer(root);
        while (!parrents.isEmpty() || !children.isEmpty()) {
            while (!parrents.isEmpty()) {
                Node node = parrents.poll();
                result.offer(new Pair<>(node.index, node.value));
                if (node.left != null) children.offer(node.left);
                if (node.right != null) children.offer(node.right);
            }
            parrents = children;
            children = new ListQueue<>();
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

    public void insert(I key) {
        insert(key, null);
    }

    /**
     * 插入一个节点, 位置完全随机
     * @param key 节点的键
     * @param value 节点的值
     */
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
    public int size() {
        return size;
    }

    /**
     * 删除一个节点，叶节点对接选则完全随机
     * @param index
     */
    public V delete(I index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        return delete(root, index);
    }

    @Override
    public V delete() {
        return delete(root.index);
    }

    private V delete(Node head, I index) {
        size --;
        if (head == null) return null;
        if (head.index.equals(index)) {
            if (isLeft(head.parrent, head)) head.parrent.left = null;
            else head.parrent.right = null;
            return head.value;
        }
        V result = null;
        result = delete(head.left, index);
        if (result != null) return result;
        return delete(head.right, index);
    }

    private boolean isLeft(Node parrent, Node head) {
        return parrent.left.equals(head);
    }


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

    public ListQueue<Pair<I, V>> preOrder() {
        ListQueue<Pair<I, V>> result = new ListQueue<>();
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

    public ListQueue<Pair<I, V>> inOrder() {
        ListQueue<Pair<I, V>> result = new ListQueue<>();
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

    public ListQueue<Pair<I, V>> postOrder() {
        ListQueue<Pair<I, V>> result = new ListQueue<>();
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
        while (!help.isEmpty())
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

    private String printTree(Node head) {
        return printInOrder(head, 0, "H", 17).toString();
    }

    private StringBuilder printInOrder(Node head, int height, String to, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        if (head == null) {
            return stringBuilder;
        }
        stringBuilder.append(printInOrder(head.right, height + 1, "V", len));
        String val = to + head.index + "," + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        stringBuilder.append(getSpace(height * len) + val + "\n");
        stringBuilder.append(printInOrder(head.left, height + 1, "Λ", len));
        return stringBuilder;
    }

    public String getSpace(int num) {
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
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();
        tree.insert(28, 1);
        tree.insert(9, 1);
        tree.insert(73, 1);
        tree.insert(8, 1);
        tree.insert(12, 1);
        tree.insert(7, 1);
        tree.insert(54, 1);
        tree.insert(98, 1);
        System.out.println(tree);
    }
}
