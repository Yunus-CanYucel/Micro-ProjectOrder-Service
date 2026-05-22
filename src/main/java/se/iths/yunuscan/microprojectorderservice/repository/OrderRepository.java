package se.iths.yunuscan.microprojectorderservice.repository;


import se.iths.yunuscan.microprojectorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
