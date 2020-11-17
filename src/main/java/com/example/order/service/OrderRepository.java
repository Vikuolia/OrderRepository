package com.example.order.service;

import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByClient(UUID client);
    void deleteAllByClient(UUID client);
}
