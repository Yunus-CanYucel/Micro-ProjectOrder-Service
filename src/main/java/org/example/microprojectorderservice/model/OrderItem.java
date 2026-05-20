package org.example.microprojectorderservice.model;

import jakarta.persistence.*;

import lombok.*;


import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private int quantity;
}