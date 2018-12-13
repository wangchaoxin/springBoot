package com.wcx.springboot.demo.xiangxue.jvm.dispatch;

/**
 * 在运行时候才能确认具体使用哪一个类
 * 在实现上，最常用的手段就是为类在方法区中建立一个虚方法表。虚方法表中存放着各个方法的实际入口地址。如果某个方法在子类中没有被重写，
 * 那子类的虚方法表里面的地址入口和父类相同方法的地址入口是一致的，都指向父类的实现入口。如果子类中重写了这个方法，
 * 子类方法表中的地址将会替换为指向子类实现版本的入口地址
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("hello,gentleman！");

        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("hello,lady！");

        }
    }

    public static void main(String[] args) {
        Human h1 = new Man();
        Human h2 = new Woman();
        h1.sayHello();
        h2.sayHello();

    }
}
