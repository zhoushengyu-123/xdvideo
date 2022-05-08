package net.xdclass.xdvideo.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Sender {
    @Autowired
    private AmqpTemplate template;
    @Value("${mq.config.exchange}")
    private String exchange;
    @Value("${mq.config.queue.info.routing.key}")
    private String routingKey;
    public void send(String msg){
        // 发送消息
        template.convertAndSend(exchange,routingKey,msg);
        }
}
