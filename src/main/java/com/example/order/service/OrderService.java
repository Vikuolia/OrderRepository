package com.example.order.service;

import com.example.order.model.Order;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order addOrder(Order newOrder);

    List<Order> getAll();

    List<Order> getClientOrders(UUID client);

    Order getById(String id) throws NotFoundException;

    void deleteById(String id);

    void deleteClientOrders(UUID client);

}