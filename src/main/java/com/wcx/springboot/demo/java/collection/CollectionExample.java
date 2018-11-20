package com.wcx.springboot.demo.java.collection;

import java.util.*;

import static jdk.nashorn.internal.objects.Global.print;

public class CollectionExample {
    public static void main(String[] args) {
        //1.collection
        List<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = {6, 7, 8, 9, 10};
        collection.addAll(Arrays.asList(moreInts));
        // Runs significantly faster, but you can’t
        // construct a Collection this way:
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);
        // Produces a list "backed by" an array:
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99); // OK -- modify an element
        // list.add(21); // Runtime error because the
        // underlying array cannot be resized.

        //2.arraylist
        List<Integer> sub = collection.subList(1, 2);
        Collections.sort(sub); // In-place sort
        Collections.shuffle(sub, new Random(20)); // Mix it up
        collection.retainAll(sub);
        collection.removeAll(sub);
        Object[] objects = collection.toArray();
        //iterator
        Iterator<Integer> iterator = sub.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            iterator.remove();
        }



        //3.LinkedList
        //LinkedList also adds methods that allow it to be used as a stack, a Queue or a doubleended queue (deque).
        LinkedList<Animal> pets =
                new LinkedList<Animal>(Arrays.asList(new Animal(), new Animal()));
        print(pets);
        // Identical:
        print("pets.getFirst(): " + pets.getFirst());
        print("pets.element(): " + pets.element());
        // Only differs in empty-list behavior:
        print("pets.peek(): " + pets.peek());
        // Identical; remove and return the first element:
        print("pets.remove(): " + pets.remove());
        print("pets.removeFirst(): " + pets.removeFirst());
        // Only differs in empty-list behavior:
        print("pets.poll(): " + pets.poll());
        print(pets);
        pets.addFirst(new Animal());
        print("After addFirst(): " + pets);
        pets.offer(new Animal());
        print("After offer(): " + pets);
        pets.add(new Animal());
        print("After add(): " + pets);
        pets.addLast(new Animal());
        print("After addLast(): " + pets);
        print("pets.removeLast(): " + pets.removeLast());

        //4.Stack
        Stack<String> stack = new Stack<String>();
        for(String s : "My dog has fleas".split(" "))
            stack.push(s);
        while(!stack.empty())
            System.out.print(stack.pop() + " ");

    }

    static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }

    public static class Animal {
    }
}
