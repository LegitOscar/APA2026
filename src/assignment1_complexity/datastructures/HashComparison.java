package assignment1_complexity.datastructures;

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


        // CONTAINS (exists) - ArrayList O(n), HashSet O(1)
        long start = System.nanoTime();
        arrayList.contains(250000);
        long end = System.nanoTime();
        System.out.println("ArrayList contains (exists): " + (end - start) + " ns");

        long start2 = System.nanoTime();
        hashSet.contains(250000);
        long end2 = System.nanoTime();
        System.out.println("HashSet contains (exists): " + (end2 - start2) + " ns");



        // CONTAINS (missing) - ArrayList O(n) worst case, HashSet O(1)
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
