package com.example.orderservice.OrdersRepo;

import com.example.orderservice.Orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders,Long> {
}
