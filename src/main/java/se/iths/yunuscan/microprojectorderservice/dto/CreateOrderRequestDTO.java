package se.iths.yunuscan.microprojectorderservice.dto;

import jakarta.validation.*;
import jakarta.validation.constraints.*;


import java.util.List;



public record CreateOrderRequestDTO(
        @NotEmpty
        List<@Valid CreateOrderItemRequestsDTO> items
){}