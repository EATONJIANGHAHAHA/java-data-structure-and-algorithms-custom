package Collections.tree;

import Collections.Queue.Queue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 搜索二叉树, 同值键将被忽略
 * @param <I>
 */
public class BinarySearchTree<I extends Comparable<I>, V> extends BinaryTree<I, V> {

    /**
     * 插入一个节点
     * @param index 节点的键
     * @param value 节点的值
     */
    @Override
    public void insert(I index, V value) {
        Node newNode = new Node(index, value, null, null, null);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        while (current.left != null && current.right != null) {
            if (index.compareTo(current.index) < 0) {
                current = current.left;
            } else if (index.compareTo(current.index) > 0)
                current = current.right;
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
    public V delete(I index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        Node current = root;
        while (current != null) {
            if (index.compareTo(current.index) == 0) break;
            if (index.compareTo(current.index) < 0) current = current.left;
            else if (index.compareTo(current.index) > 0) current = current.right;
        }
        if (current.right == null) {
            if (current.left == null) {}
        }
        throw new NotImplementedException();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.insert(28, 1);
        tree.insert(9, 2);
        tree.insert(73, 3);
        tree.insert(8, 4);
        tree.insert(12, 5);
        tree.insert(7, 6);
        tree.insert(54);
        tree.insert(98, 8);
        tree.insert(98, 7499);
        tree.insert(60, 111);
        Integer result = tree.find(5444);
        System.out.println(tree);
        Queue queue = tree.breadthFirst();
        System.out.println(queue);
    }
}
