package assignment1_complexity.datastructures;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListComparison {
    public static void main(String[] args){

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 500000; i++){
            arrayList.add(i);
            linkedList.add(i);
        }

        // GET - ArrayList O(1), LinkedList O(n)
        long start = System.nanoTime();
        arrayList.get(250000);
        long end = System.nanoTime();
        System.out.println("Array get Tid: " + (end - start) + " ns");

        long start2 = System.nanoTime();
        linkedList.get(250000);
        long end2 = System.nanoTime();
        System.out.println("linked get Tid: " + (end2 - start2) + " ns");


        // ADD (middle) - ArrayList O(n), LinkedList O(n) to find + O(1) to insert
        long start3 = System.nanoTime();
        arrayList.add(250000, 99);
        long end3 = System.nanoTime();
        System.out.println("Array add Tid: " + (end3 - start3) + " ns");

        long start4 = System.nanoTime();
        linkedList.add(250000, 99);
        long end4 = System.nanoTime();
        System.out.println("Linked add Tid: " + (end4 - start4) + " ns");


        // REMOVE (middle) - ArrayList O(n), LinkedList O(n) to find + O(1) to remove
        long start5 = System.nanoTime();
        arrayList.remove(250000);
        long end5 = System.nanoTime();
        System.out.println("Array remove Tid: " + (end5 - start5) + " ns");


        long start6 = System.nanoTime();
        linkedList.remove(250000);
        long end6 = System.nanoTime();
        System.out.println("Linked remove Tid: " + (end6 - start6) + " ns");
    }
}
