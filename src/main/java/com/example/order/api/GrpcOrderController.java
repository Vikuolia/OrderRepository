package com.example.order.api;

import com.example.order.*;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class GrpcOrderController extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderService orderService;

    @Override
    public void add(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        String id = request.getId();
        String clientId = request.getClient();
        String sellerId = request.getSeller();
        String hikeId = request.getHike();

        Order orderAdd = new Order(id, clientId, sellerId, hikeId);
        Order orderResponse = orderService.addOrder(orderAdd);

        OrderResponse response = OrderResponse.newBuilder()
                                              .setId(orderResponse.getOrderId())
                                              .setClient(orderResponse.getClientId())
                                              .setSeller(orderResponse.getSellerId())
                                              .setHike(orderResponse.getHikeId())
                                              .setDate(orderResponse.getDate())
                                              .setPrice(orderResponse.getPrice())
                                              .setStatus(Status.valueOf(orderResponse.getStatus().toString()))
                                              .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void all(AllOrdersRequest request, StreamObserver<AllOrdersResponse> responseObserver) {
        List<Order> orders = orderService.getAll();
        List<OrderResponse> responses = new ArrayList<>();

        for(Order order: orders){
            OrderResponse oneResponse = OrderResponse.newBuilder()
                                                     .setId(order.getOrderId())
                                                     .setClient(order.getClientId())
                                                     .setSeller(order.getSellerId())
                                                     .setHike(order.getHikeId())
                                                     .setDate(order.getDate())
                                                     .setPrice(order.getPrice())
                                                     .setStatus(Status.valueOf(order.getStatus().toString()))
                                                     .build();
            responses.add(oneResponse);
        }
        AllOrdersResponse response = AllOrdersResponse.newBuilder().addAllOrders(responses).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(OrderByIdRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order order = orderService.getById(request.getId());
        OrderResponse response = OrderResponse.newBuilder()
                                              .setId(order.getOrderId())
                                              .setClient(order.getClientId())
                                              .setSeller(order.getSellerId())
                                              .setHike(order.getHikeId())
                                              .setDate(order.getDate())
                                              .setPrice(order.getPrice())
                                              .setStatus(Status.valueOf(order.getStatus().toString()))
                                              .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(OrderByIdRequest request, StreamObserver<DeleteOrderResponse> responseObserver) {
        orderService.deleteById(request.getId());
        DeleteOrderResponse response = DeleteOrderResponse.newBuilder()
                .setResponse("Order with id " + request.getId() + " was deleted").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
