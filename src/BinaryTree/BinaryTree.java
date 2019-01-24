package BinaryTree;

import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Stack;

/**
 * this is a binary search tree.
 * @param <T>
 */
public class BinaryTree<T> {

    private Node root;

    private class Node {

        private Integer key;
        private T data;
        private Node left;
        private Node right;

        private Node(Integer key, T data, @Nullable Node left, @Nullable Node right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public T find(Integer key) {
        Node current = root;
        while (true) {
            if (current.key.equals(key)) return current.data;
            if (key < current.key && current.left != null) current = current.left;
            else if (key > current.key && current.right != null) current = current.right;
            return null;
        }
    }

    public void insert(Integer key, T data) {
        Node newNode = new Node(key, data, null, null);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        while (true) {
            if (key < current.key) {
                if (current.left == null) break;
                current = current.left;
            } else if (key > current.key) {
                if (current.right == null) break;
                current = current.right;
            }
        }
        if (key < current.key) current.left = newNode;
        else if (key > current.key) current.right = newNode;
    }

    public void delete(Integer key) {
        throw new NotImplementedException();
    }

    public void preOrder() {
        if (root == null) return;
        Stack<Node> nodes = new Stack<>();
        Node current;
        nodes.push(root);
        while (!nodes.empty()) {
            current = nodes.pop();
            System.out.println(current.key);
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
                System.out.println(current.key);
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
            System.out.println(help.pop().key);
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
        tree.postOrder();
    }
}
