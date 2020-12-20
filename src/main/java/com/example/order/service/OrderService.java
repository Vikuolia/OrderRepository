package com.example.order.service;

import com.example.order.model.Order;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order addOrder(Order newOrder);

    List<Order> getAll();

    List<Order> getClientOrders(String clientId);

    Order getById(String id);

    void deleteById(String id);

    void deleteClientOrders(String clientId);

}