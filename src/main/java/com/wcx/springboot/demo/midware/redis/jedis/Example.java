package com.wcx.springboot.demo.midware.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class Example {

    public static final String HOST = "192.168.64.2";

    public static void main(String[] args) {
        //1.单独jedis使用
        Jedis jedisSingle = new Jedis(HOST);
        jedisSingle.set("foo", "bar");
        String value = jedisSingle.get("foo");

        //2.多线程环境下使用redispool,单独的jedis实例不是线程安全的
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);

        /// Jedis implements Closeable. Hence, the jedis instance will be auto-closed after the last statement.
        try (Jedis jedis = pool.getResource()) {
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        }
        /// ... when closing your application:
        pool.close();

        //3.直接在finally中close
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        } finally {
            // You have to close jedis object. If you don't close then
            // it doesn't release back to pool and you can't get a new
            // resource from pool.
            if (jedis != null) {
                jedis.close();
            }
        }
        /// ... when closing your application:
        pool.close();


        //4.Setting up master/slave distribution,指定是某一个redis的slave
        //enable replication
        jedis.slaveof("localhost", 6379);  //  if the master is on the same PC which runs your code
        jedis.slaveof("192.168.1.35", 6379);

        //5.disable replication / upon failing master, promote a slave
        //In case your master goes down, you may want to promote a slave to be the new master.
        // You should first (try to) disable replication of the offline master first, then, in case you have several slaves,
        // enable replication of the remaining slaves to the new master:
        jedis.slaveofNoOne();
        jedis.slaveof("192.168.1.36", 6379);

    }
}
