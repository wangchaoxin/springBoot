package com.wcx.springboot.demo.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 执行某几个测试类
 * Run your test from the command line
 */
public class MyTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ClientTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
