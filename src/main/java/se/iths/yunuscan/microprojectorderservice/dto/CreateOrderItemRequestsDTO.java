package se.iths.yunuscan.microprojectorderservice.dto;


import lombok.*;

@Getter
@Setter
public class CreateOrderItemRequestsDTO{

    private Long productId;
    private int quantity;
}