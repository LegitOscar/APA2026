package assignment1.complexity;

import java.util.ArrayList;

public class BigOExamples {

    // O(1) - direct index lookup, constant time regardless of list size
    public static int getByIndex(ArrayList<Integer> numbers, int ID) {
        return numbers.get(ID);
    }

    // O(n) - loops through every element once
    public static int getSum(ArrayList<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); ++i) {
            sum = sum + numbers.get(i);
        }
        return sum;
    }

    // O(log n) - binary search, halves the search space each iteration
    // List must be alphabetically sorted for this to work
    public static String getMonth(ArrayList<String> month, String targetMonth) {
        int min = 0;
        int max = month.size() - 1;
        int middle = (max + min) / 2;

        while (min <= max) {
            if (month.get(middle).equals(targetMonth)) {
                return targetMonth;
            } else if (targetMonth.compareTo(month.get(middle)) > 0) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
            middle = (max + min) / 2;
        }
        return targetMonth;
    }

    // O(n²) - nested loop, checks every element against every other element
    public static void findDuplicates(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).equals(numbers.get(j))) {
                    //Commented out so it dosent flood the output in Main
                    //System.out.println("Duplicate found: " + numbers.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 10000;

        // Set up test data
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }

        ArrayList<String> months = new ArrayList<>();
        months.add("April");
        months.add("August");
        months.add("December");
        months.add("February");
        months.add("January");
        months.add("July");
        months.add("June");
        months.add("March");
        months.add("May");
        months.add("November");
        months.add("October");
        months.add("September");

        // O(1)
        long start = System.nanoTime();
        getByIndex(numbers, 5000);
        long end = System.nanoTime();
        System.out.println("O(1)      getByIndex:     " + (end - start) + " ns");

        // O(log n)
        long start2 = System.nanoTime();
        getMonth(months, "September");
        long end2 = System.nanoTime();
        System.out.println("O(log n)  getMonth:       " + (end2 - start2) + " ns");

        // O(n)
        long start3 = System.nanoTime();
        getSum(numbers);
        long end3 = System.nanoTime();
        System.out.println("O(n)      getSum:         " + (end3 - start3) + " ns");

        // O(n²)
        long start4 = System.nanoTime();
        findDuplicates(numbers);
        long end4 = System.nanoTime();
        System.out.println("O(n²)     findDuplicates: " + (end4 - start4) + " ns");
    }

}