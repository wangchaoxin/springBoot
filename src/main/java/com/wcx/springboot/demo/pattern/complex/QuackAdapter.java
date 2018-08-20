package com.wcx.springboot.demo.pattern.complex;

/**
 * 需求：想让鹅也能呱呱叫，适配器模式
 */
public class QuackAdapter implements Quackable {
    private Honk honk;

    public QuackAdapter(Honk honk) {
        this.honk = honk;
    }

    @Override
    public void quack() {
        honk.honk();
    }
}
