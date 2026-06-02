package se.iths.yunuscan.microprojectorderservice.model;

import jakarta.persistence.*;

import lombok.*;


import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name= "order_id")
    private Order order;

}