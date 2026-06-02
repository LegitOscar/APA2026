package assignment3_algorithms;

public class CycleDetector {

    // builds a linked list from the given values
    public static Node buildList(int... values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    // builds a cyclic list: 1 -> 2 -> 3 -> 4 -> 5 -> back to 2
    public static Node buildListWithCycle() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n2; // creates cycle
        return n1;
    }

    // Floyd's cycle detection — slow moves 1 step, fast moves 2
    // if they meet, there is a cycle; if fast reaches null, there isn't
    public static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        if (head == null || head.next == null) return false;

        int step = 1;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            System.out.println("Step " + step + " | Slow: " + slow.value + " | Fast: " + fast.value);

            if (slow == fast) {
                System.out.println(">>> Met at node: " + slow.value + " - CYCLE DETECTED");
                return true;
            }
            step++;
        }

        System.out.println(">>> Fast reached end - NO CYCLE");
        return false;
    }
}