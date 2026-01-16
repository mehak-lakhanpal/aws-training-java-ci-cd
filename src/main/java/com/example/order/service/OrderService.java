package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.respository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        log.info("Returning orders from DB");
        List<Order> orders =  orderRepository.findAll();
        log.info("Orders in DB:{}",orders);
        return orders;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}