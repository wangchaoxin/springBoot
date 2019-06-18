package com.wcx.springboot.demo.java.check;

import java.util.Objects;

public class Assert {
    public static void main(String[] args) {
        try {
            Objects.requireNonNull(null);
//            org.junit.Assert.assertNotNull(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
