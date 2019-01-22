package com.wcx.springboot.demo.java8.InAction.chap5;

import com.wcx.springboot.demo.java8.InAction.ch4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.wcx.springboot.demo.java8.InAction.ch4.Dish.menu;

public class Reducing{

    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        //计算所有和，0，初始值
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        //获取最大值
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        Optional<Integer> count = numbers.stream().reduce(Integer::min);

        int calories = menu.stream()
                           .map(Dish::getCalories)
                           .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }
}
