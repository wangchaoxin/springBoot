package com.wcx.springboot.demo.java.exception;

import org.junit.Test;

import java.util.logging.Logger;



/**
 * 抛出异常时，退出方法的执行，跳到上一层的处理程序
 */
public class Example {
    private static Logger logger =
            Logger.getLogger("LoggingException");
    public static void main(String[] args) throws AException, BException {
        Integer t = 1;
        print("111");
        if (t == null) {
            //exception constructor
            throw new NullPointerException("message");
        }
//        throw new AException();
        try {
            throw new BException("BException");
        } catch (BException e) {
            //1.basic Exception methods:
            print("Caught Exception");
            print("getMessage():" + e.getMessage());
            print("getLocalizedMessage():" +
                    e.getLocalizedMessage());
            print("toString():" + e);
            print("printStackTrace():");
            e.printStackTrace(System.out);
        }
    }

    /**
     * 2.finally最终都会被执行，可以统一在finally中关闭连接等操作，不需要在每个catch中关闭
     * finally clause is executed whether or not an exception
     * is thrown.
     *
     */
    @Test
    public void test2() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }finally {
            System.out.println("execute finally");
        }
    }

    /**
     * 3.using finally during return,无论在catch或者try中return，都会执行finally
     */
    @Test
    public void test3(){
        try {
//            int i = 1;
//            return;
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            System.out.println("finally execute");
        }
    }


    private static void print(String s) {
        System.out.println(s);
    }

    /**
     * 4.直接继承，没有message构造函数
     */
    static class AException extends Exception {

    }
    static class BException extends Exception {
        public BException(String message) {
            super(message);
        }

        public BException() {
            super();
        }
    }
    //5.Exception matching
    /*When an exception is thrown, the exception-handling system looks through the "nearest"
    handlers in the order they are written. When it finds a match, the exception is considered
    handled, and no further searching occurs.
    Matching an exception doesn’t require a perfect match between the exception and its
    handler. A derived-class object will match a handler for the base class*/
    class Annoyance extends Exception {}
    class Sneeze extends Annoyance {}
    @Test
    public void test4() {
        try {
            throw new Sneeze();
        } catch (Sneeze sneeze) {
            System.out.println("catch Sneeze");
            sneeze.printStackTrace();
        } catch (Annoyance annoyance) {
            System.out.println("catch annoyance");
        }
    }
}
