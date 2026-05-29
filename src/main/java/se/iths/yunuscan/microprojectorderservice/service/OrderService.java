package se.iths.yunuscan.microprojectorderservice.service;


import lombok.RequiredArgsConstructor;
import se.iths.yunuscan.microprojectorderservice.client.ProductClient;
import se.iths.yunuscan.microprojectorderservice.model.Order;
import se.iths.yunuscan.microprojectorderservice.model.OrderItem;
import se.iths.yunuscan.microprojectorderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService{
    private final OrderRepository orderRepository;
    private final ProductClient productClient;


}
