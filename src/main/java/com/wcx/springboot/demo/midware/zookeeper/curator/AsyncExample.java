package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步接口
 */
public class AsyncExample {
    public static final String PATH = "/test/curator";
    String s = "{\"name\":\"wang\"}";
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        CuratorFramework client = ClientFactory.getClient();

        //异步接口  返回码  0(ok)  -4(Connection loss)
        //传入executorService，把一个复杂处理放到专门的线程，默认通过EVENT Thread串行执行
        client.create().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println(String.format("event type [%s],result code [%s]", event.getType(), event.getResultCode()));
            }
        }, executorService).forPath(PATH + "/c2");
        Thread.sleep(Integer.MAX_VALUE);
    }

}
