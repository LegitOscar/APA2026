package assignment1.complexity;

import java.util.ArrayList;

public class Main {

    public static void testComplexity() {
        // Test with different sizes of n to show how each complexity scales
        int[] sizes = {1000, 5000, 10000};

        for (int n : sizes) {
            System.out.println("\n--- n = " + n + " ---");

            // Set up test data
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                numbers.add(i);
            }

            ArrayList<String> months = new ArrayList<>();
            months.add("April"); months.add("August"); months.add("December");
            months.add("February"); months.add("January"); months.add("July");
            months.add("June"); months.add("March"); months.add("May");
            months.add("November"); months.add("October"); months.add("September");

            // O(1)
            long start = System.nanoTime();
            BigOExamples.getByIndex(numbers, n / 2);
            long end = System.nanoTime();
            System.out.println("O(1)      getByIndex:     " + (end - start) + " ns");

            // O(log n)
            long start2 = System.nanoTime();
            BigOExamples.getMonth(months, "September");
            long end2 = System.nanoTime();
            System.out.println("O(log n)  getMonth:       " + (end2 - start2) + " ns");

            // O(n)
            long start3 = System.nanoTime();
            BigOExamples.getSum(numbers);
            long end3 = System.nanoTime();
            System.out.println("O(n)      getSum:         " + (end3 - start3) + " ns");

            // O(n²) - commented out print inside findDuplicates keeps output clean
            long start4 = System.nanoTime();
            BigOExamples.findDuplicates(numbers);
            long end4 = System.nanoTime();
            System.out.println("O(n²)     findDuplicates: " + (end4 - start4) + " ns");
        }
    }

    public static void main(String[] args) {
        testComplexity();
    }
}