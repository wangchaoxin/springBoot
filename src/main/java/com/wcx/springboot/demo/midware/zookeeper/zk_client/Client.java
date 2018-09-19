package com.wcx.springboot.demo.midware.zookeeper.zk_client;

import org.I0Itec.zkclient.ZkClient;

public class Client {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("",5000);
    }
}
