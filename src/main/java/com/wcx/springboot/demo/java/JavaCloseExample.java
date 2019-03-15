package com.wcx.springboot.demo.java;

/**
 * 1.System.exit() actually calls Runtime.getRuntime().exit() method.
 * 2.Non zero argument to exit() denotes abnormal termination of Java program.
 * 3.Shutdown hooks are executed before Java program actually terminates.
 */
public class JavaCloseExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run(){
                while(true){
                    System.out.println("User thread is running");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        t.start();
        Thread.sleep(2000);
        System.out.println("terminating or closing java program");
        System.exit(1); //non zero value to exit says abnormal termination of JVM
    }
}
