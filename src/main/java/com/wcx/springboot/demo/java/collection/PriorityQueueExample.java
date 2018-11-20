package com.wcx.springboot.demo.java.collection;

import java.util.*;

public class PriorityQueueExample {
    /**
     * A priority queue says that the element that goes next is the one with the greatest need (the
     * highest priority)
     * When you offer( ) an object onto a PriorityQueue, that object is sorted into the queue.5
     * The default sorting uses the natural order of the objects in the queue, but you can modify the
     * order by providing your own Comparator. The PriorityQueue ensures that when you call
     * peek( ), poll( ) or remove( ), the element you get will be the one with the highest priority.
     * @param args
     */
    public static void main(String[] args) {
        /*You can see that duplicates are allowed, and the lowest values have the highest priority (in
                the case of String, spaces also count as values and are higher in priority than letters). To
        show how you can change the ordering by providing your own Comparator object, the third
        constructor call to PriorityQueue<Integer> and the second call to
        PriorityQueue<String> use the reverse-order Comparator produced by
        Collections.reverseOrder( )*/
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<Integer>();
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++)
            priorityQueue.offer(rand.nextInt(i + 10));
        QueueExample.printQ(priorityQueue);
        List<Integer> ints = Arrays.asList(25, 22, 20,
                18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<Integer>(ints);
        QueueExample.printQ(priorityQueue);
        priorityQueue = new PriorityQueue<Integer>(
                ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        QueueExample.printQ(priorityQueue);
        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ =
                new PriorityQueue<String>(strings);
        QueueExample.printQ(stringPQ);
        stringPQ = new PriorityQueue<String>(
                strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        QueueExample.printQ(stringPQ);
        Set<Character> charSet = new HashSet<Character>();
        for(char c : fact.toCharArray())
            charSet.add(c); // Autoboxing
        PriorityQueue<Character> characterPQ =
                new PriorityQueue<Character>(charSet);
        QueueExample.printQ(characterPQ);
    }
}
