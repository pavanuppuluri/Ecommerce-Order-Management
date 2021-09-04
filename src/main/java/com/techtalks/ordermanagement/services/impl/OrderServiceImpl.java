package com.techtalks.ordermanagement.services.impl;

import com.techtalks.ordermanagement.domain.Order;
import com.techtalks.ordermanagement.domain.OrderLine;
import com.techtalks.ordermanagement.repositories.OrderRepository;
import com.techtalks.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Iterable<Order> listAllOrders() {
        return orderRepository.findAll();

    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);



    }

    @Override
    public String saveOrder(Order newOrder) {
        /* There is one more way to build Order object if
        we have complex mapping using Builder design pattern
        as below

        Order order=Order.builder()
                .orderDate(newOrder.getOrderDate())
                .orderNumber(newOrder.getOrderNumber)
                .destinationAddress(newOrder.getDestinationAddress())
                .build();
         */
         newOrder.setOrderDate(new Date());
         newOrder.setCreationDate(new Date());
         newOrder.setLastUpdateDate(new Date());
         newOrder.getOrderLines().stream()
                 .map(ol->
                        {
                         ol.setCreationDate(new Date());
                         ol.setLastUpdateDate(new Date());
                         return ol;
                     }).collect(Collectors.toList());

         orderRepository.save(newOrder);
         return "ORDER_CREATE_SUCCESS";
    }

    @Override
    public String updateOrder(Order order) {
        // Update scenarios can be as follows
        // Adding a new line
        // Updating the quantity of a line item
        // Deleting an existing line - As we kept orphanRemoval = true in Order.java this case will be taken care

        Order existingOrder=getOrderById(order.getOrderId()).get();
        List<OrderLine> existingOrderLines = existingOrder.getOrderLines();

        // First get all the new lines added
        // If orderLineId is null, then it is new line item

        List<OrderLine> newLines;
        newLines = order.getOrderLines().stream()
                        .filter(ol-> ol.getOrderLineId()==null)
                        .map(ol->{
                            ol.setCreationDate(new Date());
                            ol.setLastUpdateDate(new Date());
                            return ol;
                        })
                        .collect(Collectors.toList());

        // Next scenario is - Quantity updated for an existing line.
        // To check if quantity updated or not we need to first get
        // the existing quantity of the line. For this let us create
        // one HashMap with key as orderLineId and value as OrderLine record

        Map<Integer,OrderLine> linesHashMap= existingOrderLines.stream()
                                            .collect(
                                                    Collectors.toMap(
                                                            OrderLine::getOrderLineId,
                                                            Function.identity()
                                                    ));

        List<OrderLine> updatedLines;
        updatedLines = order.getOrderLines().stream()
                .filter(ol-> ol.getOrderLineId()!=null)
                .map(ol->{
                    // Now check for a given line if quantity is modified
                    OrderLine temp=linesHashMap.get(ol.getOrderLineId());
                    // If quantity mismatch then it means line updated
                    if(temp.getQuantity()!=ol.getQuantity())
                    {
                        ol.setCreationDate(temp.getCreationDate());
                        ol.setLastUpdateDate(new Date());
                    }
                    else   // Means line not updated
                    {
                        ol.setCreationDate(temp.getCreationDate());
                        ol.setLastUpdateDate(temp.getLastUpdateDate());
                    }

                    return ol;
                })
                .collect(Collectors.toList());


        order.setOrderLines(newLines);
        order.getOrderLines().addAll(updatedLines);
        order.setLastUpdateDate(new Date());
        orderRepository.save(order);
        return "ORDER_UPDATE_SUCCESS";

    }

    @Override
    public String deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
        return "ORDER_DELETED";
    }


}
