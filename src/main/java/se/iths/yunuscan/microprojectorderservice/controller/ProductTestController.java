package se.iths.yunuscan.microprojectorderservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import se.iths.yunuscan.microprojectorderservice.client.ProductClient;
import se.iths.yunuscan.microprojectorderservice.dto.ProductStockRequestDTO;
import se.iths.yunuscan.microprojectorderservice.dto.ProductStockResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/test-product")
@RequiredArgsConstructor
public class ProductTestController {

    private final ProductClient productClient;

    @PostMapping("/products/stock/decrease")
    public ResponseEntity<List<ProductStockResponseDTO>> testStockDecrease(
            @Valid @RequestBody List<ProductStockRequestDTO> requests,
            @AuthenticationPrincipal Jwt jwt
    ) {
        // Call the method not via OrderService, skipping RabbitMQ
        List<ProductStockResponseDTO> responses =
                productClient.decreaseStock(requests, jwt.getTokenValue());
        return ResponseEntity.ok(responses);
    }
}