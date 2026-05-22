package se.iths.yunuscan.microprojectorderservice.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter

public class CreateOrderRequest {
    private List<CreateOrderItemRequests> item;
}
