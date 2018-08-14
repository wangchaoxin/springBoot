package com.wcx.springboot.demo.pattern.template;

public abstract class AbstractClass {


    /**
     * 模板方法，一组算法实现，定义为final不允许子类被覆盖
     */
    public final void templateMethod(){
        operation1();
        operation2();
        if(hook()){
            operation3();
        }
    }

    /**
     * 声明为final，子类无法覆盖
     */
    final void operation1(){
        System.out.println("AbstractClass.operation1");
    }

    /**
     * 实现延迟到子类中,当子类必须提供算法中某个方法的实现时
     */
    public abstract void operation2();

    final void operation3(){
        System.out.println("AbstractClass.operation3");
    }

    /**
     * 钩子函数，子类覆盖可以改变模板行为,如果方法的这个部分是可选的，就使用钩子
     * @return
     */
    boolean hook(){
        return true;
    }
}
