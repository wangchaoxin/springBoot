package com.wcx.springboot.demo.java8.InAction.ch4;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        //foreach
        // forEach is a
        //terminal operation that returns void and applies a lambda to each dish in the source
        Dish.menu.forEach(System.out::println);

    }

    /**
     * java7写法
     * @param dishes
     * @return
     */
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    /**
     * java8写法
     * stream和collection区别：
     *      1.stream只可以遍历一次
     * @param dishes
     * @return
     */
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        //处理的结果依次向下传递,filter,map等都是中间操作，collect,count是终结操作，返回具体数据non-Stream value
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)   //过滤属性
                .sorted(comparing(Dish::getCalories)) //排序
                .map(Dish::getName)   //只选name属性
                //.count()            //计算数量
                //.distinct()         //不重复
                .collect(toList());   //存储在list中

    }

    /**
     * 多线程执行
     * @param dishes
     * @return
     */
    public static List<String> getLowCaloricDishesNamesInJava8Parallel(List<Dish> dishes){
        return dishes.parallelStream()   //多线程执行
                .filter(d -> d.getCalories() < 400)   //过滤属性
                .sorted(comparing(Dish::getCalories)) //排序
                .limit(3)             //limit
                .map(Dish::getName)   //只选name属性
                .collect(toList());   //存储在list中
    }

    /**
     * group by
     * @param dishes
     */
    public void groupBy(List<Dish> dishes) {
        Map<Dish.Type, List<Dish>> dishesByType =
                dishes.stream().collect(groupingBy(Dish::getType));
    }
}
