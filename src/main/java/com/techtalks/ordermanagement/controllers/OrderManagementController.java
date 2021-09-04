package com.techtalks.ordermanagement.controllers;

import com.techtalks.ordermanagement.domain.Order;
import com.techtalks.ordermanagement.exceptions.OrderNotFoundException;
import com.techtalks.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderManagementController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<Object> getOrderInformation(@RequestParam Integer id)
    {
        Optional<Order> order=orderService.getOrderById(id);
        if(order.isPresent())
            return new ResponseEntity<>(order,HttpStatus.OK);
        else
            throw new OrderNotFoundException("Order Not Found with ID="+id);
    }

    @GetMapping("/allorders")
    public List<Order> getAllOrders()
    {
        List<Order> allOrders= (List<Order>) orderService.listAllOrders();
        return allOrders;
    }

    @PostMapping("/createorder")
    public String createPurchaseOrder(@RequestBody Order newOrder)
    {
        String orderCreateStatus = orderService.saveOrder(newOrder);
        return orderCreateStatus;
    }

    @PostMapping("/updateorder")
    public String updatePurchaseOrder(@RequestBody Order updatedOrder)
    {
        String orderUpdateStatus = orderService.updateOrder(updatedOrder);
        return orderUpdateStatus;
    }

    @DeleteMapping("/deleteorder")
    public ResponseEntity<Object> deleteOrderInformation(@RequestParam Integer id)
    {
        orderService.getOrderById(id)
                .orElseThrow(
                            ()-> new OrderNotFoundException("Delete failed, Order Not Found with ID="+id)
                );

        String orderDeletionStatus=orderService.deleteOrderById(id);
        return new ResponseEntity<>(orderDeletionStatus,HttpStatus.OK);

    }

}
