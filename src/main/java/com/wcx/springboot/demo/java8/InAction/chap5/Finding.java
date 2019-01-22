package com.wcx.springboot.demo.java8.InAction.chap5;

import com.wcx.springboot.demo.java8.InAction.ch4.Dish;

import java.util.Optional;

import static com.wcx.springboot.demo.java8.InAction.ch4.Dish.menu;


public class Finding{

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    // Checking to see if a predicate matches at least one element
    //Is there an element in the stream
    //matching the given predicate?
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    //Checking to see if a predicate matches all elements
    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    //The opposite of allMatch is noneMatch. It ensures that no elements in the stream match the
    //given predicate
    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    //The findAny method returns an arbitrary element of the current stream.
    //optional，避免null的情况  get 获取数据，  isPresent()是否有数据
    private static Optional<Dish> findVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }

    //获取第一个数据
    private static Optional<Dish> findFirstVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findFirst();
    }

}
