package com.techtalks.ordermanagement.services;

import com.techtalks.ordermanagement.domain.Order;

import java.util.Optional;


public interface OrderService {

    Iterable<Order> listAllOrders();
    Optional<Order> getOrderById(Integer id);
    String saveOrder(Order order);
    String updateOrder(Order order);
    String deleteOrderById(Integer id);

}
