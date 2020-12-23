package com.example.order.rabbitmq;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.order.rabbitmq.Config.QUEUE;

@Component
public class Consumer {

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = QUEUE)
    public  void consumeMessageFromQueue(Order order){
        System.out.println(orderService.addOrder(order));
    }
}

