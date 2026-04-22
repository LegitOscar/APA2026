package assignment2;

import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(1000);
        }


        System.out.println("Before: ");
        printArray(numbers);

        mergeSort(numbers);

        System.out.println("\nAfter: ");
        printArray(numbers);

    }

    private static void mergeSort(int[] inputArray) {
        //Making a variable for the length of the input array
        //since we are gonna need it, many times through this algorithm.
        //We are gonna be calling this mergeSort method recursively,
        //Meaning we start calling it on the "Large" array and then keep calling it
        //as it splits down to smaller and smaller arrays, until we are calling it on just 1 element.
        //Arrays with 1 element are already sorted
        int inputLength = inputArray.length;

        //So we check if we have an empty array or just an array with one element in it.
        //And then return it, if that statement is true.
        if (inputLength < 2) {
            return;
        }
        //But if that is not the case, and we have 2 or more elements in the array then we continue.
        //So the next thing to do in our algorithm is to divide the array into 2 arrays.
        //Before we can do that, we need the midpoint of our array, which is half of the input length.
        int midIndex = inputLength / 2;
        //So here we say that the left side of the array should be from index 0 to the midpoint (5).
        int[] leftHalf = new int[midIndex];
        //We use the [inputLength - midIndex], to compensate for uneven numbers of elements.
        //If we didn't do this and the array had 9 instead of 10, then it would become 4 and not 5.
        int[] rightHalf = new int[inputLength - midIndex];

        //The arrays are empty
        //Now we need to populate the arrays with the elements from the original larger input array.
        //LeftHalf
        //We are looping from zero to the length of our left half.
        for (int i = 0; i < midIndex; i++) {
            //now we set the start index of this array to be the same as the original arrays.
            leftHalf[i] = inputArray[i];
        }
        //Here we are starting from the midpoint and ending at the inputLength, filling up the right half
        for (int i = midIndex; i < inputLength; i++) {
            //Here we have [i - midIndex], which in this instance would be the same as saying 5 - 5.
            //Meaning that we start on the 0 index, and then it increments, and we move onto 1, 2, 3 and so forth.
            //So we end up with filling the right half array with the original elements og the input array.
            rightHalf[i - midIndex] = inputArray[i];
        }
        //The mergeSort method is the one we are writing
        //So now we need to recursively call it with our left and right halves.
        //They should now be 2 completely sorted halves.
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        //Now we call our merge method, with the left and right half arrays.
        merge(inputArray, leftHalf, rightHalf);

    }

    //Method for merging the sorted halves of the array
    private static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        //Now that we got the sizes of the left and right sided arrays.
        //We need to loop through the left and right (from lowest to highest)
        //Because they are already in sorted order.
        //We are comparing the first elements of each and adding the lower one to our merged array.
        //We are gonna keep doing this until we run out of elements in our left and right arrays.
        //We need 3 variables, one for walking through the left, one for the right and one for the merged array.
        // i = left, j = right, k = merged
        int i = 0, j = 0, k = 0;

        //Looping until we run out of elements on the left or right array.
        while (i < leftSize && j < rightSize) {
            //If leftHalf at [i] is less than or equal to rightHalf of [j]
            //Then that is the smaller of the two numbers, and we add it (if equal, just add from left)
            if (leftHalf[i] <= rightHalf[j]) {
                //Now we add it to the merged array
                inputArray[k] = leftHalf[i];
                //Now that we have added it, we increment i, so we can look at the next element in the leftHand array.
                i++;
        }
            //if this doesn't happen then we know that the element on the right side must be smaller.
            else {
                //So now we add the j elements of our right half to our merged array.
                inputArray[k] = rightHalf[j];
                //since adding the element from the right hand array we now need to incriment j
                j++;
            }
            //It doesn't matter if we added from the right or the left array.
            //We still need to increment the combined array (the merged one).
            //So we can add the next element to the next index.
            k++;
        }
        //When we run out of elements in the left or right array.
        //We still have some numbers left in whichever array we didn't reach end of.
        //So we "clean up" and add the elements that are still remaning.

        //While loop for if the element is in the left array.
        //It would bypass this if there wasn't anything in the array to grab.
        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }
        //Same logic as above, but for the rightHalf of the array.
        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }


    }

    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
