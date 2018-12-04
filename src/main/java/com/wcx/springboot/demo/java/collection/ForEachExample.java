package com.wcx.springboot.demo.java.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 继承Iterable接口可以用于foreach中
 */
public class ForEachExample {
    public static class IterableClass implements Iterable<String> {
        protected String[] words = ("And that is how " +
                "we know the Earth to be banana-shaped.").split(" ");

        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int index = 0;

                public boolean hasNext() {
                    return index < words.length;
                }

                public String next() {
                    return words[index++];
                }

                public void remove() { // Not implemented
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    public static void main(String[] args) {
        for(String s : new IterableClass())
            System.out.print(s + " ");
        for(Map.Entry entry: System.getenv().entrySet()) {
            System.out.println(entry.getKey() + ": " +
                    entry.getValue());
        }
    }

    /**
     * array和foreach
     * A foreach statement works with an array or anything Iterable, but that doesn’t mean that an
     * array is automatically an Iterable, nor is there any autoboxing that takes place:
     */
    public static class ArrayIsNotIterable {
        static <T> void test(Iterable<T> ib) {
            for(T t : ib)
                System.out.print(t + " ");
        }
        public static void main(String[] args) {
            test(Arrays.asList(1, 2, 3));
            String[] strings = { "A", "B", "C" };
            // An array works in foreach, but it’s not Iterable:
            //! testSoftReference(strings);
            // You must explicitly convert it to an Iterable:
            //Trying to pass an array as an Iterable argument fails. There is no automatic conversion to
            //an Iterable; you must do it by hand
            test(Arrays.asList(strings));
        }
    }

}
