package org.example.microprojectorderservice.dto;

import lombok.*;
import org.example.microprojectorderservice.model.OrderItem;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private List<CreateOrderItemRequests> item;
}
