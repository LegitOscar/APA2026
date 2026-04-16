package assignment1.datastructures;

import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return this.age == p.age && this.name.equals(p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        TreeSet<Person> treeSet = new TreeSet<>();
        HashSet<Person> hashSet = new HashSet<>();

        // Create 100.000 persons to get meaningful benchmark results
        int size = 100000;
        Person[] people = new Person[size];
        for (int i = 0; i < size; i++) {
            people[i] = new Person("Person" + i, i);
        }

        // ADD - TreeSet O(log n), HashSet O(1)
        long start = System.nanoTime();
        for (Person p : people) treeSet.add(p);
        long end = System.nanoTime();
        System.out.println("TreeSet add: " + (end - start) + " ns");

        long start2 = System.nanoTime();
        for (Person p : people) hashSet.add(p);
        long end2 = System.nanoTime();
        System.out.println("HashSet add: " + (end2 - start2) + " ns");

        // REMOVE - TreeSet O(log n), HashSet O(1)
        long start3 = System.nanoTime();
        for (Person p : people) treeSet.remove(p);
        long end3 = System.nanoTime();
        System.out.println("TreeSet remove: " + (end3 - start3) + " ns");

        long start4 = System.nanoTime();
        for (Person p : people) hashSet.remove(p);
        long end4 = System.nanoTime();
        System.out.println("HashSet remove: " + (end4 - start4) + " ns");

        // Re-add before contains test, because remove emptied both sets
        for (Person p : people) treeSet.add(p);
        for (Person p : people) hashSet.add(p);

        // CONTAINS - TreeSet O(log n), HashSet O(1)
        long start5 = System.nanoTime();
        for (Person p : people) treeSet.contains(p);
        long end5 = System.nanoTime();
        System.out.println("TreeSet contains: " + (end5 - start5) + " ns");

        long start6 = System.nanoTime();
        for (Person p : people) hashSet.contains(p);
        long end6 = System.nanoTime();
        System.out.println("HashSet contains: " + (end6 - start6) + " ns");
    }
}