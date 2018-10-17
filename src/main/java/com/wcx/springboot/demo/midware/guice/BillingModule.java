package com.wcx.springboot.demo.midware.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.wcx.springboot.demo.midware.guice.service.TestService;
import com.wcx.springboot.demo.midware.guice.service.TestServiceImpl;

/**
 * 依赖绑定,the Module is the basic unit of definition of bindings
 */
public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        /*
         * Similarly, this binding tells Guice that when CreditCardProcessor is used in
         * a dependency, that should be satisfied with a PaypalCreditCardProcessor.
         */
        //bind(TestService.class).to(TestServiceImpl.class);
        //定义范围scope
        bind(TestService.class).to(TestServiceImpl.class).in(Scopes.SINGLETON);
    }
}
