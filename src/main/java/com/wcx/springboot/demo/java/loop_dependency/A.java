package com.wcx.springboot.demo.java.loop_dependency;

public class A {

    //在构造函数中互相初始化，会产生循环依赖
//    private B b = new B();

    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public static void main(String[] args) {
        //通过set方法互相设置，可以解决循环依赖
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        System.out.println("success");
    }
}
