package assignment3;

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








       /* Node list = CycleDetector.buildList(1, 2, 3, 4, 5);
        System.out.println("Normal list has cycle: " + CycleDetector.hasCycle(list));
        //  if(!hasCircle(list))
        //System.out.println(list);

        Node circularList = CycleDetector.buildListWithCycle();
        System.out.println("Circular List has cycle: " + CycleDetector.hasCycle(circularList));
        //   if(!hasCircle(circularList))
        //System.out.println(circularList);
        */

    }
}