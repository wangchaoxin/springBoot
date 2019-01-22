package com.wcx.springboot.demo.java8.InAction.chap5;

import com.wcx.springboot.demo.java8.InAction.ch4.Dish;

import java.util.Arrays;
import java.util.List;

import static com.wcx.springboot.demo.java8.InAction.ch4.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * in SQL you can select a particular column from a table. The Streams API provides similar
 * facilities through the map and flatMap methods.
 *
 */
public class Mapping{

    public static void main(String...args){

        // map可以连续使用，添加多个map
        List<Integer> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .map(String::length)
                                     .collect(toList());
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        // flatMap,合并stream，将数组合并成一行，返回所有不同的word
        words.stream()
                 .flatMap((String line) -> Arrays.stream(line.split("")))
                 .distinct()
                 .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
