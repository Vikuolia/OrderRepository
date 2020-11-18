package com.example.order.service;

import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByClientId(String clientId);
    void deleteAllByClientId(String clientId);

}
