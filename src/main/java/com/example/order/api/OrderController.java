package com.example.order.api;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAll();
    }

    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable String orderId) throws NotFoundException {
        return orderService.getById(orderId);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{client}")
    public ResponseEntity<Void> deleteClientOrders(@PathVariable String client) {
        orderService.deleteClientOrders(client);
        return ResponseEntity.noContent().build();
    }
}

