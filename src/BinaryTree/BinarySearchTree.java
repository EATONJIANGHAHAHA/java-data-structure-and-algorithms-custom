package BinaryTree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 搜索二叉树
 * @param <T>
 */
public class BinarySearchTree<T> extends BinaryTree<T> {

    /**
     * 插入一个节点
     * @param key 节点的键
     * @param value 节点的值
     */
    @Override
    public void insert(Integer key, T value) {
        Node newNode = new Node(key, value, null, null, null);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        while (current.left != null && current.right != null) {
            if (key < current.index) {
                current = current.left;
            } else if (key > current.index) {
                current = current.right;
            }
        }
        newNode.parrent = current;
        if (key < current.index) current.left = newNode;
        else current.right = newNode;
    }

    @Override
    public void delete(Integer key) {
        throw new NotImplementedException();
    }
}
