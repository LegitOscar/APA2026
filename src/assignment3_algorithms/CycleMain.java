package assignment3_algorithms;

public class CycleMain {

    public static void main(String[] args) {

            Node list = CycleDetector.buildList(1, 2, 3, 4, 5);
            System.out.println("--- Testing Normal List ---");
            boolean result1 = CycleDetector.hasCycle(list);
            System.out.println("Result: " + (result1 ? "Cycle detected" : "No cycle found"));

            System.out.println();

            Node circularList = CycleDetector.buildListWithCycle();
            System.out.println("--- Testing Circular List ---");
            boolean result2 = CycleDetector.hasCycle(circularList);
            System.out.println("Result: " + (result2 ? "Cycle detected" : "No cycle found"));

    }
}