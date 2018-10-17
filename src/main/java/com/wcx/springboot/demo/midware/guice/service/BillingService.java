package com.wcx.springboot.demo.midware.guice.service;

import com.google.inject.Inject;
import com.wcx.springboot.demo.midware.guice.interceptor.TestAnno;

public class BillingService {

    /**
     * inject by the field
     */
    //@Inject
    private TestService testService;

    /**
     * To make it explicit that the BillingService constructor is invoked by Guice, we add the @Inject annotation
     *
     * @param testService
     */
    @Inject
    BillingService(TestService testService) {
        this.testService = testService;
    }

    /**
     * Step 4 – Apply our annotation to our CommunicationMode and load our Module
     */
    @TestAnno()
    public void print() {
        testService.print();
    }


}
