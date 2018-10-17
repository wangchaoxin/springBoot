package com.wcx.springboot.demo.midware.guice.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Step 1 – Implement the AOPAlliance’s MethodInterceptor:
 */
public class TestInterceptor  implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("TestInterceptor");
        Object[] arguments = invocation.getArguments();
        return invocation.proceed();
    }
}
