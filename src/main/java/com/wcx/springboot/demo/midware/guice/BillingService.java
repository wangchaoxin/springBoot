package com.wcx.springboot.demo.midware.guice;

import com.google.inject.Inject;

class BillingService {

    private TestService testService;

    /**
     * To make it explicit that the BillingService constructor is invoked by Guice, we add the @Inject annotation
     * @param testService
     */
    @Inject
    BillingService(TestService testService) {
        this.testService = testService;
    }


}
