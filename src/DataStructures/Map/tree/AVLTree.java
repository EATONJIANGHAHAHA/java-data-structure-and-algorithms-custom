package DataStructures.Map.tree;

import com.sun.istack.internal.Nullable;

public class AVLTree<I extends Comparable<I>, V> extends BinarySearchTree<I, V> {

    @Override
    public void insert(I index, V value) {
        root = insert(root, null, index, value);
    }

    private Node insert(@Nullable Node head, @Nullable Node parrent, I index, V value) {
        if (head == null)
            return new Node(index, value, parrent, null, null);
        if (index.compareTo(head.index) > 0)
            head.right = insert(head.right, head, index, value);
        else if (index.compareTo(head.index) < 0)
            head.left = insert(head.left, head, index, value);
        else
            head.value = value;
        return balanceMaintain(head);
    }

    private Node balanceMaintain(Node head) {
        head.height = getMaxHeight(head) + 1;
        int balanceFactor = getBalanceFactor(head);
        if (balanceFactor > 1) return leftRotate(head);
        else if (balanceFactor < -1) return rightRotate(head);
        return head;
    }

    @Override
    public V delete(I index) {
        root = delete(root, index);
        return null;
    }

    private Node delete(Node head, I index) {
        Node replaced;
        if (head == null) return null;
        else if (index.compareTo(head.index) < 0) {
            head.left = delete(head.left, index);
            replaced = head;
        }
        else if (index.compareTo(head.index) > 0) {
            head.right = delete(head.right, index);
            replaced = head;
        }
        else
            replaced = deleteCurrentNode(head, index);
        return balanceMaintain(replaced);
    }

    private Node rightRotate(Node head) {
        Node x = head.left;
        Node t3 = x.right;
        if (t3 == null) return head;
        x.parrent = head.parrent;
        x.right = head;
        head.parrent = x;
        head.left = t3;
        t3.parrent = head;
        refreshRotatedHeight(head, x);
        return x;
    }

    private Node leftRotate(Node head) {
        Node x = head.right;
        Node t3 = x.left;
        if (t3 == null) return head;
        x.parrent = head.parrent;
        x.left = head;
        head.parrent = x;
        head.right = t3;
        t3.parrent = head;
        refreshRotatedHeight(head, x);
        return x;
    }

    private void refreshRotatedHeight(Node head, Node x) {
        head.height = Math.max((head.left != null) ? head.left.height : 0,
                (head.right != null) ? head.right.height : 0) + 1;
        x.height = Math.max((x.left != null) ? x.left.height : 0,
                (x.right != null) ? x.right.height : 0) + 1;
    }

    private Integer getMaxHeight(Node head) {
        return Math.max((head.left != null) ? head.left.height : 0,
                (head.right != null) ? head.right.height : 0);
    }

    /**
     * 获取平衡因子。如果大于0, 则右边高度高， 如果小与0, 则左边高度高
     * @param head
     * @return
     */
    private int getBalanceFactor(Node head) {
        return ((head.right != null) ? head.right.height : 0) - ((head.left != null) ? head.left.height : 0);
    }

    public static void main(String[] args) {
        Tree<Integer, Integer> tree = new AVLTree<>();
        tree.insert(52);
        tree.insert(34);
        tree.insert(77);
        tree.insert(28);
        tree.insert(36);
        tree.insert(62);
        tree.insert(81);
        tree.insert(21);
        tree.insert(32);
        tree.insert(35);
        tree.insert(30);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);
        tree.insert(11);
        tree.insert(51);
        tree.insert(31);
        tree.insert(62);
        tree.insert(52);
        tree.insert(73);
        tree.insert(70);
//        tree.insert(12);
//        tree.insert(8);
//        tree.insert(10);
        System.out.println(tree);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        tree.delete(77);
        System.out.println(tree);
    }
}
