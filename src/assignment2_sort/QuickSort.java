package assignment2_sort;

import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(100);
        }

        System.out.println("Before: ");
        printArray(numbers);

        quicksort(numbers, 0, numbers.length - 1);

        System.out.println("\nAfter: ");
        printArray(numbers);
    }

    // O(n log n) average, O(n²) worst case - pivot is always last element
    private static void quicksort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) return; // base case: single element

        int pivot = array[highIndex];
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // move smaller elements left of pivot, larger elements right
        while (leftPointer < rightPointer) {
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) leftPointer++;
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) rightPointer--;
            swap(array, leftPointer, rightPointer);
        }

        swap(array, leftPointer, highIndex); // place pivot in final position

        quicksort(array, lowIndex, leftPointer - 1);  // sort left of pivot
        quicksort(array, leftPointer + 1, highIndex); // sort right of pivot
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}