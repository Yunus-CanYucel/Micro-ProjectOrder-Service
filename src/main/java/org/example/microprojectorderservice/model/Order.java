package org.example.microprojectorderservice.model;




import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private String username;
    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
