package assignment3_algorithms.Wood;

public class WoodMain {
    public static void main(String[] args) {
        test(11);  // Expects [7, 2, 2]
        test(12);  // Expects [7, 5]
        test(13);  // Closest: [7, 5, 2] = 14? No — [7, 5] = 12, then 1 left, stuck → returns [7, 5]
        test(2);   // Expects [2]
        test(1);   // No piece fits at all → returns []
    }

    static void test(int target) {
        var result = WoodHandler.calculateWood(target);
        int sum = result.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Target: " + target + " → " + result + " (sum = " + sum + ")");
    }
}
