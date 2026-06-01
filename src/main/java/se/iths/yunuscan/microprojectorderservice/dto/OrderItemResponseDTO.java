package se.iths.yunuscan.microprojectorderservice.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        int quantity
) {
}
