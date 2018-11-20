package com.wcx.springboot.demo.java.innerclass;

/**
 * Nested classes
 * 1. You don’t need an outer-class object in order to create an object of a nested class.
 * 2. You can’t access a non-static outer-class object from an object of a nested class.
 */
public class Parcel11 {
    private static class ParcelContents implements Contents {
        private int i = 11;
        public int value() { return i; }
    }
    protected static class ParcelDestination
            implements Destination {
        private String label;
        private ParcelDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() { return label; }
        // Nested classes can contain other static elements:
        public static void f() {}
        static int x = 10;
        static class AnotherLevel {
            public static void f() {}
            static int x = 10;
        }
    }
    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }
    public static Contents contents() {
        return new ParcelContents();
    }
    public static void main(String[] args) {
        Contents c = contents();
        Destination d = destination("Tasmania");

        //1.嵌套类new
        Parcel11.ParcelContents p = new Parcel11.ParcelContents();
    }
}
