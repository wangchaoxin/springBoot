package com.wcx.springboot.demo.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * If you have several test classes, you can combine them into a test suite.
 * Running a test suite executes all test classes in that suite in the specified order.
 * A test suite can also contain other test suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientTest.class, MyTestRunner.class
})
public class TestSuit {

}
