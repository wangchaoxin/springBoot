package com.wcx.springboot.demo.java.other;

public class ShutDownHook {
    public static void main(String[] args) {
        /**
         * 使用Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在以下几种场景被调用：
         * 1. 程序正常退出
         * 2. 使用System.exit()
         * 3. 终端使用Ctrl+C触发的中断
         * 4. 系统关闭
         * 5. 使用Kill pid命令干掉进程
         */
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("shut down hook");
            }
        }));
    }
}
