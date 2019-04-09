package questions;

import java.util.HashSet;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] nodes = s.split(",");
        Node head = new Node(nodes[0].charAt(0));
        Node node = head;
        for (int i = 1; i < nodes.length; i ++) {
            node.next = new Node(nodes[i].charAt(0));
            node = node.next;
        }
        System.out.println(hasLoop(head));
    }

    private static boolean hasLoop(Node head) {
        Node node = head;
        HashSet<Character> set = new HashSet<>();
        while (node != null) {
            if (!set.add(node.val)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    static class Node {
        char val;
        Node next;
        public Node (char val) {
            this.val = val;
        }
    }
}
