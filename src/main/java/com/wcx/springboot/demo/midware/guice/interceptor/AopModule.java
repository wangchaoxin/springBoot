package com.wcx.springboot.demo.midware.guice.interceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Step 3 – Define a binding for a Matcher:
 * Matcher is a Guice class that we use do specify the components that our AOP annotation will apply to. In this case, we want the annotation to apply to implementations of CommunicationMode:
 */
public class AopModule extends AbstractModule {
    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(),Matchers.annotatedWith(TestAnno.class),new TestInterceptor());
    }
}
