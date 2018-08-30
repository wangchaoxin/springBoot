package com.wcx.springboot.demo.pattern.facade;

import com.wcx.springboot.demo.java.reflector.A;

/**
 * 外观类:组合多个子系统，提供统一接口
 */
public class Facade {
    private A a;
    private B b;

    public void execute() {
        a.print("");
        b.execute();
    }

    /**
     * 最小原则，不应该在方法中知道B的存在，直接返回 return new B().execute();
     * @return
     */
    public String minRule() {
        B b1 = new B();
        return b1.execute();
    }

}
