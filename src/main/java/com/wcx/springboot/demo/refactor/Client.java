package com.wcx.springboot.demo.refactor;

public class Client {
    private  static int x;
    public static void main(String[] args) {
        String result = "";
        int a = 0;
        int b = getB();
        int c = b + 1;
        c = 2;
        if (a > b && b > c) {
            System.out.println("");
        }
        switch (a()) {
            case 1:
                result = "1";
                break;
            case 2:
                result = "2";
        }
    }

    /**
     * 重构中的中间变量，用replace temp with query 代替
     *
     * @return
     */
    private static int a() {
        return 1;
    }

    private static int getB() {
        return 1;
    }

    private static void print(String args) {
        args += "1";

    }
}
