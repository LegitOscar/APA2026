package assignment2;

import java.util.Random;


public class BubbleSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(1000000);
        }

        System.out.println("Before:");
        printArray(numbers);

        //Sorting algorithm
        //Goes from beginning to end of list
        //For each element it will check whether
        //it is out of order with the one after it our not.
        //If they are out of order we swap them and move on to the next 2.

        //Each time through this while loop it looks to see if swappedSomething is true.
        //And if it is true, it will keep going through the While loop
        //Setting the swappedSomething to true, so we can get into the while loop
        boolean swappedSomething = true;
        while (swappedSomething) {

            //We don't have anything to change the boolean so it will always be true
            // and therefore run forever, so we set swappedSomething to false,
            // We are telling the program that if you didn't need to swap anything, then it is in perfect order.
            //But if you did need to swap something, then you need to go through it again.
            swappedSomething = false;
            //We use - 1 after numbers.length because
            //When we are looking at the second to last element,
            //We will be comparing it to the one after it,
            //Which would be the last element, so there is no reason to
            //look at the last element by itself, there is nothing to compare it too.
            for (int i = 0; i < numbers.length - 1; i++) {
                //If the number at the index we are looking at is
                //greater than the next number.
                //Then they are out of order and we need to swap them.
                if (numbers[i] > numbers[i + 1]) {
                    //The moment we find out that we needed to swap something, we set swappedSomething to true.
                    //We start optimistically by saying "we didn't have to swap anything = boolean false".
                    //But then as we discover that something is out of order, and were gonna have to swap.
                    //We set swappedSomething to = boolean true.
                    //Then it goes out of the for loop and back up to the top of the while loop
                    //And checks if it had to swap something, and in this case it did, so we run it one more time.
                    swappedSomething = true;
                    // Here we use a temporary variable, so we can switch the positions of the elements.
                    // Here we switch so that index 0 becomes index 1, using the temp variable.
                    // Now the [i + 1] (1) index needs to be at the position of [i] (0)
                    // we do that be assigning the numbers[i + 1] to the temp variable.
                    //We use a temp, so we don't assign the same index to them both and lose our original [i] index.
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                }
            }
        }

        System.out.println("\nAfter:");
        printArray(numbers);

    }
        //Loop through each element in the Array and print it out.
    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

}
