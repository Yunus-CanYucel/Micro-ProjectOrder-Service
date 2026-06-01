package se.iths.yunuscan.microprojectorderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        LocalDateTime orderDate,
        String customerName,
        List<OrderItemResponseDTO> orderItems,
        BigDecimal totalPrice
) {
}
