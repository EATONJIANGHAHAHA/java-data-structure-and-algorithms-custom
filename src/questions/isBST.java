package questions;

import java.util.LinkedList;
import java.util.Scanner;

public class isBST {

    protected class Node {
        int index;
        Node left;
        Node right;
        Node(int index, Node left, Node right) {
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }

    public static void sovle() {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> parrent = new LinkedList<>();
        LinkedList<Integer> children = new LinkedList<>();
        int numCurNodes = 1, current = 0, currentIndex = 0;
        String[] input = scanner.nextLine().split(" ");
        for (int i = 0; i < input.length; i ++) {
            current = Integer.parseInt(input[i]);
            children.add(current);
            if (notBST(current, parrent, children, currentIndex)){
                System.out.println("False");
                return;
            }
            currentIndex++;
            if (numCurNodes == children.size()) {
                parrent = children;
                children = new LinkedList<>();
                currentIndex = 0;
                numCurNodes *= 2;
            }
        }
        System.out.println("True");
    }

    private static boolean notBST(int current, LinkedList<Integer> parrent, LinkedList<Integer> children, int currentIndex) {
        if (parrent.size() == 0) return false;
        if (currentIndex % 2 == 0) { //左孩子
            if (current < parrent.get(currentIndex / 2)) {
                return false;
            }
            else return true;
        } else {
            //右孩子
            if (current > parrent.get(currentIndex / 2) &&
            current > children.get(currentIndex - 1)) return false;
            else return true;
        }

    }

    public static void main(String[] args) {
        sovle();
    }
}
