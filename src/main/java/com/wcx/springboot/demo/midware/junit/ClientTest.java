package com.wcx.springboot.demo.midware.junit;

import org.junit.*;

public class ClientTest {


    /**
     * 每个方法执行的时候都执行
     * Executed before each test. It is used to prepare the test environment (e.g., read input data, initialize the class).
     */
    @Before
    public void before() {
        System.out.println("before");
    }

    /**
     * Executed after each test. It is used to cleanup the test environment (e.g., delete temporary data, restore defaults).
     * It can also save memory by cleaning up expensive memory structures.
     */
    @After
    public void after() {
        System.out.println("after");
    }

    /**
     * 只执行一次
     * Executed once, before the start of all tests. It is used to perform time intensive activities, for example, to connect to a database.
     * Methods marked with this annotation need to be defined as static to work with JUnit.
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("before class");
    }

    /**
     * Executed once, after all tests have been finished. It is used to perform clean-up activities, for example, to disconnect from a database.
     * Methods annotated with this annotation need to be defined as static to work with JUnit.
     */
    @AfterClass
    public static void afterClass() {
        System.out.println("after class");
    }

    /**
     * Marks that the test should be disabled. This is useful when the underlying code has been changed and the test case has not yet been adapted.
     * Or if the execution time of this test is too long to be included. It is best practice to provide the optional description, why the test is disabled.
     */
    @Test
    @Ignore("not used")
    public void ignoreTest() {
        System.out.println("ignore");
    }

    /**
     * Fails if the method does not throw the named exception.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void exception() throws Exception {
        System.out.println("exception");
        throw new Exception("");
    }

    /**
     * Fails if the method takes longer than 100 milliseconds.
     *
     * @throws InterruptedException
     */
    @Test(timeout = 100)
    public void time() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("time out");
    }

    @Test
    public void test() {

//        Assume.assumeFalse(System.getProperty("os.name").contains("Linux"));
        //Disabling tests
        //Alternatively you can use Assume.assumeFalse or Assume.assumeTrue to define a condition for the test. Assume.assumeFalse marks the test as invalid, if its condition evaluates to true.
        Assume.assumeFalse(System.getProperty("os.name").toLowerCase().contains("windows"));
        System.out.println("test");
        //各种assert
        Assert.assertEquals(1, 2);
        Assert.assertNotNull(1);
        //Checks that both variables refer to the same object.
        Assert.assertSame("1","1");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    //命名规范
    //One possible convention is to use the "should" in the test method name.
    // For example, "ordersShouldBeCreated" or "menuShouldGetActive".
    // This gives a hint what should happen if the test method is executed.
    public void orderShouldNotBeNull() {

    }


}
