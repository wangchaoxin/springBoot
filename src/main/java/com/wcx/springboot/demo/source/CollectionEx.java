package com.wcx.springboot.demo.source;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
}
