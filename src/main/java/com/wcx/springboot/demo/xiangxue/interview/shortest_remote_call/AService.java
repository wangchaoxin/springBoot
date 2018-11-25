package com.wcx.springboot.demo.xiangxue.interview.shortest_remote_call;

public class AService {
    public String call1() throws InterruptedException {
        Thread.sleep(2000);
        return "a";
    }
    public String call2() throws InterruptedException {
        Thread.sleep(2000);
        return "b";
    }
}
