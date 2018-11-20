package com.wcx.springboot.demo.java.collection;

import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer,Integer> m =
                new HashMap<Integer,Integer>();
        for(int i = 0; i < 10000; i++) {
        // Produce a number between 0 and 20:
            int r = rand.nextInt(20);
            Integer freq = m.get(r);
            m.put(r, freq == null ? 1 : freq + 1);
        }
        System.out.println(m);
        //containskey and containsValue
        m.containsKey("key");
        m.containsValue("");
        //keyset and values
        Set<Integer> integers = m.keySet();
        Collection<Integer> values = m.values();
    }
}
