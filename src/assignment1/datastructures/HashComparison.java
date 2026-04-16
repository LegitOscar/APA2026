package assignment1.datastructures;

import java.util.ArrayList;
import java.util.HashSet;

public class HashComparison {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < 500000; i++) {
            arrayList.add(i);
            hashSet.add(i);
        }


        // CONTAINS - element exists
        // ArrayList is like looking for a specific face in a crowd by checking
        // every single person one by one from the left side until you find them.
        // If there are 500.000 people, you might have to check a lot of them. That is O(n).
        //
        // HashSet is like a venue where everyone is assigned a specific section
        // based on their name. To find someone, you just calculate their section
        // and go directly there - no searching needed. That is O(1).
        long start = System.nanoTime();
        arrayList.contains(250000);
        long end = System.nanoTime();
        System.out.println("ArrayList contains (exists): " + (end - start) + " ns");

        long start2 = System.nanoTime();
        hashSet.contains(250000);
        long end2 = System.nanoTime();
        System.out.println("HashSet contains (exists): " + (end2 - start2) + " ns");



        // CONTAINS - element does not exist
        // For ArrayList this is actually the worst possible case - since the value
        // is not there, it has to check every single person in the entire crowd
        // before it can conclude that the person simply is not there. Still O(n).
        //
        // For HashSet it makes no difference whether the value exists or not.
        // It calculates the section, looks there, and immediately knows the
        // answer. Not finding someone is just as fast as finding them. Still O(1).
        long start3 = System.nanoTime();
        arrayList.contains(999999);
        long end3 = System.nanoTime();
        System.out.println("ArrayList contains (doesn't exists): " + (end3 - start3) + " ns");

        long start4 = System.nanoTime();
        hashSet.contains(999999);
        long end4 = System.nanoTime();
        System.out.println("HashSet contains (doesn't exists): " + (end4 - start4) + " ns");


    }
}
