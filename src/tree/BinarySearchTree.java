package tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 搜索二叉树
 * @param <T>
 */
public class BinarySearchTree<T> extends BinaryTree<T> {

    /**
     * 插入一个节点
     * @param index 节点的键
     * @param value 节点的值
     */
    @Override
    public void insert(Integer index, T value) {
        Node newNode = new Node(index, value, null, null, null);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        while (current.left != null && current.right != null) {
            if (index < current.index) {
                current = current.left;
            } else if (index > current.index)
                current = current.right;
        }
        if (index == current.index) return;
        newNode.parrent = current;
        if (index < current.index) current.left = newNode;
        else current.right = newNode;
    }

    @Override
    public T find(Integer index) {
        Node current = root;
        while (current != null) {
            if (current.index.equals(index)) return current.value;
            if (index < current.index) current = current.left;
            else if (index > current.index) current = current.right;
        }
        return null;
    }

    @Override
    protected Node findNode(Integer index) {
        Node current = root;
        while (current != null) {
            if (current.index.equals(index)) return current;
            if (index < current.index) current = current.left;
            else if (index > current.index) current = current.right;
        }
        return null;
    }

    @Override
    public T delete(Integer index) {
        if (isEmpty()) throw new IllegalArgumentException("Tree is empty.");
        Node current = root;
        while (current != null) {
            if (current.index.equals(index)) break;
            if (index < current.index) current = current.left;
            else if (index > current.index) current = current.right;
        }
        if (current.right == null) {
            if (current.left == null) {}
        }
        throw new NotImplementedException();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(28, 1);
        tree.insert(9, 2);
        tree.insert(73, 3);
        tree.insert(8, 4);
        tree.insert(12, 5);
        tree.insert(7, 6);
        tree.insert(54, 7);
        tree.insert(98, 8);
        tree.insert(98, 9);
        tree.insert(98, 10);
        Integer result = tree.find(5444);
        tree.printTree();
    }
}
