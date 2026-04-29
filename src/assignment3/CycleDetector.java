package assignment3;

public class CycleDetector {

    // Bygger en liste af int-værdier
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

    // Vi laver en cyklisk liste
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
        n5.next = n2; // Laver cyklus
        return n1;
    }

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
                System.out.println(">>> Slow and Fast met at node: " + slow.value + " - CYCLE DETECTED, breaking out!");
                return true;
            }
            step++;
        }

        System.out.println(">>> Fast reached the end of the list - NO CYCLE, breaking out!");
        return false;
    }


}
