package se.iths.yunuscan.microprojectorderservice.dto;


import lombok.*;

@Getter
@Setter
public class CreateOrderItemRequests {

    private Long productId;
    private int quantity;
}