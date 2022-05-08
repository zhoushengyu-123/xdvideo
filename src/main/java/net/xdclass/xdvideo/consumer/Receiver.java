package net.xdclass.xdvideo.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
@RabbitListener( bindings = @QueueBinding( value = @Queue(value = "${mq.config.queue.error}",autoDelete = "true") ,exchange = @Exchange(value = "${mq.config.exchange}" ,type = ExchangeTypes.DIRECT) ,key = "${mq.config.queue.error.routing.key}" ) )
public class Receiver {

    /*** 接收消息，然后处理消息 */
    @RabbitListener(queues = {"${mq.config.queue.info}"})
    public void process(String msg) {
        // 处理消息
        System.out.println("recevier: " + msg);//sssss
    }
}
