package org.example.microprojectorderservice.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequests {

    private Long productId;
    private int quantity;
}