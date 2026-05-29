package se.iths.yunuscan.microprojectorderservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStockResponseDTO {
    private Long productId;
    private String name;
    private BigDecimal price;
    private int stock;

}
