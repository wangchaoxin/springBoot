package com.wcx.springboot.demo.java.innerclass;

public class Parcel1 {
    private int value;

    class Contents implements com.wcx.springboot.demo.java.innerclass.Contents {
        private int i = 11;

        /**
         * 4.可以用内部 类实现接口，父类函数返回接口
         * @return
         */
        @Override
        public int value() {
            return i;
        }
        /**
         * 3 .this 创建外部类对象的引用
         * @return
         */
        public Parcel1 outer() {
            return Parcel1.this;
        }

    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
            //1.The link to the outer class
            //可以访问外部类的所有变量和方法
           /*  The inner class secretly captures a reference to the particular object of the enclosing
            class that was responsible for creating it. Then, when you refer to a member of the enclosing
            class, that reference is used to select that member*/
            value = 1;
        }

        String readLabel() {
            return label;
        }
    }

    /**
     * 5.匿名内部类 Anonymous inner classes
     * Create an object of an anonymous class that’s inherited
     * from Contents." The reference returned by the new expression is automatically upcast to a
     * Contents reference
     * In the anonymous inner class, Contents is created by using a default constructor
     * @return
     */
    public Price getPrice() {
        return new Price() {
            @Override
            public int getPrice() {
                return 0;
            }
        };
    }


    public Destination to(String s) {
        return new Destination(s);
    }
    public com.wcx.springboot.demo.java.innerclass.Contents contents() {
        return new Contents();
    }

    // Using inner classes looks just like
    // using any other class, within Parcel1:
    public void ship(String dest) {
        com.wcx.springboot.demo.java.innerclass.Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");


       /* If you want to make an object of the inner class anywhere except from within a non-static
        method of the outer class, you must specify the type of that object as
        OuterClassName.InnerClassName, as seen in main( )*/
        Destination to = p.to("1");
        com.wcx.springboot.demo.java.innerclass.Contents contents = p.contents();

        // Inner classes in methods and scopes
    }
}