package com.wcx.springboot.demo.pattern.complex;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式：需求，统一管理quack类
 */
public class QuackComposite implements Quackable {

    private List<Quackable> list = new ArrayList<>();

    public void add(Quackable quackable) {
        list.add(quackable);
    }

    @Override
    public void quack() {
        for (Quackable quackable : list) {
            quackable.quack();
        }
    }
}
