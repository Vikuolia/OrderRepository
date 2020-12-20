package com.example.order.service;

import com.example.order.model.Order;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public List<Order> getClientOrders(String clientId) {
        return orderRepository.findAllByClientId(clientId);
    }

    @Override
    public Order getById(String id){
        return orderRepository.getOne(id);
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteClientOrders(String clientId) {
        orderRepository.deleteAllByClientId(clientId);
    }


}

