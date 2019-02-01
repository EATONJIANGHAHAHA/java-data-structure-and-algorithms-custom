package tree;

import java.util.Random;

public class BalancedBinaryTree<T> extends BinaryTree<T> {

    private class BalanceData {

        private boolean isBalanced;
        private int height;

        private BalanceData(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isBalanced(Node node) {
        BalanceData balanceData = isBalancedRecur(node);
        return balanceData.isBalanced;
    }

    private BalanceData isBalancedRecur(Node node) {
        if (node.left == null && node.right == null) return new BalanceData(true, 0);
        BalanceData leftTree = isBalancedRecur(node.left);
        if (!leftTree.isBalanced) return new BalanceData(false, 0);
        BalanceData rightTree = isBalancedRecur(node.right);
        if (!rightTree.isBalanced) return new BalanceData(false, 0);
        if (Math.abs(leftTree.height - rightTree.height) > 1) return new BalanceData(false, 0);
        return new BalanceData(true, Math.abs(leftTree.height - rightTree.height) + 1);
    }

    /**
     * 插入一个节点
     * @param key 节点的键
     * @param value 节点的值
     */
    @Override
    public void insert(Integer key, T value) {
        if (root == null) {
            root = new Node(key, value, null, null, null);
            return;
        }
        Node current = root;
        while (true) {
            Random random = new Random();
            if (current.left == null) break;
            if (current.right == null) break;
            int rightLeft = random.nextInt() % 2;
            if (rightLeft == 0) current = current.left;
            else if (rightLeft == 1) current = current.right;
        }
        Node newNode = new Node(key, value, current, null, null);
        if (current.left == null) current.left = newNode;
        else if (current.right == null) current.right = newNode;
    }

    public static void main(String[] args) {
        BalancedBinaryTree tree = new BalancedBinaryTree();
        tree.insert(28, 1);
        tree.insert(9, 1);
        tree.insert(73, 1);
        tree.insert(8, 1);
        tree.insert(12, 1);
        tree.insert(7, 1);
        tree.insert(54, 1);
        tree.insert(98, 1);
        tree.printTree();
    }
}
