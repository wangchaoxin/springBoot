package com.wcx.springboot.demo.xiangxue.interview.shortest_remote_call;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 需要调用service的多个结果，串行调用速度慢
 * 转换成并行调用,时间缩短一倍
 */
public class CallExample {
    static AService aService = new AService();
    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testSerializeCall();
        testParallelCall();


    }

    private static void testParallelCall() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Callable call1 = new Callable() {
            @Override
            public Object call() throws Exception {
                return aService.call1();
            }
        };
        Callable call2 = new Callable() {
            @Override
            public Object call() throws Exception {
                return aService.call1();
            }
        };
        Future future1 = executorService.submit(call1);
        Future future2 = executorService.submit(call2);
        Map<String, Object> result = new HashMap<>();
        result.put("a", future1.get());
        result.put("b", future2.get());
        System.out.println(result);
        long end = System.currentTimeMillis();
        System.out.println("parallel call consume time :" + (end - start) / 1000 * 1000);

    }

    private static void testSerializeCall() throws InterruptedException {
        long start = System.currentTimeMillis();

        String a = aService.call1();
        String b = aService.call2();

        long end = System.currentTimeMillis();

        System.out.println("serialize call consume time :" + (end - start) / 1000 * 1000);
    }
}
