package com.wcx.springboot.demo.midware.guice;

import com.google.inject.AbstractModule;

/**
 * 依赖绑定
 */
public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        /*
         * Similarly, this binding tells Guice that when CreditCardProcessor is used in
         * a dependency, that should be satisfied with a PaypalCreditCardProcessor.
         */
        bind(TestService.class).to(TestServiceImpl.class);
    }
}
