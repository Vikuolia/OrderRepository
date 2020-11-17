package com.example.order.service;

import com.example.order.model.Order;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<Order> getClientOrders(UUID client) {
        return orderRepository.findAllByClient(client);
    }

    @Override
    public Order getById(String id) throws NotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) return orderRepository.findById(id).get();
        else throw new NotFoundException(String.format("Order with id: %s does not exist", id));
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteClientOrders(UUID client) {
        orderRepository.deleteAllByClient(client);
    }


}

