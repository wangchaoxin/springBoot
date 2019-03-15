package com.wcx.springboot.demo.midware.redis.jedis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Crud {
    public static final String HOST = "192.168.64.2";

    public static void main(String[] args) {

        //jedis需要connect一下才能连接成功
        Jedis jedis = new Jedis(HOST);
        jedis.connect();
        if (jedis.isConnected()) {
            System.out.println("jedis connected");
        }
        //1.strings
        jedis.set("events/city/rome", "32,15,223,828");
        String cachedResponse = jedis.get("events/city/rome");

        //2.lists
        jedis.lpush("queue#tasks", "firstTask");
        jedis.lpush("queue#tasks", "secondTask");
        //删除元素
        /*count > 0: Remove elements equal to value moving from head to tail.
        count < 0: Remove elements equal to value moving from tail to head.
        count = 0: Remove all elements equal to value.
        For example, LREM list -2 "hello" will remove the last two occurrences of "hello" in the list stored at list.
        Return value
        Integer reply: the number of removed elements.*/
        jedis.lrem("key", 0, "value");
        String task = jedis.rpop("queue#tasks");
        //3.sets
        jedis.sadd("nicknames", "nickname#1");
        jedis.sadd("nicknames", "nickname#2");
        jedis.sadd("nicknames", "nickname#1");
        //3.1 remove 元素
        /*Return value
        Integer reply: the number of members that were removed from the set, not including non existing members.
        jedis.srem("key", "value");
        */
        Set<String> nicknames = jedis.smembers("nicknames");
        boolean exists = jedis.sismember("nicknames", "nickname#1");


        //4.hashes
        jedis.hset("user#1", "name", "Peter");
        jedis.hset("user#1", "job", "politician");

        String name = jedis.hget("user#1", "name");

        Map<String, String> fields = jedis.hgetAll("user#1");
        String job = fields.get("job");
        //5.Sorted Sets
        Map<String, Double> scores = new HashMap<>();
        scores.put("PlayerOne", 3000.0);
        scores.put("PlayerTwo", 1500.0);
        scores.put("PlayerThree", 8200.0);
        scores.entrySet().forEach(playerScore -> {
            jedis.zadd("setKey", playerScore.getValue(), playerScore.getKey());
        });
        String player = jedis.zrevrange("ranking", 0, 1).iterator().next();
        long rank = jedis.zrevrank("ranking", "PlayerOne");

        //6.Transactions
        String friendsPrefix = "friends#";
        String userOneId = "4352523";
        String userTwoId = "5552321";

        Transaction t = jedis.multi();
        t.sadd(friendsPrefix + userOneId, userTwoId);
        t.sadd(friendsPrefix + userTwoId, userOneId);
        t.exec();
        //You can even make a transaction success dependent on a specific key by “watching” it right before you instantiate your Transaction:
        //If the value of that key changes before the transaction is executed, the transaction will not be completed successfully.
        jedis.watch("friends#deleted#" + userOneId);

        //7.pipline
        //When we have to send multiple commands, we can pack them together in one request and save connection overhead by using pipelines, it is essentially a network optimization. As long as the operations are mutually independent, we can take advantage of this technique:
        String userOneId1 = "4352523";
        String userTwoId1 = "4849888";

        Pipeline p = jedis.pipelined();
        p.sadd("searched#" + userOneId, "paris");
        p.zadd("ranking", 126, userOneId);
        p.zadd("ranking", 325, userTwoId);
        Response<Boolean> pipeExists = p.sismember("searched#" + userOneId, "paris");
        Response<Set<String>> pipeRanking = p.zrange("ranking", 0, -1);
        p.sync();

        boolean b = pipeExists.get();
        Set<String> ranking = pipeRanking.get();

        //9. Connection Pooling
        final JedisPoolConfig poolConfig = buildPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost");
        try (Jedis jedis1 = jedisPool.getResource()) {
            // do operations with jedis resource
        }
        //10.redis cluster
        //We only need to provide the host and port details from one of our master instances,
        // it will auto-discover the rest of the instances in the cluster.
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("localhost", 6379))) {
            // use the jedisCluster resource as if it was a normal Jedis resource
        } catch (IOException e) {
        }


    }

    private static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
