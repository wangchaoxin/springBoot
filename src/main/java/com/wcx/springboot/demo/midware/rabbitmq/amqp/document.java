package com.wcx.springboot.demo.midware.rabbitmq.amqp;

public class document {
    /**
     * EXCHANGE
     * 交换机的功能主要是接收消息并且转发到绑定的队列，交换机不存储消息，在启用ack模式后，交换机找不到队列会返回错误。交换机有四种类型：Direct, topic, Headers and Fanout
     * Direct：direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去.
     *         Direct Exchange是RabbitMQ默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列。
     * Topic：按规则转发消息（最灵活）,支持通配符
     * Headers：设置header attribute参数类型的交换机
     * Fanout：转发消息到所有绑定队列
     * */
    public void doc(){}
}
