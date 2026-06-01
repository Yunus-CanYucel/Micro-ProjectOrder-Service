package se.iths.yunuscan.microprojectorderservice.repository;


import se.iths.yunuscan.microprojectorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
}
