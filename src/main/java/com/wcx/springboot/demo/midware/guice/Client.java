package com.wcx.springboot.demo.midware.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wcx.springboot.demo.midware.guice.interceptor.AopModule;
import com.wcx.springboot.demo.midware.guice.service.BillingService;

public class Client {
    public static void main(String[] args) {
        /*
         * Guice.createInjector() takes your Modules, and returns a new Injector
         * instance. Most applications will call this method exactly once, in their
         * main() method.
         */
        Injector injector = Guice.createInjector(new BillingModule(),new AopModule());

        /*
         * Now that we've got the injector, we can build objects.
         */
        BillingService billingService = injector.getInstance(BillingService.class);
        billingService.print();
    }
}
