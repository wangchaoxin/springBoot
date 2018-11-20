package com.wcx.springboot.demo.java.collection;

import java.util.*;

public class setExample {
    public static void main(String[] args) {
        //2.Set
       /* TreeSet keeps elements sorted into
        a red-black tree data structure, whereas HashSet uses the hashing function.
        LinkedHashSet also uses hashing for lookup speed, but appears to maintain elements in
        insertion order using a linked list*/
        Set<Integer> intset = new HashSet<Integer>();
        intset.add(1);
        Random rand = new Random(47);
        SortedSet<Integer> treeSet = new TreeSet<Integer>();
        for (int i = 0; i < 10000; i++)
            treeSet.add(rand.nextInt(30));
        System.out.println(treeSet);


        Set<String> set1 = new HashSet<String>();
        Collections.addAll(set1,
                "A B C D E F G H I J K L".split(" "));
        set1.add("M");
        print("H: " + set1.contains("H"));
        print("N: " + set1.contains("N"));
        Set<String> set2 = new HashSet<String>();
        Collections.addAll(set2, "H I J K L".split(" "));
        print("set2 in set1: " + set1.containsAll(set2));
        set1.remove("H");
        print("set1: " + set1);
        print("set2 in set1: " + set1.containsAll(set2));
        set1.removeAll(set2);
        print("set2 removed from set1: " + set1);
        Collections.addAll(set1, "X Y Z".split(" "));
        print("‘X Y Z’ added to set1: " + set1);

        //可以传入list作为参数，去重
        TreeSet<String> ts1 = new TreeSet<>(new ArrayList<>());
        //不区分大小写
        Set<String> words =
                new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
    }

    private static void print(String h) {
        System.out.println(h);
    }


}
