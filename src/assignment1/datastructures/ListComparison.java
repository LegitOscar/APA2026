package assignment1.datastructures;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListComparison {
    public static void main(String[] args){

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 500000; i++){
            arrayList.add(i);
            linkedList.add(i);
        }

        // GET
        // Imagine a numbered bookshelf where every book has a number on the shelf.
        // ArrayList is like that bookshelf - you can go directly to shelf number 250000
        // without looking at any other shelf. That is why it is O(1) - instant.
        //
        // LinkedList is like a treasure hunt where each clue only tells you
        // where the next clue is. To get to clue 250000, you have to follow
        // every single clue from the start. That is why it is O(n) - slow.
        long start = System.nanoTime();
        arrayList.get(250000);
        long end = System.nanoTime();
        System.out.println("Array get Tid: " + (end - start) + " ns");

        long start2 = System.nanoTime();
        linkedList.get(250000);
        long end2 = System.nanoTime();
        System.out.println("linked get Tid: " + (end2 - start2) + " ns");


        // ADD
        // Imagine inserting a new seat in the middle of a long row at a cinema.
        // ArrayList is like that row - every person sitting to the right of the
        // new seat has to physically move one seat further down to make room.
        // The more people there are, the longer it takes. That is O(n).
        //
        // LinkedList is like a chain of people holding hands. To insert someone
        // in the middle, you just break one pair of hands and connect the new
        // person in between. Only two connections change, no matter how long
        // the chain is. That is O(1) for the insertion itself.
        long start3 = System.nanoTime();
        arrayList.add(250000, 99);
        long end3 = System.nanoTime();
        System.out.println("Array add Tid: " + (end3 - start3) + " ns");

        long start4 = System.nanoTime();
        linkedList.add(250000);
        long end4 = System.nanoTime();
        System.out.println("Linked add Tid: " + (end4 - start4) + " ns");


        // REMOVE
        // Removing from an ArrayList is like pulling a book out from the middle
        // of a tightly packed bookshelf - every book to the right automatically
        // slides left to fill the gap. That takes time proportional to how many
        // books need to slide. That is O(n).
        //
        // Removing from a LinkedList is like removing a person from the chain -
        // the two neighbours just reach past the gap and grab each others hands.
        // Only two connections change regardless of the size of the chain. That is O(1).
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
