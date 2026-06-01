package se.iths.yunuscan.microprojectorderservice.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record CreateOrderItemRequestsDTO(

        @NotNull
        Long productId,
        @Min(1)
        int quantity
){}