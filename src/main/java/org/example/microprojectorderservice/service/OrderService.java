package org.example.microprojectorderservice.service;


import lombok.RequiredArgsConstructor;
import org.example.microprojectorderservice.dto.CreateOrderItemRequests;
import org.example.microprojectorderservice.dto.CreateOrderRequest;
import org.example.microprojectorderservice.model.Order;
import org.example.microprojectorderservice.model.OrderItem;
import org.example.microprojectorderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(CreateOrderRequest request, String user) {

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUsername(user);

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CreateOrderItemRequests itemRequest : request.getItem()) {

            OrderItem orderItem = new OrderItem();



            /*
            * Client thing is supposed to be here
            *
            * */


            orderItem.setQuantity(itemRequest.getQuantity());

            orderItems.add(orderItem);

            BigDecimal itemTotal = orderItem.getPrice()
                    .multiply(BigDecimal.valueOf(orderItem.getQuantity()));

            totalPrice = totalPrice.add(itemTotal);
        }

        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }
}
