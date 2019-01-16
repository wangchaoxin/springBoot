package com.wcx.springboot.demo.java8.basic.BehaviorParameterization;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

public class Example {
    interface IA {
        void request();
    }

    class A implements IA {
        @Override
        public void request() {

        }
    }

    /**
     * 行为作为参数，可以设置不同的行为
     *
     * @param a
     */
    public void test(IA a) {
        a.request();
    }

    @Test
    public void test1() {
        //1.list files
        File[] files = new File(".").listFiles();
        //2.过滤files
        File[] files1 = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        //3.将方法当作值用，方法引用
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);

        System.out.println("test");
    }


}
