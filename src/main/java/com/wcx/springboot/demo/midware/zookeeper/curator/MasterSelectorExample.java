package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

/**
 * master选举:多台机器同时向一个节点下创建一个子节点，只有一台机器能创建成功，该机器作为master
 */
public class MasterSelectorExample {
    //master选举的跟节点，本次master选举在该节点下进行
    private static final String MASTER_PATH = "/testSoftReference/curator/master_path";

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = ClientFactory.getClient();
        LeaderSelector selector = new LeaderSelector(client, MASTER_PATH, new LeaderSelectorListener() {
            //竞争到master后自动调用该方法，执行完之后立即释放master权利，执行新一轮master选举
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成为master");
                Thread.sleep(3000);
                System.out.println("master 操作完成");
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {

            }
        });
        //调用不断的进行重新选举
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
