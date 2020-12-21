package com.example.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@EnableAutoConfiguration
@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private String date;
    private double price;
    private Status status;

    private String clientId;

    private String sellerId;

    private String hikeId;


    public Order(String clientId, String sellerId, String hikeId){
        this.orderId = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.sellerId = sellerId;
        this.date = new Date().toString();
        this.hikeId = hikeId;
        this.price = 0;
        this.status = Status.inProgress;
    }

}

