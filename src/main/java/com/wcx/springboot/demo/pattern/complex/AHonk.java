package com.wcx.springboot.demo.pattern.complex;

public class AHonk implements Honk {
    @Override
    public void honk() {
        System.out.println("AHonk honk");
    }
}
