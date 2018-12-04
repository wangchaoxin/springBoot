package com.wcx.springboot.demo.jvm.dispatch;


/**
 * 调用目标在程序代码写好、编译器进行编译时就必须确定下来。这类方法的调用称为解析。
 * 在Java语言中符合“编译期可知，运行期不可变”这个要求的方法，主要包括静态方法和私有
 * 方法两大类，前者与类型直接关联，后者在外部不可被访问，这两种方法各自的特点决定了
 * 它们都不可能通过继承或别的方式重写其他版本，因此它们都适合在类加载阶段进行解析。
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy！");//1
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman！");//2
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady！");//3
    }

    public static void main(String[] args) {

        Human h1 = new Man();
        Human h2 = new Woman();

        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(h1);
        sr.sayHello(h2);


    }
}
