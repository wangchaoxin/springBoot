package com.wcx.springboot.demo.java.generic;

import java.util.ArrayList;

class Fruit {
}

class Apple1 extends Fruit {
}

class Jonathan extends Apple1 {
}

class Orange extends Fruit {
}

public class Example {
    public static void main(String[] args) {
        ArrayList<? extends Fruit> flist = new ArrayList<Apple1>();
//        flist.add(new Apple1());
//        flist.add(new Fruit());
//        flist.add(new Object());
        Fruit fruit = (Apple1)flist.get(0);
    }
}
