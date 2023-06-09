package com.vhbeltramini.dronezeta.repository;

import com.vhbeltramini.dronezeta.model.Order;
import com.vhbeltramini.dronezeta.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer>{

    Optional<Order> findByUserIdAndStatus(Integer id, OrderStatus orderStatus);
    Optional<List<Order>> findByUserId(Integer id);

}
