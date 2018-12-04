package com.wcx.springboot.demo.midware.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * If you have several testSoftReference classes, you can combine them into a testSoftReference suite.
 * Running a testSoftReference suite executes all testSoftReference classes in that suite in the specified order.
 * A testSoftReference suite can also contain other testSoftReference suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientTest.class, MyTestRunner.class
})
public class TestSuit {

}
