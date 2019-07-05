package com.wcx.springboot.demo.source;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CollectionEx {
    /**
     * 求集合的交集，并集，差集
     */
    @Test
    public void test() {
        Set set1 = new HashSet();
        Set set2 = new HashSet();
        Sets.difference(set1, set2);
        Sets.union(set1, set2);
        Sets.intersection(set1, set2);
    }

    /**
     * 平均分割list,chunkSize 分割的每个数组大小
     */
    @Test
    public void test1() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final int chunkSize = 20;
        final Collection<List<Integer>> result = splitList(numbers, chunkSize);
        System.out.println(result);
    }

    public static Collection<List<Integer>> splitList(List<Integer> numbers, int chunkSize) {
        final AtomicInteger counter = new AtomicInteger();
        return numbers.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values();
    }


}
