package com.wcx.springboot.demo.java8.InAction.ch3;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.wcx.springboot.demo.java8.InAction.ch1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        //1.lambda表达式代替匿名类
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };
        //没有return语句，return语句是默认的,不加{}的情况,加{}需要有return语句
        Comparator<Apple> byWeight1 =
                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Function<String, Object> stringObjectFunction = (String s) -> {
            return "";
        };
        //2.函数接口,只包含一个abstract方法的接口,An interface is still a functional interface if it has many default methods as long as it
        //specifies only one abstract method
        //函数签名相同的地方就可以用lamda表达式,如runnable ()->void,就可以用() -> { }替换
        Runnable r = () -> {
        };

        //3.现成的函数接口
        //3.1 Predicate
        //Predicate<T> interface defines an abstract method named test that
        //accepts an object of generic type T and returns a boolean,You might want to use this interface when you
        //need to represent a boolean expression that uses an object of type T
        //public interface Predicate<T>{
        //    boolean test (T t);
        //}

        //3.2 Consumer
        //The java.util.function.Consumer<T> interface defines an abstract method named accept that
        //takes an object of generic type T and returns no result (void). You might use this interface when
        //you need to access an object of type T and perform some operations on it
        //void accept(T t);

        //3.3 Function
        //The java.util.function.Function<T, R> interface defines an abstract method named apply that
        //takes an object of generic type T as input and returns an object of generic type R. You might use
        //this interface when you need to define a lambda that maps information from an input object to
        //an output (for example, extracting the weight of an apple or mapping a string to its length)
        //R apply(T t);

        //3.4 Using local variables
        //lambda可以使用local variable，但是必须是final的，不能被赋值两次

        //3.6. Method references 方法引用
        //Method references let you reuse existing method definitions and pass them just like lambdas
        //Indeed, a method reference lets you create a lambda expression from an existing method
        //implementation
        //Lambda Method                        reference equivalent
        //(Apple a) -> a.getWeight()           Apple::getWeight
        //(String s) -> System.out.println(s)  System.out::println
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        //逆序排列
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        //3.8.1 Chaining Comparators，连续比较
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor)
        );
        //3.8.2. Composing Predicates
        Predicate<Apple> predicate = apple -> apple.getColor().equals("red");
        Function<String, String> addHeader = (s) -> s;

        //3.10 conclution
        //Lambda expressions can be used only where a functional interface is expected.

    }
}
