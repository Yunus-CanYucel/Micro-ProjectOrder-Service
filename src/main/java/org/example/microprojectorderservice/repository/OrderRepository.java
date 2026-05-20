package org.example.microprojectorderservice.repository;


import org.example.microprojectorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface OrderRepository extends JpaRepository<Order, BigDecimal> {
}
