package se.iths.yunuscan.microprojectorderservice.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter

public class CreateOrderRequestDTO {
    private List<CreateOrderItemRequestsDTO> item;

}
