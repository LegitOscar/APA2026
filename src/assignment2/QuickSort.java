package assignment2;

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

        //The quicksort method takes 3 parameters
        //The first one is the actual array that it is going to sort.
        //We pass in a range with the lowIndex and highIndex of that particular subarray
        //So that it can properly do the recursive quick sorts.
        private static void quicksort(int[] array, int lowIndex, int highIndex){
            //As recursive calls keep going, we are gonna end up with one element at some point.
            //Because an array of one element is always sorted, we simply need to return that element.
        if (lowIndex >= highIndex) {
            return;
        }

            //We start the method by choosing a pivot, and for this one we are choosing the last element.
            int pivot = array[highIndex];

            //Now we need to do partitioning where we move all numbers lower than the pivot to the left
            //and all numbers higher than the pivot, to right og the pivot.
            int leftpointer = lowIndex;
            int rightpointer = highIndex;

            //We have the leftpointer which runs through and checks if any of the values are higher than our pivot
            //and the rightpointer checks if the int is lower than the pivot
            //We then swap the positions of the left and right on the numbers the pointers stopped on.
            //The left and right move closer together until they are on the same position and then that becomes our new pivot.
            while (leftpointer < rightpointer) {
                // This will keep incrementing the left pointer until the value of the array at that
                //left pointer is greater than the pivot, or until it is greater than the right pointer.
                while (array[leftpointer] <= pivot && leftpointer < rightpointer) {
                    leftpointer++;
                }
                //Same idea for the right pointer, that moves down the array instead of up.
                while (array[rightpointer] >= pivot && leftpointer < rightpointer) {
                    rightpointer--;
                }

                swap(array, leftpointer, rightpointer);

            }
            //We swap with highIndex here since we know that it is our pivot, cause we have always chosen the last element.
            swap(array, leftpointer, highIndex);
            //We now want to quicksort the left side of the pivot, and that's why we use leftpointer-1
            //so we move one step back from the pivot and only sort the element son the left.
            quicksort(array, lowIndex, leftpointer - 1);
            //Same logic here but on the right side of the pivot.
            quicksort(array, leftpointer + 1, highIndex);
        }

        //Helping method for swapping indexes.
        private static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        }

        private static void printArray(int[] numbers){
            for (int i = 0; i < numbers.length; i++){
                System.out.print(numbers[i] + " ");
            }

    }
}
