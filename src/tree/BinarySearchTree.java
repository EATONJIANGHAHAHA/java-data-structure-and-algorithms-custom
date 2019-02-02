package tree;

/**
 * 二叉搜索树,以key作为索引
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
        Node current = root, parrent = current.parrent;
        while (current != null) {
            parrent = current;
            if (key < current.index) current = current.left;
            else if (key > current.index)  current = current.right;
            else return;
        }
        current = parrent;
        newNode.parrent = current;
        if (key < current.index) current.left = newNode;
        else current.right = newNode;
    }

    /**
     * 寻找一个键的对应值
     * @param key 键
     * @return 值
     */
    public T findByKey(Integer key) {
        Node current = root;
        while (current != null) {
            if (current.index.equals(key)) return current.value;
            if (key < current.index) current = current.left;
            else current = current.right;
        }
        return null;
    }

    /**
     * 寻找一个键的对应节点
     * @param key
     * @return
     */
    protected Node node(Integer key) {
        Node current = root;
        while (current != null) {
            if (current.index.equals(key)) return current;
            if (key < current.index) current = current.left;
            else current = current.right;
        }
        return null;
    }

    /**
     * 删除一个节点
     * @param key
     */
    @Override
    public void delete(Integer key) {
        Node current = node(key);
        if (current == null) throw new IllegalArgumentException("Key not found.");
        if (current.right == null) {
            if (isLeftChild(current.parrent, current)) current.parrent.left = current.left;
            else current.parrent.right = current.left;
        } else {
            Node successer = removeMin(current.right);
            successer.left = current.left;
            successer.right = current.right;
            successer.parrent = current.parrent;
            if (current.left != null) current.left.parrent = successer;
            if (current.right != null) current.right.parrent = successer;
            if (!current.equals(root)) {
                if (isLeftChild(current.parrent, current)) current.parrent.left = successer;
                else current.parrent.right = successer;
            } else root = successer;
        }
    }

    private Node removeMin(Node head) {
        while (head.left != null)
            head = head.left;
        if (isLeftChild(head.parrent, head)) head.parrent.left = null;
        else head.parrent.right = null;
        return head;
    }

    private boolean isLeftChild(Node parrent, Node current) {
        return parrent.left.equals(current);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinarySearchTree();
        tree.insert(28, 1);
        tree.insert(9, 2);
        tree.insert(73, 3);
        tree.insert(8, 4);
        tree.insert(12, 5);
//        tree.insert(7, 6);
        tree.insert(54, 7);
        tree.insert(98, 8);
//        tree.insert(11, 9);
//        tree.insert(16, 10);
        tree.printTree();
        boolean result = tree.isBinarySearchTree();
        System.out.println(result);
    }
}
