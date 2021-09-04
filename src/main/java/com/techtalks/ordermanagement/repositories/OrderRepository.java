package com.techtalks.ordermanagement.repositories;

import com.techtalks.ordermanagement.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {


}
