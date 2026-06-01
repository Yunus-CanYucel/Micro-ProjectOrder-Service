package se.iths.yunuscan.microprojectorderservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import se.iths.yunuscan.microprojectorderservice.dto.CreateOrderRequestDTO;
import se.iths.yunuscan.microprojectorderservice.dto.OrderResponseDTO;
import se.iths.yunuscan.microprojectorderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @Valid @RequestBody CreateOrderRequestDTO request,
            @AuthenticationPrincipal Jwt jwt
            ) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(request,jwt);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(
            @AuthenticationPrincipal Jwt jwt
    ) {
        List<OrderResponseDTO> orders = orderService.findMyOrders(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
