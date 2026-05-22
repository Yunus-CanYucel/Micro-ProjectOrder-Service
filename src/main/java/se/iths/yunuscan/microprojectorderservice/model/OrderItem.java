package se.iths.yunuscan.microprojectorderservice.model;

import jakarta.persistence.*;

import lombok.*;


import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private Long id;
    private BigDecimal price;
    private int stock;
}